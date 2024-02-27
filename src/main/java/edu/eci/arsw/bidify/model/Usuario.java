package edu.eci.arsw.bidify.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    @NotNull
    private String nombre;
    @NotNull
    private String email;
    private String password;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Producto> productos;


    public Usuario(String userName, String nombre, String email, String password){
        this.userName=userName;
        this.nombre=nombre;
        this.email=email;
        this.password=password;
        productos = new ArrayList<>();
    }
    
    public void addProducto(Producto producto) {
        productos.add(producto);
        producto.setUsuario(this);
    }

    public void removeProducto(Producto producto) {
        productos.remove(producto);
        producto.setUsuario(null);
    }
}
