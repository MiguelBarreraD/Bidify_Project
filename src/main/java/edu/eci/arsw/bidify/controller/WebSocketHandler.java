package edu.eci.arsw.bidify.controller;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.eci.arsw.bidify.dto.MessageDto;
import edu.eci.arsw.bidify.dto.SubastaDto;
import edu.eci.arsw.bidify.model.Subasta;
import edu.eci.arsw.bidify.model.Usuario;
import edu.eci.arsw.bidify.service.SubastaService;
import edu.eci.arsw.bidify.service.UsuarioService;

@Controller
public class WebSocketHandler extends StompSessionHandlerAdapter{
    @Autowired
    SimpMessagingTemplate msgt;
    @Autowired
    private SubastaService subastaService;
    @Autowired
    private UsuarioService usuarioService;

    @MessageMapping("/{subastaId}/messages")
    public synchronized ResponseEntity<Subasta> addMessageToSubasta(@DestinationVariable int subastaId, @RequestBody MessageDto messageDto) {
        
        Subasta subasta = subastaService.addMessageAndProcessBid(messageDto, subastaId);
        while(subastaService.getMonitor().isSuspendido() == false){
        }
        subasta = subastaService.getSubastaById(subastaId).get();
        System.out.println(subasta.getPrecioFinal());
        if (subasta != null) {
            msgt.convertAndSend("/topic/subasta/" + subastaId + "/messages", subasta);
            return new ResponseEntity<>(subasta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @MessageMapping("/subasta/{id}")
    public ResponseEntity<Subasta> getMontoGanador(@DestinationVariable int subastaId) {
        
        Optional<Subasta> subasta = subastaService.getSubastaById(subastaId);
        if (subasta.isPresent()) {
            msgt.convertAndSend("/topic/subasta/" + subastaId + "/obtener", subasta);
            return new ResponseEntity<>(subasta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @MessageMapping("/{subastaId}/finalizar")
    public ResponseEntity<Subasta> finalizarSubasta(@DestinationVariable  int subastaId) {

        Optional<Subasta> subastaOptional = subastaService.getSubastaById(subastaId);
        if (subastaOptional.isPresent()) {
            Subasta subasta = subastaOptional.get();
            subastaService.finalizarSubasta(subastaId);
            msgt.convertAndSend("/topic/subasta/" + subastaId + "/finalizar", subasta);
            return new ResponseEntity<>(subasta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @MessageMapping("/{subastaId}/iniciar")
    public ResponseEntity<Subasta> iniciarSubasta(@DestinationVariable  int subastaId) {
        Optional<Subasta> subastaOptional = subastaService.getSubastaById(subastaId);
        if (subastaOptional.isPresent()) {
            Subasta subasta = subastaOptional.get();
            subastaService.iniciarSubasta(subastaId);
            msgt.convertAndSend("/topic/subasta/" + subastaId + "/iniciar", subasta);
            return new ResponseEntity<>(subasta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @MessageMapping("/{subastaId}")
    public ResponseEntity<Subasta> actualizarSubasta(@PathVariable("subastaId") int subastaId, @RequestBody SubastaDto subastaDto) {

        Optional<Subasta> subastaOptional = subastaService.getSubastaById(subastaId);
        if (subastaOptional.isPresent()) {
            Subasta subasta = subastaOptional.get();
            subasta.setId(subastaId);
            subasta.setEstado(subastaDto.isEstado());
            subasta.setGanador(subastaDto.getGanador());
            subasta.setMessageList(subastaDto.getMessageList());
            subasta.setOferentes(subastaDto.getOferentes());
            subasta.setPrecioFinal(subastaDto.getPrecioFinal());
            subastaService.addSubasta(subasta);
            msgt.convertAndSend("/topic/subasta/" + subastaId + "/actualizacion", subasta);
            return new ResponseEntity<>(subasta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @MessageMapping("/subasta/crear")
    public ResponseEntity<Subasta> createSubasta(@RequestBody Subasta subasta) {
        Subasta newSubasta = subastaService.addSubasta(subasta);
        msgt.convertAndSend("/topic/subasta/crear", subasta);
        return new ResponseEntity<>(newSubasta, HttpStatus.OK);
    }

    @MessageMapping("/{subastaId}/{usuario}/unirse")
    public ResponseEntity<Subasta> unirseSubasta(@DestinationVariable int subastaId,
            @DestinationVariable String usuario) {
        Usuario user = usuarioService.getUsuarioByUserName(usuario).get();
        Optional<Subasta> subasta = subastaService.getSubastaById(subastaId);
        if (subasta.isPresent()) {
            subastaService.añadirParticipante(subastaId, user);
            msgt.convertAndSend("/topic/subasta/" + subastaId + "/" + usuario +"/unirse", subasta);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
} 
