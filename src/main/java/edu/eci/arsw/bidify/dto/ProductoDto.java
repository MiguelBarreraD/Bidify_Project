package edu.eci.arsw.bidify.dto;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductoDto {

    @NotBlank
    private String nombre;
    @Min(0)
    private Float precio;
    @NotBlank
    private String img;
    public ProductoDto() {
    }

    public ProductoDto(@NotBlank String nombre, @Min(0) Float precio, @NotBlank String img) {
        this.nombre = nombre;
        this.precio = precio;
        this.img = img;
    }

}