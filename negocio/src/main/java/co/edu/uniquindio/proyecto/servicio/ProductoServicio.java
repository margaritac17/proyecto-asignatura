package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;

import java.util.ArrayList;
import java.util.List;

public interface ProductoServicio {


    Producto publicarProducto(Producto p) throws Exception;

    Producto actualizarProducto(Producto producto) throws Exception;

    void eliminarProducto(Integer codigoP) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    List<Producto> listarTodosProductos();

    List<Producto> listarProductosRangoPrecio(Float precio1, Float precio2) throws Exception;

     void comentarProducto(Comentario comentario) throws Exception;
    //void comentarProducto(String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void comprarProductos(Compra compra) throws Exception;

    List<Producto> buscarProducto(String nombre, String[] filtros);

    List<Producto> listarProductos(Usuario usuario) throws Exception;

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(String categoria) throws Exception;

    Integer calificacionPromedio(Integer codigoProducto);

    Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception;

    List<Producto> listarProductosCiudad(String ciudad);

    List<Producto> listarProductosRangoUnidad(Integer unidad1, Integer unidad2);

    List<Producto> listarProductosDescuento(Float descuento);

    Integer cantidadProductosPorMes(Integer mes);

    Integer medioPago(String medioPago);
}
