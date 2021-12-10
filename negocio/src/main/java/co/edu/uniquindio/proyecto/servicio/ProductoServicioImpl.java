package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;
    private final CompraRepo compraRepo;
    private final DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private EmailSenderService service;

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
    public Producto actualizarProducto(Producto p) throws Exception {
        Optional<Producto> buscado= productoRepo.findById(p.getCodigo());
        if(buscado.isEmpty()){
            throw new Exception("El producto no existe");
        }
        return productoRepo.save(p);
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
    public List<Producto> listarProductosCiudad(String ciudad) {
        return productoRepo.listaProductosCiudad(ciudad);
    }

    @Override
    public List<Producto> listarProductosRangoUnidad(Integer unidad1, Integer unidad2) {
        return productoRepo.listaProductosUnidades(unidad1, unidad2);
    }

    @Override
    public List<Producto> listarProductosDescuento(Float descuento) {
        return productoRepo.listaProductosDescuento(descuento);
    }

    @Override
    public Integer cantidadProductosPorMes(Integer mes) {
        LocalDate date1 = LocalDate.of(2021, mes, 01);
        LocalDate date2 = LocalDate.of(2021, mes, 31);

        return productoRepo.cantidadProductosPorMes(date1,date2);
    }

    @Override
    public Integer medioPago(String medio) {
        return productoRepo.cantidadMedioPago(medio);
    }


    @Override
    public List<Producto> listarProductos(Usuario usuario) throws Exception {
        return productoRepo.listaProductosUsuario(usuario.getCodigo());
    }

    @Override
    public List<Producto> listarProductosRangoPrecio(Float precio1, Float precio2) throws Exception {
        return productoRepo.listarProductosRangoPrecio(precio1, precio2);
    }

}
