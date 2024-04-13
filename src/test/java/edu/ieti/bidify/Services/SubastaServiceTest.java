package edu.ieti.bidify.Services;


import edu.ieti.bidify.model.Producto;
import edu.ieti.bidify.model.Subasta;
import edu.ieti.bidify.repository.SubastaRepository;
import edu.ieti.bidify.service.SubastaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import edu.ieti.bidify.model.Usuario;



@ExtendWith(MockitoExtension.class)
class SubastaServiceTest {

    @Mock
    private SubastaRepository subastaRepository;

    @InjectMocks
    private SubastaService subastaService;

    @Test
    void testAddSubasta() {
        Producto producto = new Producto(1, "Producto 1", 10.0f, "imagen.jpg");
        Usuario usuario = new Usuario(1, "usuario1", "Usuario 1", "usuario1@example.com", "password", new ArrayList<>());
        Subasta subasta = new Subasta(usuario, producto, BigDecimal.valueOf(100.0), true, 0);
        when(subastaRepository.save(any(Subasta.class))).thenReturn(subasta);

        Subasta result = subastaService.addSubasta(subasta);

        assertNotNull(result);
        assertEquals(subasta, result);
    }

    @Test
    void testFindAllSubastas() {
        Producto producto1 = new Producto(1, "Producto 1", 10.0f, "imagen.jpg");
        Producto producto2 = new Producto(2, "Producto 2", 20.0f, "imagen.jpg");
        Usuario usuario1 = new Usuario(1, "usuario1", "Usuario 1", "usuario1@example.com", "password", new ArrayList<>());
        Usuario usuario2 = new Usuario(2, "usuario2", "Usuario 2", "usuario2@example.com", "password", new ArrayList<>());
        List<Subasta> subastas = Arrays.asList(
                new Subasta(usuario1, producto1, BigDecimal.valueOf(100.0), true, 0),
                new Subasta(usuario2, producto2, BigDecimal.valueOf(200.0), true, 0)
        );
        when(subastaRepository.findAll()).thenReturn(subastas);

        List<Subasta> result = subastaService.findAllSubastas();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(subastas, result);
    }

    @Test
    void testGetSubastaById() {
        int id = 1;
        Producto producto = new Producto(1, "Producto 1", 10.0f, "imagen.jpg");
        Usuario usuario = new Usuario(1, "usuario1", "Usuario 1", "usuario1@example.com", "password", new ArrayList<>());
        Subasta subasta = new Subasta(usuario, producto, BigDecimal.valueOf(100.0), true, 0);
        when(subastaRepository.findById(id)).thenReturn(Optional.of(subasta));

        Optional<Subasta> result = subastaService.getSubastaById(id);

        assertTrue(result.isPresent());
        assertEquals(subasta, result.get());
    }

    @Test
    void testGetSubastaByProducto() {
        // Arrange
        Producto producto = new Producto(1, "Producto 1", 10.0f, "imagen.jpg");
        Usuario usuario = new Usuario(1, "usuario1", "Usuario 1", "usuario1@example.com", "password", new ArrayList<>());
        Subasta subasta = new Subasta(usuario, producto, BigDecimal.valueOf(100.0), true, 0);
        when(subastaRepository.findByProducto(producto)).thenReturn(Optional.of(subasta));

        Optional<Subasta> result = subastaService.getSubastaByProducto(producto);

        assertTrue(result.isPresent());
        assertEquals(subasta, result.get());
    }
}