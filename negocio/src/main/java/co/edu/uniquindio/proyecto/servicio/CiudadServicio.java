package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import java.util.List;

public interface CiudadServicio {

    List<Ciudad> listarCiudades();

    Ciudad obtenerCiudad(Integer id) throws Exception;
}
