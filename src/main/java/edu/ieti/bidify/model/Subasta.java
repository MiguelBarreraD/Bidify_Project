package edu.ieti.bidify.model;
import edu.ieti.bidify.dto.MessageDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Clase que representa una subasta en el sistema de subastas.
 */
@Data
@Document(collection = "subastas")
public class Subasta {
    @Id
    private int id;
    @DBRef
    private Usuario subastador;
    @DBRef
    private Producto producto;
    private BigDecimal precioInicial;
    private boolean estado;
    private int cantidadDeOfertantes;
    @DBRef
    private Set<Usuario> oferentes;
    private BigDecimal precioFinal;
    @DBRef
    private Usuario ganador;
    private List<MessageDto> messageList;
    /**
     * Constructor por defecto de la clase Subasta.
     * Inicializa la lista de mensajes asociados a la subasta.
     */
    public Subasta() {
        this.messageList = new ArrayList<>();
    }
    /**
     * Constructor de la clase Subasta.
     *
     * @param subastador Usuario que inicia la subasta.
     * @param producto Producto que se subasta.
     * @param precioInicial Precio inicial de la subasta.
     * @param estado Estado actual de la subasta.
     * @param cantidadDeOfertantes Cantidad de ofertantes en la subasta.
     */
    public Subasta(Usuario subastador, Producto producto, BigDecimal precioInicial, boolean estado,
                   int cantidadDeOfertantes) {
        this.subastador = subastador;
        this.producto = producto;
        this.precioInicial = precioInicial;
        this.estado = estado;
        this.cantidadDeOfertantes = cantidadDeOfertantes;
        this.messageList = new ArrayList<>();
        this.precioFinal = precioInicial;
    }
    /**
     * Agrega un mensaje a la lista de mensajes asociados a la subasta.
     *
     * @param message Mensaje a agregar.
     */
    public void addMessage(MessageDto message) {
        messageList.add(message);
    }
}