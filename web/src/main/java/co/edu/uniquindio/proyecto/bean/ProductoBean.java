package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.CiudadServicio;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    private ArrayList<String> imagenes;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Value("${upload.url}")
    private String urlUploads;

    @Getter @Setter
    private List<Categoria> categorias;

    @PostConstruct
    public void inicializar(){

        this.producto= new Producto();
        this.imagenes=new ArrayList<>();
        categorias= productoServicio.listarCategorias();
        ciudades = ciudadServicio.listarCiudades();
    }

    public void crearProducto(){
        try {
            //Estoy quemando el usuario vendedor de producto(se debe quitar mas adelatne)
            if(!imagenes.isEmpty()){
                Usuario usuario= usuarioServicio.obtenerUsuario("123");
                producto.setVendedor(usuario);
                producto.setImagenes(imagenes);
                producto.setFecha_limite(LocalDateTime.now().plusMonths(2));
                productoServicio.publicarProducto(producto);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El producto se creó correctamente");
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Es necesario subir al menos una imagen");
                FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
            }
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", fm);

        }
    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen= event.getFile();
        String nombreImagen= subirImagen(imagen);
        if(nombreImagen!=null){
            imagenes.add(nombreImagen);
        }

    }

    public String subirImagen(UploadedFile imagen){
        try {
            File archivo = new File(urlUploads+"/"+imagen.getFileName());
            OutputStream outputStream= new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return  imagen.getFileName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
