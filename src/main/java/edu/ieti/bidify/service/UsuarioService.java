package edu.ieti.bidify.service;


import edu.ieti.bidify.dto.UsuarioDto;
import edu.ieti.bidify.exceptions.AttributeException;
import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.repository.UsuarioRepository;
import edu.ieti.bidify.security.enums.RolEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona las operaciones relacionadas con los usuarios en el sistema de subastas.
 */
@Service
@Data
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Elimina un usuario por su nombre de usuario.
     *
     * @param userName Nombre de usuario del usuario a eliminar.
     */
    public void deleteUserName(String userName) {
        usuarioRepository.deleteByUserName(userName);
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param user Usuario con la nueva información.
     */
    public void updateUser(Usuario user) {
        if(usuarioRepository.existsById(user.getId())){
            usuarioRepository.save(user);
        }
    }

    /**
     * Realiza la autenticación de un usuario.
     *
     * @param userName Nombre de usuario del usuario.
     * @param password Contraseña del usuario.
     * @return true si la autenticación es exitosa, false de lo contrario.
     */
    public boolean login(String userName, String password) {
        Usuario user = usuarioRepository.findByUserNameAndPassword(userName, password);
        return user != null;
    }

    /**
     * Obtiene una lista de todos los usuarios en el sistema.
     *
     * @return Lista de usuarios.
     */
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario Usuario a registrar.
     */
    public void registrarUsuario(Usuario usuario){
        int id = autoIncrement();
        usuario.setId(id);
        usuarioRepository.save(usuario);
    }

    /**
     * Registra un nuevo usuario en el sistema a partir de UsuarioDto.
     * @param usuarioDto Usuario a registrar.
     * @throws AttributeException Si el nombre de usuario ya existe.
     * @return Usuario registrado.
     */

    public Usuario crearUsuario(UsuarioDto usuarioDto) throws AttributeException{
        if(usuarioRepository.existsByUserName(usuarioDto.getUserName())) {
            throw new AttributeException("El nombre de usuario ya existe");
        }
        if(usuarioRepository.existsByEmail(usuarioDto.getEmail())) {
            throw new AttributeException("El correo ya está registrado");
        }
        int id = autoIncrement();
        List<RolEnum> roles = usuarioDto.getRoles().stream().map(rol -> RolEnum.valueOf(rol)).collect(Collectors.toList());
        Usuario usuario = new Usuario(id, usuarioDto.getUserName(), usuarioDto.getNombre(), usuarioDto.getEmail(), usuarioDto.getPassword(), roles);
        return usuarioRepository.save(usuario);
    }

    /**
     * Incrementa el ID de los usuarios automáticamente.
     *
     * @return Nuevo ID.
     */
    private int autoIncrement(){
        List<Usuario> usuario = usuarioRepository.findAll();
        return usuario.isEmpty()? 1 : usuario.stream().max(Comparator.comparing(Usuario::getId)).get().getId() + 1;
    }

    /**
     * Verifica si existe un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return true si existe, false de lo contrario.
     */
    public boolean existsById(int id) {
        return usuarioRepository.existsById(id);
    }

    /**
     * Verifica si existe un usuario por su nombre de usuario.
     *
     * @param userName Nombre de usuario.
     * @return true si existe, false de lo contrario.
     */
    public boolean existsByUserName(String userName) {
        return usuarioRepository.existsByUserName(userName);
    }
}
