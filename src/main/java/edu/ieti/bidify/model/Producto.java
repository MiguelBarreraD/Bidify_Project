package edu.ieti.bidify.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Clase que representa un producto en el sistema de subastas.
 */
@Data
@Document(collection = "productos")
public class Producto {
    @Id
    private int id;
    private String nombre;
    private float precio;
    private String img;

    /**
     * Constructor por defecto de la clase Producto.
     * Se utiliza para la serialización/deserialización de MongoDB.
     */
    public Producto(){

    }
    /**
     * Constructor de la clase Producto.
     *
     * @param id Identificador único del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param img URL de la imagen del producto.
     */
    public Producto(int id, String nombre, float precio, String img) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.img = img;
    }
}
