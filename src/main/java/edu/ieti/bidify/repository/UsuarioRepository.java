package edu.ieti.bidify.repository;

import edu.ieti.bidify.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {
    Usuario findByUserNameAndPassword(String userName, String password);
    boolean existsByUserName(String nombre);
    boolean existsById(int id);
    boolean existsByEmail(String email);
    void deleteByUserName(String userName);
}
