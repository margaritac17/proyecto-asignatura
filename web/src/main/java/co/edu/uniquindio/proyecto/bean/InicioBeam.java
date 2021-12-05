package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBeam implements Serializable {

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private List<Producto> productosCategoria;


    @Autowired
    private ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar(){
        this.productos=productoServicio.listarTodosProductos();
    }



    public String irADetalle(String id){
        return "detalle_producto?faces-redirect=true&amp;producto="+id;
    }







}

