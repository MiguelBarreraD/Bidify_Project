package edu.eci.arsw.bidify.controller;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import edu.eci.arsw.bidify.service.SubastaService;
import edu.eci.arsw.bidify.service.UsuarioService;
import edu.eci.arsw.bidify.dto.MessageDto;
import edu.eci.arsw.bidify.dto.SubastaDto;
import edu.eci.arsw.bidify.model.Subasta;
import edu.eci.arsw.bidify.model.Usuario;

@RestController
@RequestMapping("/subasta")
@CrossOrigin(origins = "*")
public class SubastaController{
    @Autowired
    private SubastaService subastaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Subasta>> getAllSubastas() {
        List<Subasta> subastas = subastaService.findAllSubastas();
        return new ResponseEntity<>(subastas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public synchronized ResponseEntity<Subasta> getSubastaById(@PathVariable int id) {
        
        Optional<Subasta> subasta = subastaService.getSubastaById(id);
        if (subasta.isPresent()) {
            return new ResponseEntity<>(subasta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{subastaId}/messages")
    public ResponseEntity<List<MessageDto>> getMessageList(@PathVariable int subastaId) {
        List<MessageDto> messageList = subastaService.getMessageList(subastaId);

        if (messageList != null) {
            return new ResponseEntity<>(messageList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
