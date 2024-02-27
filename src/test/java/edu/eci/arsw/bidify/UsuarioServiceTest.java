package edu.eci.arsw.bidify;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import edu.eci.arsw.bidify.model.Usuario;
import edu.eci.arsw.bidify.repository.UsuarioRepository;
import edu.eci.arsw.bidify.service.UsuarioService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testDeleteUserName() {
        // Arrange
        String userName = "testUser";
        // Act
        usuarioService.deleteUserName(userName);
        // Assert
        verify(usuarioRepository, times(1)).deleteByUserName(userName);
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        Usuario user = new Usuario();
        user.setId(1);
        when(usuarioRepository.existsById(user.getId())).thenReturn(true);
        // Act
        usuarioService.updateUser(user);
        // Assert
        verify(usuarioRepository, times(1)).save(user);
    }

    @Test
    public void testLogin() {
        // Arrange
        String userName = "testUser";
        String password = "testPassword";
        Usuario user = new Usuario();
        when(usuarioRepository.findByUserNameAndPassword(userName, password)).thenReturn(user);
        // Act
        boolean result = usuarioService.login(userName, password);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Usuario> userList = new ArrayList<>();
        when(usuarioRepository.findAll()).thenReturn(userList);
        // Act
        List<Usuario> result = usuarioService.findAll();
        // Assert
        assertEquals(userList, result);
    }

    @Test
    public void testRegistrarUsuario() {
        // Arrange
        Usuario user = new Usuario();
        // Act
        usuarioService.registrarUsuario(user);
        // Assert
        verify(usuarioRepository, times(1)).save(user);
    }

    @Test
    public void testExistsById() {
        // Arrange
        int userId = 1;
        when(usuarioRepository.existsById(userId)).thenReturn(true);
        // Act
        boolean result = usuarioService.existsById(userId);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testExistsByUserName() {
        // Arrange
        String userName = "testUser";
        when(usuarioRepository.existsByUserName(userName)).thenReturn(true);
        // Act
        boolean result = usuarioService.existsByUserName(userName);
        // Assert
        assertTrue(result);
    }
    
    //corregir:

    /*
     * 
    @Test
    public void testFindByUserName() {
        // Arrange
        String userName = "testUser";
        Usuario user = new Usuario();
        when(usuarioRepository.findByUserName(userName)).thenReturn(user);
        // Act
        Usuario result = usuarioService.findByUserName(userName);
        // Assert
        assertEquals(user, result);
    }
    
     */
}

