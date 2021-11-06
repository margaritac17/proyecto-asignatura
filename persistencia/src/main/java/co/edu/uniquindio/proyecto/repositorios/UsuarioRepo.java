package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {

    //Permite realizar algunas consultas para obtener diferentes datos de la tabla usuario

    List<Usuario> findByNombreContains(String nombre);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailAndPassword(String email, String clave);

    Optional<Usuario> findByUsername(String username);

    Page<Usuario> findAll(Pageable paginador);

    @Query("select p from Usuario u, IN (u.productosFavoritos) p where u.email= :email")
    List<Producto> obtenerProductosFavoritos(String email);
}
