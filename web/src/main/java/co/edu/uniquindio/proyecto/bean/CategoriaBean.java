package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

@Component
@ViewScoped
public class CategoriaBean implements Serializable {

    @Getter @Setter
    Categoria[] categorias;

    @Getter @Setter
    ArrayList <Categoria>listaCategorias =new ArrayList<Categoria>();

    @PostConstruct
    public void inicializar(){
        this.categorias=Categoria.values();
        this.listaCategorias= llenarArrayList();
    }

    public ArrayList llenarArrayList(){
        for(int i=0; i<categorias.length; i++){
            listaCategorias.add(categorias[i]);
        }
        System.out.println(listaCategorias.size());
        return listaCategorias;

    }


}