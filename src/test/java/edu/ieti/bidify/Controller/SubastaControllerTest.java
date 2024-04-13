package edu.ieti.bidify.Controller;

import edu.ieti.bidify.controller.SubastaController;
import edu.ieti.bidify.dto.MessageDto;
import edu.ieti.bidify.model.Subasta;
import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.security.enums.RolEnum;
import edu.ieti.bidify.service.SubastaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubastaControllerTest {

    @Mock
    private SubastaService subastaService;

    @InjectMocks
    private SubastaController subastaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSubasta() {
        Subasta subasta = new Subasta();
        Subasta newSubasta = new Subasta();

        when(subastaService.addSubasta(subasta)).thenReturn(newSubasta);

        ResponseEntity<Subasta> responseEntity = subastaController.createSubasta(subasta);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newSubasta, responseEntity.getBody());
        verify(subastaService, times(1)).addSubasta(subasta);
    }

    @Test
    void testGetAllSubastas() {
        List<Subasta> subastas = Arrays.asList(new Subasta(), new Subasta());

        when(subastaService.findAllSubastas()).thenReturn(subastas);

        ResponseEntity<List<Subasta>> responseEntity = subastaController.getAllSubastas();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subastas, responseEntity.getBody());
        verify(subastaService, times(1)).findAllSubastas();
    }

    @Test
    void testGetSubastaById() {
        int id = 1;
        Subasta subasta = new Subasta();

        when(subastaService.getSubastaById(id)).thenReturn(Optional.of(subasta));

        ResponseEntity<Subasta> responseEntity = subastaController.getSubastaById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subasta, responseEntity.getBody());
        verify(subastaService, times(1)).getSubastaById(id);
    }

    @Test
    void testGetSubastaByIdNotFound() {
        int id = 1;

        when(subastaService.getSubastaById(id)).thenReturn(Optional.empty());

        ResponseEntity<Subasta> responseEntity = subastaController.getSubastaById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(subastaService, times(1)).getSubastaById(id);
    }

    @Test
    void testGetMessageList() {
        int subastaId = 1;
        List<MessageDto> messageList = Arrays.asList(new MessageDto(), new MessageDto());

        when(subastaService.getMessageList(subastaId)).thenReturn(messageList);

        ResponseEntity<List<MessageDto>> responseEntity = subastaController.getMessageList(subastaId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(messageList, responseEntity.getBody());
        verify(subastaService, times(1)).getMessageList(subastaId);
    }

    @Test
    void testGetMessageListNotFound() {
        int subastaId = 1;

        when(subastaService.getMessageList(subastaId)).thenReturn(null);

        ResponseEntity<List<MessageDto>> responseEntity = subastaController.getMessageList(subastaId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(subastaService, times(1)).getMessageList(subastaId);
    }

    @Test
    void testRecibirPuja() {
        int subastaId = 1;
        Usuario usuario = new Usuario(1, "userName", "nombre", "email", "password", Arrays.asList(RolEnum.ROLE_USER));
        BigDecimal oferta = new BigDecimal("100.00");
        Subasta subasta = mock(Subasta.class);

        when(subastaService.getSubastaById(subastaId)).thenReturn(Optional.of(subasta));
        when(subasta.isEstado()).thenReturn(true);

        ResponseEntity<Subasta> responseEntity = subastaController.recibirPuja(subastaId, usuario, oferta);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subasta, responseEntity.getBody());
        verify(subastaService, times(1)).getSubastaById(subastaId);
        verify(subasta, times(1)).isEstado();
    }

    @Test
    void testRecibirPujaBadRequest() {
        int subastaId = 1;
        Usuario usuario = new Usuario(1, "userName", "nombre", "email", "password", Arrays.asList(RolEnum.ROLE_USER));
        BigDecimal oferta = new BigDecimal("100.00");

        when(subastaService.getSubastaById(subastaId)).thenReturn(Optional.empty());

        ResponseEntity<Subasta> responseEntity = subastaController.recibirPuja(subastaId, usuario, oferta);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(subastaService, times(1)).getSubastaById(subastaId);
        verify(subastaService, never()).recibirPuja(usuario, oferta);
    }
}