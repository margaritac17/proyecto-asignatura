package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubastaRepo extends JpaRepository<Subasta, Integer> {

    /*
    @Query("select Subasta from Subasta where Subasta.codigo = :nombre")
    List<Subasta> obtenerSubastaPorCodigo(Integer codigo);
    */
    Page<Subasta> findAllBy (Pageable paginador);
    List<Subasta> findAllByCodigo(Integer codigo);

}
