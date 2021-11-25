package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ProductoServicio {


    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto producto) throws Exception;

    void eliminarProducto(Integer codigoP) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    List<Producto> listarTodosProductos();

     void comentarProducto(Comentario comentario) throws Exception;
    //void comentarProducto(String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void comprarProductos(Compra compra) throws Exception;

    List<Producto> buscarProducto(String nombre, String[] filtros);

    List<Producto> listarProductos(String codigoUsuario) throws Exception;

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(String categoria) throws Exception;

    Integer calificacionPromedio(Integer codigoProducto);
}
