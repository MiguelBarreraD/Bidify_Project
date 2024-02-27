package edu.eci.arsw.bidify.model;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import edu.eci.arsw.bidify.dto.MessageDto;

import lombok.Data;

@Data

@Entity
public class Subasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @OneToOne 
    private Usuario subastador;
    @OneToOne
    private Producto producto;
    @NotNull
    private BigDecimal precioInicial;
    @NotNull
    private volatile boolean estado;
    @NotNull
    private int cantidadDeOfertantes;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subasta_usuario", joinColumns = @JoinColumn(name = "subasta_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> oferentes = new HashSet<>();
    private BigDecimal precioFinal;
    @OneToOne
    private Usuario ganador;
    @ElementCollection
    @OrderColumn
    @Fetch(FetchMode.JOIN)
    private List<MessageDto> messageList;

    public Subasta(){
        this.messageList = new ArrayList<>();
    }
    public Subasta(Usuario subastador, Producto producto, BigDecimal precioInicial, boolean estado,
                   int cantidadDeOfertantes){
        this.subastador = subastador;
        this.producto = producto;
        this.precioInicial = precioInicial;
        this.estado = estado;
        this.cantidadDeOfertantes = cantidadDeOfertantes;
        this.messageList = new ArrayList<>();
        this.precioFinal = precioInicial;
        
    }
    public void addMessage(MessageDto message) {
        messageList.add(message);
    }
    public synchronized boolean isEstado() {
        return estado;
    }

    public synchronized void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void añadirOferente(Usuario oferente) {
        oferentes.add(oferente);
        cantidadDeOfertantes = oferentes.size();
    }
}
