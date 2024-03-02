package edu.ieti.bidify.controller;


import edu.ieti.bidify.dto.*;
import edu.ieti.bidify.model.Producto;
import edu.ieti.bidify.service.ProductoService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controlador REST que maneja las solicitudes relacionadas con los productos en el sistema.
 */
@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    /**
     * Obtiene la lista de todos los productos registrados en el sistema.
     *
     * @return Respuesta HTTP con la lista de productos.
     */
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list(){
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    /**
     * Obtiene un producto por su identificador único.
     *
     * @param id Identificador único del producto.
     * @return Respuesta HTTP con el producto encontrado, o NOT_FOUND si no existe.
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    /**
     * Obtiene un producto por su nombre.
     *
     * @param nombre Nombre del producto.
     * @return Respuesta HTTP con el producto encontrado, o NOT_FOUND si no existe.
     */
    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre){
        if(!productoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param productoDto Datos del producto a crear.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio() <= 0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(productoService.existsByNombre(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        productoService.save(productoDto);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    /**
     * Actualiza un producto existente en el sistema.
     *
     * @param id          Identificador único del producto a actualizar.
     * @param productoDto Nuevos datos del producto.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductoDto productoDto){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()<=0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        productoService.save(productoDto);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    /**
     * Elimina un producto del sistema.
     *
     * @param id Identificador único del producto a eliminar.
     * @return Respuesta HTTP con un mensaje de éxito o error.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
}