package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    //Me permite traer la informacion de una compra por el id
    Optional<Compra> findById(int id);

}