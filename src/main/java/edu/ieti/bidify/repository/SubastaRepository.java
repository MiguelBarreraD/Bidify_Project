package edu.ieti.bidify.repository;
import edu.ieti.bidify.model.Producto;
import edu.ieti.bidify.model.Subasta;
import edu.ieti.bidify.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SubastaRepository extends MongoRepository<Subasta, Integer> {
    Optional<Subasta> findBySubastador(Usuario subastador);
    Optional<Subasta> findByProducto(Producto producto);
    Optional<Subasta> findById(int id);
}
