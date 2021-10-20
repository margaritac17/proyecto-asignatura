package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepo extends JpaRepository <Ciudad,Integer> {
    //Me permite traer el nombre de la ciudad
    Optional<Ciudad> findByNombreCiudad(String nombreCiudad);

    //Consulta queobtiene una ciudad dado su nombre
    @Query("select u from Ciudad c join c.usuarios u where c.nombreCiudad = :nombre")
    List<Usuario> listarUsuarios(String nombre);
}
