package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MensajeRepo  extends JpaRepository<Mensaje, Integer> {

    //Obtiene el Mensaje por su codigo
    Optional<Mensaje> findByCodigo(int codigo);

}
