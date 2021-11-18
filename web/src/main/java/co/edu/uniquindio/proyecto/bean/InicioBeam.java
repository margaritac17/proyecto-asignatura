package co.edu.uniquindio.proyecto.bean;

import org.springframework.stereotype.Component;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class InicioBeam implements Serializable {

    private String mensaje="Mi primera pagina en JSF ";

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
