package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra,Integer> {

    //Me permite obtener un producto que este asociado al detalle compra
    List<DetalleCompra> findByProducto(Producto producto);

    @Query("select dc from DetalleCompra dc where dc.compra.codigo= :codigoCompra")
    List<DetalleCompra> listarDetalleCompra(Integer codigoCompra);
}
