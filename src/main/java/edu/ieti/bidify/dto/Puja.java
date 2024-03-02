package edu.ieti.bidify.dto;

import edu.ieti.bidify.model.Usuario;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Clase que representa una puja realizada por un usuario en una subasta.
 */

@Data
public class Puja implements Comparable<Puja> {
    private Usuario postor;
    private BigDecimal oferta;

    /**
     * Constructor de la clase Puja.
     *
     * @param postor Usuario que realiza la puja.
     * @param oferta Oferta realizada.
     */
    public Puja(Usuario postor, BigDecimal oferta) {
        this.postor = postor;
        this.oferta = oferta;
    }

    /**
     * Compara esta puja con otra puja basándose en la oferta realizada.
     *
     * @param otraPuja Otra puja a comparar.
     * @return Valor negativo si esta puja es menor, valor positivo si es mayor, 0 si son iguales.
     */
    @Override
    public int compareTo(Puja otraPuja) {
        return this.oferta.compareTo(otraPuja.getOferta());
    }
}