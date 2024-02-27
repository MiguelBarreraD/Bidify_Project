package edu.eci.arsw.bidify;
import edu.eci.arsw.bidify.controller.UsuarioController;
import edu.eci.arsw.bidify.dto.Mensaje;
import edu.eci.arsw.bidify.model.Producto;
import edu.eci.arsw.bidify.model.Usuario;
import edu.eci.arsw.bidify.service.ProductoService;
import edu.eci.arsw.bidify.service.UsuarioService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private ProductoService productoService;

    @Test
    public void testList() {
        // Arrange
        Usuario usuario1 = new Usuario("migue", "Miguel Angel", "miguel@gmail.com", "123");
        Usuario usuario2 = new Usuario("jaider", "Jaider Gonzalez", "jaider@gmail.com", "123");
        Usuario usuario3 = new Usuario("santi", "Santiago Gonzalez", "santiago@gmail.com", "123");
        Usuario usuario4 = new Usuario("camilo", "camilo Angel", "camilo@gmail.com", "123");
        Usuario usuario5 = new Usuario("sebastian", "sebastian Gonzalez", "sebastian@gmail.com", "123");
        Usuario usuario6 = new Usuario("andrea", "andrea Gonzalez", "andrea@gmail.com", "123");
        usuarioService.registrarUsuario(usuario1);
        usuarioService.registrarUsuario(usuario2);
        usuarioService.registrarUsuario(usuario3);
        usuarioService.registrarUsuario(usuario4);
        usuarioService.registrarUsuario(usuario5);
        usuarioService.registrarUsuario(usuario6);
        // Act
        ResponseEntity<List<Usuario>> response = usuarioController.list();
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testRegistrar() {
        // Arrange
        Usuario usuario = new Usuario("migue", "Miguel Angel", "miguel@gmail.com", "123");
        // Act
        ResponseEntity<?> response = usuarioController.registrar(usuario);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario registrado", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testRegistrarBlankUsernameAndPassword() {
        // Arrange
        Usuario usuario = new Usuario("migue", null, "miguel@gmail.com", null);
        // Act
        ResponseEntity<?> response = usuarioController.registrar(usuario);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El nombre de usuario y la contraseña son obligatorios", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testDelete() {
        // Arrange
        String userName = "angelito";
        Usuario usuario = new Usuario("angelito", "Miguel Angel", "miguel@gmail.com", "123");
        usuarioService.registrarUsuario(usuario);
        // Act
        ResponseEntity<?> response = usuarioController.delete(userName);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario eliminado", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testDeleteNotFound() {
        // Arrange
        String userName = "username";
        // Act
        ResponseEntity<?> response = usuarioController.delete(userName);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("El usuario no existe", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testUpdate() {
        // Arrange
        Usuario usuario = new Usuario("angelito", "Miguel Angel", "miguel@gmail.com", "123");
        usuarioService.registrarUsuario(usuario);
        // Act
        ResponseEntity<?> response = usuarioController.update(usuario);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario actualizado", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testUpdateNotFound() {
        // Arrange
        Usuario usuario = new Usuario("angelito", "Miguel Angel", "miguel@gmail.com", "123");
        // Act
        ResponseEntity<?> response = usuarioController.update(usuario);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("El usuario no existe", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testLoginSuccessful() {
        // Arrange
        Usuario usuario = new Usuario("angelito", "Miguel Angel", "miguel@gmail.com", "123");
        usuarioService.registrarUsuario(usuario);
        // Act
        ResponseEntity<?> response = usuarioController.login(usuario);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login exitoso", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testLoginInvalidCredentials() {
        // Arrange
        Usuario usuario = new Usuario("angelito", "Miguel Angel", "miguel@gmail.com", "123");
        // No registres el usuario en usuarioService
        // Act
        ResponseEntity<?> response = usuarioController.login(usuario);
        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciales inválidas", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testGetProductos() {
        // Arrange
        String userName = "Liceth";
        Usuario usuario0 = new Usuario("Liceth", "KarenLi", "karen@gmail.com", "123");
        usuarioService.registrarUsuario(usuario0);
        Producto productoprueba1 = new Producto("Jordan One", (float) 600000, "https://phantom-expansion.unidadeditorial.es/6239da431613d30a7ade440a4719e3db/crop/0x378/1074x982/resize/828/f/jpg/assets/multimedia/imagenes/2022/03/21/16478732471407.jpg");
        productoprueba1.setUsuario(usuario0);
        productoService.save(productoprueba1);
        Producto productoprueba2 = new Producto("Camara", (float) 800000, "https://www.workshopexperience.com/wp-content/uploads/2017/07/marcas-de-camaras-fotograficas-4.jpg");
        productoprueba2.setUsuario(usuario0);
        productoService.save(productoprueba2);
        usuario0.addProducto(productoprueba2);
        usuario0.addProducto(productoprueba1);
        // Act
        ResponseEntity<?> response = usuarioController.getProductos(userName);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    @Test
    public void testGetProductosNoProductos() {
        // Arrange
        String userName = "Amor";
        Usuario usuario = new Usuario("Amor", "KarenLi", "karen@gmail.com", "123");
        usuarioService.registrarUsuario(usuario);
        // Act
        ResponseEntity<?> response = usuarioController.getProductos(userName);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("El usuario no tiene productos", ((Mensaje) response.getBody()).getMensaje());
    }
}

