package edu.eci.arsw.bidify.dto;

import java.math.BigDecimal;

import edu.eci.arsw.bidify.model.Usuario;
import lombok.Data;
@Data
public class Puja implements Comparable<Puja> {
    private Usuario postor;
    private BigDecimal oferta;

     
    public Puja(Usuario postor, BigDecimal oferta) {
        this.postor = postor;
        this.oferta = oferta;
    }

    @Override
    public int compareTo(Puja otraPuja) {
        return this.oferta.compareTo(otraPuja.getOferta());
    }
}
