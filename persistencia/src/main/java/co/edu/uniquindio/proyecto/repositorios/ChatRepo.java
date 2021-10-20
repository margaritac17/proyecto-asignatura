package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepo  extends JpaRepository<Chat, Integer> {

    //Consulta que me permite obtener el chat por medio del codigo
    Optional<Chat> findByCodigo(int codigo);

}
