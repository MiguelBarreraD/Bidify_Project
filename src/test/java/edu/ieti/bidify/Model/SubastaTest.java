package edu.ieti.bidify.Model;

import edu.ieti.bidify.dto.MessageDto;
import edu.ieti.bidify.model.Producto;
import edu.ieti.bidify.model.Subasta;
import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.security.enums.RolEnum;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SubastaTest {

    @Test
    void testConstructor() {
        Usuario subastador = new Usuario(1, "subastador", "NombreSubastador", "subastador@example.com", "password", Arrays.asList(RolEnum.ADMIN));
        Producto producto = new Producto();
        BigDecimal precioInicial = BigDecimal.valueOf(100);
        boolean estado = true;
        int cantidadDeOfertantes = 0;

        Subasta subasta = new Subasta(subastador, producto, precioInicial, estado, cantidadDeOfertantes);

        assertNotNull(subasta);
        assertEquals(subastador, subasta.getSubastador());
        assertEquals(producto, subasta.getProducto());
        assertEquals(precioInicial, subasta.getPrecioInicial());
        assertEquals(estado, subasta.isEstado());
        assertEquals(cantidadDeOfertantes, subasta.getCantidadDeOfertantes());
        assertNotNull(subasta.getMessageList());
        assertTrue(subasta.getMessageList().isEmpty());
        assertEquals(precioInicial, subasta.getPrecioFinal());
    }

    @Test
    void testAddMessage() {
        Subasta subasta = new Subasta();
        MessageDto message = new MessageDto();

        subasta.addMessage(message);

        assertFalse(subasta.getMessageList().isEmpty());
        assertTrue(subasta.getMessageList().contains(message));
    }

}
