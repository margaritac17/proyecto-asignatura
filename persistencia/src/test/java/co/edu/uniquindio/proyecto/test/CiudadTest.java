package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    //Funcion que permite realizar la prueba unitaria para registrar una ciudad y se comprueba que no sea null
    @Test
    @Sql("classpath:data.sql")
    public void registrarCiudadTest(){

        Ciudad ciudad= Ciudad.builder().nombreCiudad("Cali").build();
        Ciudad ciudadGuardada= ciudadRepo.save(ciudad);
        Assertions.assertNotNull(ciudadGuardada);
    }

    //Funcion que permite realizar la prueba unitaria para eliminar una ciudad y comprobar que se elimino mediante el asserNull
    @Test
    @Sql("classpath:data.sql")
    public void eliminarCiudadTest(){

        Ciudad ciudad =ciudadRepo.findById(1).orElse(null);
        ciudadRepo.delete(ciudad);

        Ciudad ciudadEliminada =ciudadRepo.findById(1).orElse(null);
        Assertions.assertNull(ciudadEliminada);

    }

    //Funcion que permite realizar la prueba unitaria para actualizar una ciudad
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarCidadTest(){

        Ciudad ciudad =ciudadRepo.findById(1).orElse(null);
        ciudad.setNombreCiudad("Medellin");
        ciudadRepo.save(ciudad);

        Ciudad ciudadActualizada =ciudadRepo.findById(1).orElse(null);
        Assertions.assertEquals("Medellin", ciudadActualizada.getNombreCiudad());
    }

    //Funcion que permite realizar la prueba unitaria para listar las ciudades y validar mediante el tamaño de la lista
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Ciudad> lista = ciudadRepo.findAll();
        Assertions.assertEquals(3, lista.size());
    }

}
