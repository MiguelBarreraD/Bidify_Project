package edu.ieti.bidify.controller;

import edu.ieti.bidify.dto.Mensaje;
import edu.ieti.bidify.dto.UsuarioDto;
import edu.ieti.bidify.exceptions.AttributeException;
import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.service.UsuarioService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST que maneja las solicitudes relacionadas con los usuarios en el sistema.
 */
@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    /**
     * Obtiene la lista de todos los usuarios registrados en el sistema.
     *
     * @return Lista de usuarios.
     */
    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = usuarioService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario Usuario a registrar.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        if(StringUtils.isBlank(usuario.getUserName()) || StringUtils.isBlank(usuario.getPassword()))
            return new ResponseEntity<>(new Mensaje("El nombre de usuario y la contraseña son obligatorios"), HttpStatus.BAD_REQUEST);

        usuarioService.registrarUsuario(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario registrado"), HttpStatus.OK);
    }

    /*
     * Metodo para crear usuario
     */

    @PostMapping("/crearUsuario")
    public ResponseEntity<?> crear(@Valid @RequestBody UsuarioDto usuarioDto) throws AttributeException{
        usuarioService.crearUsuario(usuarioDto);
        return new ResponseEntity<>(new Mensaje("Usuario registrado"), HttpStatus.OK);
    }

    /**
     * Elimina un usuario del sistema.
     *
     * @param userName Nombre de usuario del usuario a eliminar.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @DeleteMapping("/eliminar/{userName}")
    public ResponseEntity<?> delete(@PathVariable("userName") String userName){
        if(!usuarioService.existsByUserName(userName))
            return new ResponseEntity<>(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);

        usuarioService.deleteUserName(userName);
        return new ResponseEntity<>(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

    /**
     * Actualiza la información de un usuario en el sistema.
     *
     * @param usuario Usuario con la información actualizada.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PutMapping("/actualizar")
    public ResponseEntity<?> update(@RequestBody Usuario usuario){
        if(!usuarioService.existsById(usuario.getId()))
            return new ResponseEntity<>(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);

        usuarioService.updateUser(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    }

    /**
     * Inicia sesión en el sistema con las credenciales proporcionadas.
     *
     * @param usuario Credenciales del usuario para iniciar sesión.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        boolean isAuthenticated = usuarioService.login(usuario.getUserName(), usuario.getPassword());
        if(isAuthenticated){
            return new ResponseEntity<>(new Mensaje("Login exitoso"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("Credenciales inválidas"), HttpStatus.UNAUTHORIZED);
        }
    }
}
