package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicio.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Scope("session")
@Component
public class SeguridadBean implements Serializable  {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailSenderService emailSenderService;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Getter @Setter
    private Categoria categoria;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Getter @Setter
    private Integer codigoCompra;

    @Autowired
    private DetalleCompraServicio detalleCompraServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private ArrayList<Producto> misProductos;

    @Getter @Setter
    private ArrayList<Compra> misCompras;

    @Getter @Setter
    private List<DetalleCompra> misDetallesCompra;

    @Getter @Setter
    private List<Producto> productosFavoritos;

    @Getter @Setter
    private List<Producto> productosCategoria;


    @Getter @Setter
    private Float subtotal;

    @Getter @Setter
    private Float total;

    @Getter @Setter
    private boolean  estaEnFavoritos;

    @PostConstruct
    public void inicializar(){
        this.subtotal= 0F;
        this.codigoCompra=0;
        this.productosCarrito= new ArrayList<>();
        this.misProductos= new ArrayList<>();
        this.misCompras= new ArrayList<>();
        this.misDetallesCompra= new ArrayList<>();
        this.productosFavoritos= new ArrayList<>();
    }

    public String  iniciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                this.usuarioSesion= usuarioServicio.iniciarSesion(email, password);
                autenticado=true;
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }
        return null;
    }

    public  String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarAlCarrito(Integer id, Float precio, String nombre, String imagen){
        ProductoCarrito pc= new ProductoCarrito(id, nombre, imagen, precio, 1);

        if(!productosCarrito.contains(pc)){

            productosCarrito.add(pc);
            subtotal+=precio;
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }
    }

    public void eliminarDelCarrito(int indice){
        subtotal -= productosCarrito.get(indice).getPrecio();
        productosCarrito.remove(indice);

    }

    public void actualizarSubtotal(){
        subtotal= 0F;
        for(ProductoCarrito p:productosCarrito){
            subtotal+=p.getPrecio()*p.getUnidades();
        }
    }

    public void calculartotal(){
        total= 0F;
        for(DetalleCompra dc:misDetallesCompra){
            total+=dc.getPrecio_producto()*dc.getUnidades();
        }
    }

    public void comprar(){
        if(usuarioSesion!=null && !productosCarrito.isEmpty()){
            try {
                productoServicio.comprarProductos(usuarioSesion, productosCarrito, "Efectivo");
                triggerMail();
                productosCarrito.clear();
                subtotal=0F;

                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada Satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }
    }

    public void almacenarMisProductos(){
        if(usuarioSesion!=null){
            try {
                this.misProductos= (ArrayList<Producto>) productoServicio.listarProductos(usuarioSesion);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public String irADetalle(String id){
        return "detalle_mi_producto?faces-redirect=true&amp;producto="+id;
    }

    public String irADetalleCompra(String id){
        this.codigoCompra= Integer.parseInt(id);
        return "detalle_mi_compra?faces-redirect=true&amp;compra="+id;
    }




    public void listarMisCompras(){
        if(usuarioSesion!=null){
            try {
                this.misCompras= (ArrayList<Compra>) compraServicio.listarComprasUsuario(usuarioSesion.getCodigo());
                misCompras.forEach(System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void listarMisDetallesCompras(){
        if(usuarioSesion!=null){
            try {
                this.misDetallesCompra= detalleCompraServicio.listarDetallesCompra(codigoCompra);
                misDetallesCompra.forEach(System.out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //SEBAS
    public int productosEnVenta(){
        return misProductos.size();
    }

    public void agregarAlFavoritos(Producto producto){
        try{
            if(usuarioSesion!=null){
                if(!productosFavoritos.contains(producto)) {
                    productosFavoritos.add(producto);
                    FacesMessage fmm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado a favoritos");
                    FacesContext.getCurrentInstance().addMessage("add-favorito", fmm);
                }else{
                    productosFavoritos.remove(producto);
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto eliminado de favoritos");
                    FacesContext.getCurrentInstance().addMessage("add-favorito", fm);
                }
            }
        } catch (Exception e) {
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("add-favorito", fm);
        }

    }


    public boolean encontrarProducto(Producto producto){
        estaEnFavoritos= false;
        try {
            if(productosFavoritos!=null){
                for(int i=0; i<productosFavoritos.size() && estaEnFavoritos==false ;i++){
                    if(producto.getCodigo()==productosFavoritos.get(i).getCodigo()){
                        estaEnFavoritos=true;
                    }
                }
                return estaEnFavoritos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaEnFavoritos;
    }

    public boolean estaEnFavoritosYLogueado(Producto producto){
        if(autenticado){
            if(encontrarProducto(producto)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public String getMensaje(Producto producto) {
        if(autenticado){
            if(encontrarProducto(producto)){
                return "Eliminar de Favoritos";
            }else{
                return "Añadir a Favoritos";
            }
        }
        return "";

    }

    //diego

    public String irADetalleCategoria(Categoria nombreCategoria){
        this.categoria = nombreCategoria;
        //this.codigoCompra= Integer.parseInt(nombreCategoria);
        return "categoria_producto?faces-redirect=true&amp;categoria="+nombreCategoria;
    }

    public String direccionarReporte(){
        return "reportes?faces-redirect=true&amp";

    }

    public void listarProductoCategoria(){
        this.productosCategoria = productoServicio.listarProductos(categoria);
        productosCategoria.forEach(System.out::println);
    }

    public void triggerMail(){
        System.out.println("1");
        String mensaje = "<h1>UNISHOP</h1>";
        mensaje += "<h2>Hola, " + usuarioSesion.getNombre() + "</h2>"
                + "\n\nConfirmacion de Compra\n"
                + "\n<h4>DETALLES DE LA COMPRA</h4>"
                + "<P>" + productosCarritoMensaje() + "</P>"
                + "<h2>Total compra: $" + subtotal
                + "</h2></br></br>Atentamente, "
                + "<h3>UNISHOP</h3>";
        try {
            emailSenderService.sendSimpleEmail("margaritacente2001.com", mensaje,
                    "Compra Unishop");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String  productosCarritoMensaje(){
        String mensaje= "" ;
        for (int i=0; i<productosCarrito.size();i++){
            mensaje= mensaje.concat(productosCarrito.get(i).getNombre() ) + "    " ;
            mensaje= mensaje.concat(String.valueOf(productosCarrito.get(i).getPrecio() ) )+ "    " ;
            mensaje= mensaje.concat(String.valueOf(productosCarrito.get(i).getUnidades() ) )+ "    " ;
            mensaje.concat(" \t" );
        }
        return mensaje;
    }

}
