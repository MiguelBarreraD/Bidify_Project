package edu.eci.arsw.bidify;
import edu.eci.arsw.bidify.controller.SubastaController;
import edu.eci.arsw.bidify.controller.WebSocketHandler;
import edu.eci.arsw.bidify.dto.MessageDto;
import edu.eci.arsw.bidify.model.Producto;
import edu.eci.arsw.bidify.model.Subasta;
import edu.eci.arsw.bidify.model.Usuario;
import edu.eci.arsw.bidify.service.ProductoService;
import edu.eci.arsw.bidify.service.SubastaService;
import edu.eci.arsw.bidify.service.UsuarioService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubastaControllerTest {

    @Autowired
    private SubastaService subastaService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SubastaController subastaController;
    @Autowired
    private WebSocketHandler webSocketHandler;
    
    @Test
    public void testCreateSubasta() {
        // Arrange
        Producto producto5 = new Producto("Funko pop Michael Jackson", (float) 600000, "https://http2.mlstatic.com/D_NQ_NP_631613-MCO71749069309_092023-O.webp");
        productoService.save(producto5);
        Usuario usuario1 = new Usuario("migue", "Miguel Angel", "miguel@gmail.com", "123");
        Usuario usuario2 = new Usuario("jaider", "Jaider Gonzalez", "jaider@gmail.com", "123");
        Usuario usuario3 = new Usuario("santi", "Santiago Gonzalez", "santiago@gmail.com", "123");
        usuarioService.registrarUsuario(usuario1);
        usuarioService.registrarUsuario(usuario2);
        usuarioService.registrarUsuario(usuario3);
        Set<Usuario> oferentes = new HashSet<>();
        oferentes.add(usuario1);
        oferentes.add(usuario3);
        BigDecimal bigDecimalValue = new BigDecimal(Float.toString(producto5.getPrecio()));
        Subasta subasta = new Subasta(usuario2, producto5, bigDecimalValue,true, 2);
        subasta.setOferentes(oferentes);
        subastaService.addSubasta(subasta);
        // Act
        ResponseEntity<Subasta> response = webSocketHandler.createSubasta(subasta);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetAllSubastas() {
        // Arrange
        // Act
        ResponseEntity<List<Subasta>> response = subastaController.getAllSubastas();
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetSubastaById() {
        // Arrange
        int subastaId = 1;
        // Agrega una subasta al subastaService con el id dado
        // Act
        ResponseEntity<Subasta> response = subastaController.getSubastaById(subastaId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetSubastaByIdNotFound() {
        // Arrange
        int subastaId = 0;
        // No agregues ninguna subasta al subastaService
        // Act
        ResponseEntity<Subasta> response = subastaController.getSubastaById(subastaId);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetMessageList() {
        // Arrange
        int subastaId = 1;
        // Agrega mensajes a subastaService para la subasta con el subastaId dado
        // Act
        ResponseEntity<List<MessageDto>> response = subastaController.getMessageList(subastaId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetMessageListNotFound() {
        // Arrange
        int subastaId = 1;
        // Act
        ResponseEntity<List<MessageDto>> response = subastaController.getMessageList(subastaId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    /*
     * 
    @Test
    public void testRecibirPuja() {
        // Arrange
        int subastaId = 1;
        Producto producto5 = new Producto("Funko pop Michael Jackson", (float) 600000, "https://http2.mlstatic.com/D_NQ_NP_631613-MCO71749069309_092023-O.webp");
        productoService.save(producto5);
        Usuario usuario1 = new Usuario("migue", "Miguel Angel", "miguel@gmail.com", "123");
        Usuario usuario2 = new Usuario("jaider", "Jaider Gonzalez", "jaider@gmail.com", "123");
        Usuario usuario3 = new Usuario("santi", "Santiago Gonzalez", "santiago@gmail.com", "123");
        usuarioService.registrarUsuario(usuario1);
        usuarioService.registrarUsuario(usuario2);
        usuarioService.registrarUsuario(usuario3);
        Set<Usuario> oferentes = new HashSet<>();
        oferentes.add(usuario1);
        oferentes.add(usuario3);
        BigDecimal bigDecimalValue = new BigDecimal(Float.toString(producto5.getPrecio()));
        Subasta subasta = new Subasta(usuario2, producto5, bigDecimalValue,true, 2);
        subasta.setOferentes(oferentes);
        subastaService.addSubasta(subasta);
        // Act
        ResponseEntity<Subasta> response = subastaController.recibirPuja(subastaId, usuario2, bigDecimalValue);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    
     */


    /*
     * 
    @Test
    public void testRecibirPujaInvalidSubasta() {
        // Arrange
        int subastaId = 0;
        Usuario usuario = new Usuario("jaider", "Jaider Gonzalez", "jaider@gmail.com", "123");
        BigDecimal oferta = BigDecimal.valueOf(100.0);
        // Act
        ResponseEntity<Subasta> response = subastaController.recibirPuja(subastaId, usuario, oferta);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
     */

     /*
      * 
    @Test
    public void testRecibirPujaSubastaNoActiva() {
        // Arrange
        int subastaId = 0;
        Producto producto5 = new Producto("Funko pop Michael Jackson", (float) 600000, "https://http2.mlstatic.com/D_NQ_NP_631613-MCO71749069309_092023-O.webp");
        productoService.save(producto5);
        Usuario usuario1 = new Usuario("migue", "Miguel Angel", "miguel@gmail.com", "123");
        Usuario usuario2 = new Usuario("jaider", "Jaider Gonzalez", "jaider@gmail.com", "123");
        Usuario usuario3 = new Usuario("santi", "Santiago Gonzalez", "santiago@gmail.com", "123");
        usuarioService.registrarUsuario(usuario1);
        usuarioService.registrarUsuario(usuario2);
        usuarioService.registrarUsuario(usuario3);
        Set<Usuario> oferentes = new HashSet<>();
        oferentes.add(usuario1);
        oferentes.add(usuario3);
        BigDecimal bigDecimalValue = new BigDecimal(Float.toString(producto5.getPrecio()));
        Subasta subasta = new Subasta(usuario2, producto5, bigDecimalValue,false, 2);
        subasta.setOferentes(oferentes);
        subastaService.addSubasta(subasta);
        // Act
        ResponseEntity<Subasta> response = subastaController.recibirPuja(subastaId, usuario2, bigDecimalValue);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
      */
}
