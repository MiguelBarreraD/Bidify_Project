package edu.eci.arsw.bidify.dto;
import java.util.List;

import edu.eci.arsw.bidify.model.Producto;
import lombok.Data;

@Data
public class UsuarioDto {
    private String usuario;
    private String nombre;
    private String email;
    private String password;

    public UsuarioDto(String usuario, String nombre, String email, String password){
        this.usuario=usuario;
        this.nombre=nombre;
        this.email=email;
        this.password=password;
    }
    
}
