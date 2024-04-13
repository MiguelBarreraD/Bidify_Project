package edu.ieti.bidify.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ieti.bidify.controller.UsuarioController;
import edu.ieti.bidify.dto.Mensaje;
import edu.ieti.bidify.dto.UsuarioDto;
import edu.ieti.bidify.exceptions.AttributeException;
import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.security.dto.AuthenticationResponseDto;
import edu.ieti.bidify.security.dto.LoginUsuarioDto;
import edu.ieti.bidify.security.enums.RolEnum;
import edu.ieti.bidify.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void listUsuariosTest() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "userName", "nombre", "email", "password", Arrays.asList(RolEnum.ROLE_USER)));
        when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/usuario/lista"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void registrarUsuarioTest() throws Exception {
        Usuario usuario = new Usuario(1, "userName", "nombre", "email", "password", Arrays.asList(RolEnum.ROLE_USER));
        usuario.setUserName("test");
        usuario.setPassword("password");

        mockMvc.perform(post("/usuario/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }
}
