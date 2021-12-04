package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;
    private final CompraRepo compraRepo;
    private final DetalleCompraRepo detalleCompraRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo, DetalleCompraRepo detalleCompraRepo, CompraRepo compraRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo= comentarioRepo;
        this.compraRepo=compraRepo;
        this.detalleCompraRepo= detalleCompraRepo;
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try{
            return productoRepo.save(p);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void actualizarProducto(Producto producto) throws Exception {

    }

    @Override
    public void eliminarProducto(Integer codigoP) throws Exception {
        Optional<Producto> producto= productoRepo.findById(codigoP);

        if(producto.isEmpty()){
            throw  new Exception("El codigo del producto no existe");
        }
        productoRepo.deleteById(codigoP);

    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(() -> new Exception("El codigo del producto no es valido"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFecha(LocalDateTime.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, String[] filtros) {
        return productoRepo.buscarProductoNombre(nombreProducto);
    }


    @Override
    public List<Categoria> listarCategorias() {
        return Arrays.asList(Categoria.values());
    }

    @Override
    public Categoria obtenerCategoria(String categoria) throws Exception {
        return Categoria.valueOf(categoria);
    }

    @Override
    public Integer calificacionPromedio(Integer codigoProducto) {
        return productoRepo.calificacionPromedio(codigoProducto);
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception  {
       try {
           Compra c= new Compra();
           c.setFecha_compra(LocalDateTime.now(ZoneId.of("America/Bogota")));
           c.setUsuario(usuario);
           c.setMedio_pago(medioPago);
           Compra compraGuardada =compraRepo.save(c);

           DetalleCompra dc;
           for(ProductoCarrito p:productos){
               dc= new DetalleCompra();
               dc.setCompra(compraGuardada);
               dc.setPrecio_producto(p.getPrecio());
               dc.setUnidades(p.getUnidades());
               dc.setProducto(productoRepo.findById(p.getId()).get());
               //VERIFICAR QUE LAS UNIDADES DEL PRODUCTO SI ESTEN DISPONIBLES
               detalleCompraRepo.save(dc);
           }
           return compraGuardada;
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
    }


    @Override
    public List<Producto> listarProductos(Usuario usuario) throws Exception {
        System.out.println(usuario.getCodigo());
        return productoRepo.listaProductosUsuario(usuario.getCodigo());
    }
}
