package edu.ieti.bidify.security.service;

import java.util.Collection;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.ieti.bidify.model.Usuario;

public class UserPrincipal implements UserDetails{
    private String userName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String userName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal build(Usuario usuario) {
        Collection<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());
        return new UserPrincipal(usuario.getUserName(), usuario.getEmail(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return userName;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getEmail() {
        return email;
    }
}
