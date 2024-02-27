package edu.eci.arsw.bidify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.eci.arsw.bidify.controller.ProductoController;
import edu.eci.arsw.bidify.dto.Mensaje;
import edu.eci.arsw.bidify.dto.ProductoDto;
import edu.eci.arsw.bidify.model.Producto;
import edu.eci.arsw.bidify.service.ProductoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductoControllerTest {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoController productoController;

    @Test
    public void testGetByIdNotFound() {
        // Arrange
        int productId = 1;
        // Act
        ResponseEntity<Producto> response = productoController.getById(productId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetByNombre() {
        // Arrange
        String productName = "Test";
        Producto producto1 = new Producto("Test", (float) 600000, "https://phantom-expansion.unidadeditorial.es/6239da431613d30a7ade440a4719e3db/crop/0x378/1074x982/resize/828/f/jpg/assets/multimedia/imagenes/2022/03/21/16478732471407.jpg");
        productoService.save(producto1);
        // Act
        ResponseEntity<Producto> response = productoController.getByNombre(productName);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto1, response.getBody());
    }

        /**
     * 
    @Test
    public void testCreateSuccess() {
        // Arrange
        ProductoDto productoDto = new ProductoDto("Producto1", (float) 20.0, "imagen.jpg");
        // Act
        ResponseEntity<?> response = productoController.create(productoDto);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("producto creado", ((Mensaje) response.getBody()).getMensaje());
    }
    @Test
    public void testCreateBlankNombre() {
        // Arrange
        ProductoDto productoDto = new ProductoDto("", (float) 20.0, "imagen.jpg");
        // Act
        ResponseEntity<?> response = productoController.create(productoDto);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("el nombre es obligatorio", ((Mensaje) response.getBody()).getMensaje());
    }
    */

    @Test
    public void testUpdateSuccess() {
        // Arrange
        int productId = 1;
        ProductoDto productoDto = new ProductoDto("NuevoNombre", (float) 30.0, "nueva_imagen.jpg");
        Producto existingProduct = new Producto("Test", (float) 600000, "https://phantom-expansion.unidadeditorial.es/6239da431613d30a7ade440a4719e3db/crop/0x378/1074x982/resize/828/f/jpg/assets/multimedia/imagenes/2022/03/21/16478732471407.jpg");
        productoService.save(existingProduct);
        // Act
        ResponseEntity<?> response = productoController.update(productId, productoDto);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("producto actualizado", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testUpdateExistingNombre() {
        // Arrange
        int productId = 1;
        ProductoDto productoDto = new ProductoDto("NuevoNombre", (float) 30.0, "nueva_imagen.jpg");
        Producto existingProduct = new Producto("Test", (float) 600000, "https://phantom-expansion.unidadeditorial.es/6239da431613d30a7ade440a4719e3db/crop/0x378/1074x982/resize/828/f/jpg/assets/multimedia/imagenes/2022/03/21/16478732471407.jpg");
        productoService.save(existingProduct);
        // Act
        ResponseEntity<?> response = productoController.update(productId, productoDto);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("no existe", ((Mensaje) response.getBody()).getMensaje());
    }

    @Test
    public void testDeleteSuccess() {
        // Arrange
        int productId = 1;
        Producto existingProduct = new Producto("Test", (float) 600000, "https://phantom-expansion.unidadeditorial.es/6239da431613d30a7ade440a4719e3db/crop/0x378/1074x982/resize/828/f/jpg/assets/multimedia/imagenes/2022/03/21/16478732471407.jpg");
        productoService.save(existingProduct);
        // Act
        ResponseEntity<?> response = productoController.delete(productId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("producto eliminado", ((Mensaje) response.getBody()).getMensaje());
    }
}
