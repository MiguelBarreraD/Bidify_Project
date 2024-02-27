package edu.eci.arsw.bidify.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.bidify.model.Usuario;
import edu.eci.arsw.bidify.repository.UsuarioRepository;
import lombok.Data;

@Service
@Data
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public void deleteUserName(String userName) {
        usuarioRepository.deleteByUserName(userName);
    }

    public void updateUser(Usuario user) {
        if(usuarioRepository.existsById(user.getId())){
            usuarioRepository.save(user);
        }
    }

    public boolean login(String userName, String password) {
        Usuario user = usuarioRepository.findByUserNameAndPassword(userName, password);
        return user != null;
    }
    
    public Optional<Usuario> getUsuarioByUserName(String userName) {
        return usuarioRepository.findByUserName(userName);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public void registrarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public boolean existsById(int id) {
        return usuarioRepository.existsById(id);
    }

    public boolean existsByUserName(String userName) {
        return usuarioRepository.existsByUserName(userName);
    }

    
}
