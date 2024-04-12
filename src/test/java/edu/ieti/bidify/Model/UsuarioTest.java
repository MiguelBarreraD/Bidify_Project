package edu.ieti.bidify.Model;

import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.security.enums.RolEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void testConstructor() {
        // Arrange
        int id = 1;
        String userName = "user1";
        String nombre = "User One";
        String email = "user1@example.com";
        String password = "password";
        List<RolEnum> roles = new ArrayList<>();
        roles.add(RolEnum.ADMIN);

        // Act
        Usuario usuario = new Usuario(id, userName, nombre, email, password, roles);

        // Assert
        assertNotNull(usuario);
        assertEquals(id, usuario.getId());
        assertEquals(userName, usuario.getUserName());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(email, usuario.getEmail());
        assertEquals(password, usuario.getPassword());
        assertEquals(roles, usuario.getRoles());
        assertNotNull(usuario.getProductos());
        assertTrue(usuario.getProductos().isEmpty());
    }

    @Test
    void testAddProducto() {
        // Arrange
        int id = 1;
        String userName = "user1";
        String nombre = "User One";
        String email = "user1@example.com";
        String password = "password";
        List<RolEnum> roles = Arrays.asList(RolEnum.ADMIN);
    
        Usuario usuario = new Usuario(id, userName, nombre, email, password, roles);
        String productoId = "123";
    
        // Act
        usuario.addProducto(productoId);
    
        // Assert
        assertFalse(usuario.getProductos().isEmpty());
        assertTrue(usuario.getProductos().contains(productoId));
    }
    
    @Test
    void testRemoveProducto() {
        // Arrange
        int id = 1;
        String userName = "user1";
        String nombre = "User One";
        String email = "user1@example.com";
        String password = "password";
        List<RolEnum> roles = Arrays.asList(RolEnum.ADMIN);
    
        Usuario usuario = new Usuario(id, userName, nombre, email, password, roles);
        String productoId = "123";
        usuario.addProducto(productoId);
    
        // Act
        usuario.removeProducto(productoId);
    
        // Assert
        assertTrue(usuario.getProductos().isEmpty());
    }
    

}