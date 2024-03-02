package edu.ieti.bidify.controller;

import edu.ieti.bidify.dto.MessageDto;
import edu.ieti.bidify.model.Subasta;
import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.service.SubastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST que maneja las solicitudes relacionadas con las subastas en el sistema.
 */
@RestController
@RequestMapping("/subasta")
@CrossOrigin(origins = "*")
public class SubastaController{
    @Autowired
    private SubastaService subastaService;

    /**
     * Crea una nueva subasta en el sistema.
     *
     * @param subasta Subasta a crear.
     * @return Respuesta HTTP con la subasta creada.
     */
    @PostMapping
    public ResponseEntity<Subasta> createSubasta(@RequestBody Subasta subasta) {
        Subasta newSubasta = subastaService.addSubasta(subasta);
        return new ResponseEntity<>(newSubasta, HttpStatus.OK);
    }

    /**
     * Obtiene todas las subastas existentes en el sistema.
     *
     * @return Respuesta HTTP con la lista de subastas.
     */
    @GetMapping
    public ResponseEntity<List<Subasta>> getAllSubastas() {
        List<Subasta> subastas = subastaService.findAllSubastas();
        return new ResponseEntity<>(subastas, HttpStatus.OK);
    }

    /**
     * Obtiene una subasta por su identificador único.
     *
     * @param id Identificador único de la subasta.
     * @return Respuesta HTTP con la subasta encontrada, o NOT_FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Subasta> getSubastaById(@PathVariable int id) {
        Optional<Subasta> subasta = subastaService.getSubastaById(id);
        if (subasta.isPresent()) {
            return new ResponseEntity<>(subasta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene la lista de mensajes asociados a una subasta por su identificador único.
     *
     * @param subastaId Identificador único de la subasta.
     * @return Respuesta HTTP con la lista de mensajes encontrada, o NOT_FOUND si la subasta no existe.
     */
    @GetMapping("/{subastaId}/messages")
    public ResponseEntity<List<MessageDto>> getMessageList(@PathVariable int subastaId) {
        List<MessageDto> messageList = subastaService.getMessageList(subastaId);

        if (messageList != null) {
            return new ResponseEntity<>(messageList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Recibe una puja para una subasta específica.
     *
     * @param subastaId Identificador único de la subasta.
     * @param usuario Usuario que realiza la puja.
     * @param oferta Oferta realizada en la puja.
     * @return Respuesta HTTP con la subasta actualizada, o BAD_REQUEST si la subasta no existe o no está activa.
     */
    @PostMapping("/{subastaId}/pujas")
    public ResponseEntity<Subasta> recibirPuja(@PathVariable int subastaId, @RequestBody Usuario usuario, @RequestBody BigDecimal oferta) {
        Subasta subasta = subastaService.getSubastaById(subastaId).orElse(null);

        if (subasta != null && subasta.isEstado()) {
            subastaService.recibirPuja(usuario, oferta);

            return new ResponseEntity<>(subasta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}