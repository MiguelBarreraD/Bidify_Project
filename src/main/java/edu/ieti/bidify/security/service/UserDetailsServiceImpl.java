package edu.ieti.bidify.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioRepository.findByUserNameOrEmail(username, username);
        if(user.isEmpty()){
            return null;
        }else{
            return UserPrincipal.build(user.get());
        }
    }
    
}
