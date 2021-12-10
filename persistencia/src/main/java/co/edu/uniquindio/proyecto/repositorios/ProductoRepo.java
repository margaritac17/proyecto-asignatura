package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    Page<Producto> findAll(Pageable paginador);

    //Consulta que obtiene el nombre del vendedor dado el codigo de un Producto
    @Query("select p.vendedor.nombre from Producto p where p.codigo= :id")
    String obtenerNombreVendedor(Integer id);

    @Query("select p from Producto p where p.nombre like concat('%', :nombre,'%') ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select p from Producto p where :categoria member of p.categorias")
    List<Producto> listarPorCategoria(Categoria categoria);

    @Query("select avg(co.calificacion) as promedioCalificaciones from Producto p  join p.comentarios co  where p.codigo= :codigoProducto")
    Integer calificacionPromedio(Integer codigoProducto);

    @Query("select p from Producto p where p.vendedor.codigo = :codigoUsuario")
    List<Producto> listaProductosUsuario(String codigoUsuario);

    @Query("select p from Producto p where p.precio> :precio1 and  p.precio< :precio2")
    List<Producto> listarProductosRangoPrecio(Float precio1, Float precio2);

    @Query("select p from Producto p where p.ciudad.nombreCiudad = :ciudad")
    List<Producto> listaProductosCiudad(String ciudad);

    @Query("select p from Producto p where p.unidades> :unidad1 and  p.unidades< :unidad2")
    List<Producto> listaProductosUnidades(Integer unidad1, Integer unidad2);

    @Query("select p from Producto p where p.descuento > :descuento")
    List<Producto> listaProductosDescuento(Float descuento);

    @Query("select count(p) from Producto p where p.fecha_limite between :date1 AND :date2")
    Integer cantidadProductosPorMes(LocalDate date1, LocalDate date2);

    @Query("select count(c) from Compra c where c.medio_pago= :medioo")
    Integer cantidadMedioPago(String medioo);
}
