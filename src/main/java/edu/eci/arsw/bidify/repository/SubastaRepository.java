package edu.eci.arsw.bidify.repository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.bidify.model.Producto;
import edu.eci.arsw.bidify.model.Subasta;
import edu.eci.arsw.bidify.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface SubastaRepository extends JpaRepository<Subasta, Integer>{
    Optional<Subasta> findBySubastador(Usuario subastador);
    Optional<Subasta> findByProducto(Producto producto);
    Optional<Subasta> findById(int id); 
}
