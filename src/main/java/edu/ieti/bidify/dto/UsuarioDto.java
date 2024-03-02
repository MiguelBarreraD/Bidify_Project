package edu.ieti.bidify.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UsuarioDto {

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String userName;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El correo del usuario no puede estar vacío")
    private String email;
    @NotBlank(message = "La contraseña del usuario no puede estar vacío")
    private String password;
    @NotEmpty(message = "La lista de roles no puede estar vacía")
    List<String> roles;

    public UsuarioDto() {
    }
    
    public UsuarioDto(String userName, String nombre, String email, String password, List<String> roles) {
        this.userName = userName;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
