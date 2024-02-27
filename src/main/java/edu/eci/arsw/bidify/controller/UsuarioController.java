package edu.eci.arsw.bidify.controller;

import edu.eci.arsw.bidify.dto.Mensaje;
import edu.eci.arsw.bidify.model.Producto;
import edu.eci.arsw.bidify.model.Usuario;
import edu.eci.arsw.bidify.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = usuarioService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        if(StringUtils.isBlank(usuario.getUserName()) || StringUtils.isBlank(usuario.getPassword()))
            return new ResponseEntity<>(new Mensaje("El nombre de usuario y la contraseña son obligatorios"), HttpStatus.BAD_REQUEST);

        usuarioService.registrarUsuario(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario registrado"), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{userName}")
    public ResponseEntity<?> delete(@PathVariable("userName") String userName){
        if(!usuarioService.existsByUserName(userName))
            return new ResponseEntity<>(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);

        usuarioService.deleteUserName(userName);
        return new ResponseEntity<>(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> update(@RequestBody Usuario usuario){
        if(!usuarioService.existsById(usuario.getId()))
            return new ResponseEntity<>(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);

        usuarioService.updateUser(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        boolean isAuthenticated = usuarioService.login(usuario.getUserName(), usuario.getPassword());
        if(isAuthenticated){
            return new ResponseEntity<>(new Mensaje("Login exitoso"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Mensaje("Credenciales inválidas"), HttpStatus.UNAUTHORIZED);
        }
    }

    @Transactional
    @GetMapping("/productos/{userName}")
    public ResponseEntity<?> getProductos(@PathVariable("userName") String userName) {
        Usuario usuario = usuarioService.getUsuarioByUserName(userName).get();
        if (usuario == null) {
            return new ResponseEntity<>(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);
        }
        List<Producto> productos = usuario.getProductos();
        if (productos.isEmpty()) {
            return new ResponseEntity<>(new Mensaje("El usuario no tiene productos"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productos, HttpStatus.OK);
        }
    }

    @GetMapping("/productos/todos")
    public ResponseEntity<?> getProductosDeTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();

        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(new Mensaje("No hay usuarios disponibles"), HttpStatus.NOT_FOUND);
        } else {
            Map<String, List<Producto>> productosPorUsuario = new HashMap<>();

            for (Usuario usuario : usuarios) {
                List<Producto> productosUsuario = usuario.getProductos();
                productosPorUsuario.put(usuario.getNombre(), productosUsuario);
            }

            if (productosPorUsuario.isEmpty()) {
                return new ResponseEntity<>(new Mensaje("Ningún usuario tiene productos"), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(productosPorUsuario, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/info/{userName}")
    public ResponseEntity<?> getInfoByUserName(@PathVariable String userName) {
        try {
            Usuario usuario = usuarioService.getUsuarioByUserName(userName).get();
            if (usuario != null) {
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener la información del usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

    
