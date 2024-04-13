package edu.ieti.bidify.Services;


import edu.ieti.bidify.dto.ProductoDto;
import edu.ieti.bidify.model.Producto;
import edu.ieti.bidify.repository.ProductoRepository;
import edu.ieti.bidify.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void testList() {
        List<Producto> productos = Arrays.asList(
                new Producto(1, "Producto 1", 10.0f, "imagen1.jpg"),
                new Producto(2, "Producto 2", 20.0f, "imagen2.jpg")
        );
        
        when(productoRepository.findAll()).thenReturn(productos);


        List<Producto> result = productoService.list();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(productos, result);
    }

    @Test
    void testGetOne() {
        int id = 1;
        Producto producto = new Producto(id, "Producto 1", 10.0f, "imagen.jpg");
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        Optional<Producto> result = productoService.getOne(id);

        assertTrue(result.isPresent());
        assertEquals(producto, result.get());
    }

    @Test
    void testGetByNombre() {
        String nombre = "Producto 1";
        Producto producto = new Producto(1, nombre, 10.0f, "imagen.jpg");
        when(productoRepository.findByNombre(nombre)).thenReturn(Optional.of(producto));
        Optional<Producto> result = productoService.getByNombre(nombre);

        assertTrue(result.isPresent());
        assertEquals(producto, result.get());
    }

    @Test
    void testSave() {
        // Arrange
        ProductoDto productoDto = new ProductoDto("Producto 1", 10.0f, "imagen.jpg");
        Producto producto = new Producto(1, "Producto 1", 10.0f, "imagen.jpg");
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        Producto result = productoService.save(productoDto);

        assertNotNull(result);
        assertEquals(producto, result);
    }

    @Test
    void testDelete() {
        int id = 1;
        productoService.delete(id);
        verify(productoRepository, times(1)).deleteById(id);
    }

    @Test
    void testExistsById() {
        int id = 1;
        when(productoRepository.existsById(id)).thenReturn(true);
        boolean result = productoService.existsById(id);

        assertTrue(result);
    }

    @Test
    void testExistsByNombre() {
        String nombre = "Producto 1";
        when(productoRepository.existsByNombre(nombre)).thenReturn(true);
        boolean result = productoService.existsByNombre(nombre);

        assertTrue(result);
    }

    @Test
    void testUpdate() {
        int id = 1;
        ProductoDto productoDto = new ProductoDto("Producto Actualizado", 15.0f, "imagen_actualizada.jpg");
        Producto producto = new Producto(id, "Producto 1", 10.0f, "imagen.jpg");
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        Producto result = productoService.update(id, productoDto);
        assertNotNull(result);

        assertEquals("Producto Actualizado", result.getNombre());
        assertEquals(15.0, result.getPrecio());
        assertEquals("imagen_actualizada.jpg", result.getImg());
    }

    @Test
    void testAutoIncrement() {
        List<Producto> productos = Arrays.asList(
                new Producto(1, "Producto 1", 10.0f, "imagen1.jpg"),
                new Producto(2, "Producto 2", 20.0f, "imagen2.jpg"),
                new Producto(3, "Producto 3", 30.0f, "imagen3.jpg")
        );
        when(productoRepository.findAll()).thenReturn(productos);
        int newId = productoService.autoIncrement();
        assertEquals(4, newId);
    }
}