package edu.eci.arsw.bidify.dto;
import java.math.BigDecimal;
import java.util.*;
import edu.eci.arsw.bidify.model.Producto;
import edu.eci.arsw.bidify.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubastaDto {
    private Usuario subastador;
    private Producto producto;
    private BigDecimal precioInicial;
    private boolean estado;
    private int cantidadDeOfertantes;
    private Set<Usuario> oferentes = new HashSet<>();
    private BigDecimal precioFinal;
    private Usuario ganador;
    private List<MessageDto> messageList = new ArrayList<>();

    public SubastaDto(Usuario subastador, Producto producto, BigDecimal precioInicial, boolean estado,
    int cantidadDeOfertantes){
        this.subastador = subastador;
        this.producto = producto;
        this.precioInicial = precioInicial;
        this.estado = estado;
        this.cantidadDeOfertantes = cantidadDeOfertantes;
    }
    

}
