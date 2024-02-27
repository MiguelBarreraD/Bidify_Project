package edu.eci.arsw.bidify;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import edu.eci.arsw.bidify.model.Producto;
import edu.eci.arsw.bidify.repository.ProductoRepository;
import edu.eci.arsw.bidify.service.ProductoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductoServiceTest{

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    public void testList() {
        // Arrange
        List<Producto> productos = Arrays.asList(new Producto(), new Producto());
        when(productoRepository.findAll()).thenReturn(productos);
        // Act
        List<Producto> result = productoService.list();
        // Assert
        assertEquals(productos, result);
    }

    @Test
    public void testGetOne() {
        // Arrange
        int productId = 1;
        Producto producto = new Producto();
        when(productoRepository.findById(productId)).thenReturn(Optional.of(producto));
        // Act
        Optional<Producto> result = productoService.getOne(productId);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(producto, result.get());
    }

    @Test
    public void testExistsById() {
        // Arrange
        int productId = 1;
        when(productoRepository.existsById(productId)).thenReturn(true);
        // Act
        boolean result = productoService.existsById(productId);
        // Assert
        assertTrue(result);
    }

    @Test
    public void testExistsByNombre() {
        // Arrange
        String nombre = "Producto1";
        when(productoRepository.existsByNombre(nombre)).thenReturn(true);
        // Act
        boolean result = productoService.existsByNombre(nombre);
        // Assert
        assertTrue(result);
    }
}

