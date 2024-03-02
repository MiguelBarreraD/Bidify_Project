package edu.ieti.bidify.service;

import edu.ieti.bidify.dto.ProductoDto;
import edu.ieti.bidify.model.Producto;
import edu.ieti.bidify.repository.ProductoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Servicio que gestiona las operaciones relacionadas con los productos en el sistema de subastas.
 */
@Service
@Data
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    /**
     * Obtiene una lista de todos los productos en el sistema.
     *
     * @return Lista de productos.
     */
    public List<Producto> list(){
        return productoRepository.findAll();
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id ID del producto.
     * @return Producto encontrado, o un valor opcional vacío si no se encuentra.
     */
    public Optional<Producto> getOne(int id){
        return productoRepository.findById(id);
    }

    /**
     * Obtiene un producto por su nombre.
     *
     * @param nombre Nombre del producto.
     * @return Producto encontrado, o un valor opcional vacío si no se encuentra.
     */
    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param producto Objeto ProductoDto con la información del nuevo producto.
     * @return Producto guardado.
     */
    public Producto save(ProductoDto producto){
        int id = autoIncrement();
        Producto productoNuevo = new Producto(id, producto.getNombre(), producto.getPrecio(), producto.getImg());
        return productoRepository.save(productoNuevo);

    }

    /**
     * Incrementa el ID de los productos automáticamente.
     *
     * @return Nuevo ID.
     */
    private int autoIncrement(){
        List<Producto> productos = productoRepository.findAll();
        return productos.isEmpty()? 1 : productos.stream().max(Comparator.comparing(Producto::getId)).get().getId() + 1;
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id ID del producto a eliminar.
     */
    public void delete(int id){
        productoRepository.deleteById(id);
    }

    /**
     * Verifica si existe un producto por su ID.
     *
     * @param id ID del producto.
     * @return true si existe, false de lo contrario.
     */
    public boolean existsById(int id){
        return productoRepository.existsById(id);
    }

    /**
     * Verifica si existe un producto por su nombre.
     *
     * @param nombre Nombre del producto.
     * @return true si existe, false de lo contrario.
     */
    public boolean existsByNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }

    /**
     * Actualiza la información de un producto existente.
     *
     * @param id ID del producto a actualizar.
     * @param dto Objeto ProductoDto con la nueva información del producto.
     * @return Producto actualizado.
     */
    public Producto update(int id, ProductoDto dto){
        Producto producto = productoRepository.findById(id).get();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setImg(dto.getImg());
        return productoRepository.save(producto);
    }
}
