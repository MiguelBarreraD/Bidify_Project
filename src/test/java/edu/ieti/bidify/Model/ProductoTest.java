package edu.ieti.bidify.Model;



import org.junit.jupiter.api.Test;

import edu.ieti.bidify.model.Producto;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    void testConstructor() {
        // Arrange
        int id = 1;
        String nombre = "Producto1";
        float precio = 100.0f;
        String img = "imagen1.jpg";

        Producto producto = new Producto(id, nombre, precio, img);

        assertEquals(id, producto.getId());
        assertEquals(nombre, producto.getNombre());
        assertEquals(precio, producto.getPrecio());
        assertEquals(img, producto.getImg());
    }

    @Test
    void testSetters() {

        Producto producto = new Producto();
        int id = 2;
        String nombre = "Producto2";
        float precio = 200.0f;
        String img = "imagen2.jpg";

        producto.setId(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setImg(img);

        assertEquals(id, producto.getId());
        assertEquals(nombre, producto.getNombre());
        assertEquals(precio, producto.getPrecio());
        assertEquals(img, producto.getImg());
    }
}
