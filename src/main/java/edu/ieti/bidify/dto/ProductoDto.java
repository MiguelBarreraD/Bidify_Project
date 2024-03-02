package edu.ieti.bidify.dto;

import lombok.Data;

/**
 * Clase que representa un objeto de transferencia de datos (DTO) para un producto.
 */
@Data
public class ProductoDto {
    private String nombre;
    private float precio;
    private String img;

    /**
     * Constructor por defecto de la clase ProductoDto.
     */
    public ProductoDto(){

    }

    /**
     * Constructor de la clase ProductoDto.
     *
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param img URL de la imagen del producto.
     */
    public ProductoDto(String nombre, float precio, String img) {
        this.nombre = nombre;
        this.precio = precio;
        this.img = img;
    }
}
