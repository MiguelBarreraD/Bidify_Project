package edu.ieti.bidify.Controller;

import edu.ieti.bidify.controller.ProductoController;
import edu.ieti.bidify.dto.Mensaje;
import edu.ieti.bidify.dto.ProductoDto;
import edu.ieti.bidify.model.Producto;
import edu.ieti.bidify.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto());
        productos.add(new Producto());

        when(productoService.list()).thenReturn(productos);

        ResponseEntity<List<Producto>> response = productoController.list();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productos, response.getBody());
        verify(productoService, times(1)).list();
    }

    @Test
    void testGetById() {
        int id = 1;
        Producto producto = new Producto();

        when(productoService.existsById(id)).thenReturn(true);
        when(productoService.getOne(id)).thenReturn(Optional.of(producto));

        ResponseEntity<Producto> response = productoController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, response.getBody());
        verify(productoService, times(1)).existsById(id);
        verify(productoService, times(1)).getOne(id);
    }

    @Test
    void testGetByIdNotFound() {
        int id = 1;

        when(productoService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = productoController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, times(1)).existsById(id);
        verify(productoService, never()).getOne(id);
    }

    @Test
    void testGetByNombre() {
        String nombre = "Producto 1";
        Producto producto = new Producto();

        when(productoService.existsByNombre(nombre)).thenReturn(true);
        when(productoService.getByNombre(nombre)).thenReturn(Optional.of(producto));

        ResponseEntity<Producto> response = productoController.getByNombre(nombre);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, response.getBody());
        verify(productoService, times(1)).existsByNombre(nombre);
        verify(productoService, times(1)).getByNombre(nombre);
    }

    @Test
    void testGetByNombreNotFound() {
        String nombre = "Producto 1";

        when(productoService.existsByNombre(nombre)).thenReturn(false);

        ResponseEntity<?> response = productoController.getByNombre(nombre);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, times(1)).existsByNombre(nombre);
        verify(productoService, never()).getByNombre(nombre);
    }


    @Test
    void testCreateInvalidNombre() {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("");
        productoDto.setPrecio(100.0f);

        ResponseEntity<?> response = productoController.create(productoDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, never()).existsByNombre(productoDto.getNombre());
        verify(productoService, never()).save(productoDto);
    }

    @Test
    void testCreateInvalidPrecio() {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("Producto 1");
        productoDto.setPrecio(0.0f);

        ResponseEntity<?> response = productoController.create(productoDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, never()).existsByNombre(productoDto.getNombre());
        verify(productoService, never()).save(productoDto);
    }

    @Test
    void testCreateDuplicateName() {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("Producto 1");
        productoDto.setPrecio(100.0f);

        when(productoService.existsByNombre(productoDto.getNombre())).thenReturn(true);

        ResponseEntity<?> response = productoController.create(productoDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, times(1)).existsByNombre(productoDto.getNombre());
        verify(productoService, never()).save(productoDto);
    }



    @Test
    void testUpdateNotFound() {
        int id = 1;
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("Producto 1");
        productoDto.setPrecio(100.0f);

        when(productoService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = productoController.update(id, productoDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, times(1)).existsById(id);
        verify(productoService, never()).existsByNombre(productoDto.getNombre());
        verify(productoService, never()).save(productoDto);
    }

    @Test
    void testUpdateDuplicateName() {
        int id = 1;
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("Producto 1");
        productoDto.setPrecio(100.0f);

        when(productoService.existsById(id)).thenReturn(true);
        when(productoService.existsByNombre(productoDto.getNombre())).thenReturn(true);
        when(productoService.getByNombre(productoDto.getNombre()))
                .thenReturn(Optional.of(new Producto(2, "Producto 2", 200.0f, "img")));

        ResponseEntity<?> response = productoController.update(id, productoDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, times(1)).existsById(id);
        verify(productoService, times(1)).existsByNombre(productoDto.getNombre());
        verify(productoService, never()).save(productoDto);
    }




    @Test
    void testDelete() {
        int id = 1;

        when(productoService.existsById(id)).thenReturn(true);
        doNothing().when(productoService).delete(id);

        ResponseEntity<?> response = productoController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, times(1)).existsById(id);
        verify(productoService, times(1)).delete(id);
    }

    @Test
    void testDeleteNotFound() {
        int id = 1;

        when(productoService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = productoController.delete(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof Mensaje);
        verify(productoService, times(1)).existsById(id);
        verify(productoService, never()).delete(id);
    }
}