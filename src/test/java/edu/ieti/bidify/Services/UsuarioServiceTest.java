package edu.ieti.bidify.Services;

import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.dto.UsuarioDto;
import edu.ieti.bidify.exceptions.AttributeException;
import edu.ieti.bidify.repository.UsuarioRepository;
import edu.ieti.bidify.security.enums.RolEnum;
import edu.ieti.bidify.security.jwt.JWTGenerator;
import edu.ieti.bidify.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTGenerator jwtProvider;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void testDeleteUserName() {
        // Arrange
        String userName = "user1";

        // Act
        usuarioService.deleteUserName(userName);

        // Assert
        verify(usuarioRepository, times(1)).deleteByUserName(userName);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Usuario user = new Usuario(1, "user1", "User 1", "user1@example.com", "password", Arrays.asList(RolEnum.ADMIN));
        when(usuarioRepository.existsById(user.getId())).thenReturn(true);

        // Act
        usuarioService.updateUser(user);

        // Assert
        verify(usuarioRepository, times(1)).save(user);
    }

    @Test
    void testLogin() {
        // Arrange
        String userName = "user1";
        String password = "password";
        Usuario user = new Usuario(1, userName, "User 1", "user1@example.com", password, Arrays.asList(RolEnum.ADMIN));
        when(usuarioRepository.findByUserNameAndPassword(userName, password)).thenReturn(user);

        // Act
        boolean result = usuarioService.login(userName, password);

        // Assert
        assertTrue(result);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Usuario> users = Arrays.asList(
                new Usuario(1, "user1", "User 1", "user1@example.com", "password", Arrays.asList(RolEnum.ADMIN)),
                new Usuario(2, "user2", "User 2", "user2@example.com", "password", Arrays.asList(RolEnum.USER)));
        when(usuarioRepository.findAll()).thenReturn(users);

        // Act
        List<Usuario> result = usuarioService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(users, result);
    }

    @Test
    void testRegistrarUsuario() {
        Usuario usuario = new Usuario(1, "user1", "User 1", "user1@example.com", "password",
                Arrays.asList(RolEnum.ADMIN));
        usuarioService.registrarUsuario(usuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testCrearUsuario() throws AttributeException {
        UsuarioDto usuarioDto = new UsuarioDto("MiguelB", "Miguel", "user1@example.com", "password",
                Arrays.asList("ADMIN"));
        Usuario usuario = new Usuario(1, "MiguelB", "Miguel", "user1@example.com", "encoded_password",
                Arrays.asList(RolEnum.ADMIN));

        when(usuarioRepository.existsByUserName(usuarioDto.getUserName())).thenReturn(false);
        when(usuarioRepository.existsByEmail(usuarioDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(usuarioDto.getPassword())).thenReturn("encoded_password");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.crearUsuario(usuarioDto);

        assertNotNull(result);
        assertEquals(usuario, result);
    }

    @Test
    void testExistsById() {
        // Arrange
        int id = 1;
        when(usuarioRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = usuarioService.existsById(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void testExistsByUserName() {
        // Arrange
        String userName = "user1";
        when(usuarioRepository.existsByUserName(userName)).thenReturn(true);

        // Act
        boolean result = usuarioService.existsByUserName(userName);

        // Assert
        assertTrue(result);
    }
}