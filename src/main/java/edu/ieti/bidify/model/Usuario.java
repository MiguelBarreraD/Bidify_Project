package edu.ieti.bidify.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;
/**
 * Clase que representa un usuario en el sistema de subastas.
 */
@Data
@Document(collection = "usuarios")
public class Usuario {
    @Id
    private int id;
    private String userName;
    private String nombre;
    private String email;
    private String password;
    private List<String> productos; // Solo almacenamos IDs de productos

    /**
     * Constructor de la clase Usuario.
     *
     * @param userName Nombre de usuario.
     * @param nombre Nombre del usuario.
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     */
    public Usuario(String userName, String nombre, String email, String password) {
        this.userName = userName;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        productos = new ArrayList<>();
    }

    /**
     * Agrega un ID de producto a la lista de productos asociados al usuario.
     *
     * @param productoId ID del producto a agregar.
     */
    public void addProducto(String productoId) {
        productos.add(productoId);
    }

    /**
     * Remueve un ID de producto de la lista de productos asociados al usuario.
     *
     * @param productoId ID del producto a remover.
     */
    public void removeProducto(String productoId) {
        productos.remove(productoId);
    }
}
