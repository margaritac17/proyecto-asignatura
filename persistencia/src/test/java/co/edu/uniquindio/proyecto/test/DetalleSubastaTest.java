package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.DetalleSubasta;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.DetalleSubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleSubastaTest {
    
    @Autowired
    private DetalleSubastaRepo detalleSubastaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    //Metodo test de registrar una Detalle subasta
    @Test
    @Sql("classpath:data.sql")
    public void registrarDetalleSubastaTest(){

        Subasta subasta = subastaRepo.findById(1).orElse(null);
        Usuario usuario = usuarioRepo.findById("Maria").orElse(null);

        DetalleSubasta detalleSubasta = DetalleSubasta.builder().codigo(1).fecha_subasta(LocalDate.of(2011,10,15)).valor(20.000).subasta(subasta).usuario(usuario).build();

       DetalleSubasta detalleSubastaGuardada = detalleSubastaRepo.save(detalleSubasta);

        Assertions.assertNotNull(detalleSubastaGuardada);
    }

    //Metodo test para eliminar una Detalle subasta
    @Test
    @Sql("classpath:data.sql")
    public void eliminarDetalleSubastaTest(){

        DetalleSubasta detalleSubasta = detalleSubastaRepo.findById(1).orElse(null);

        detalleSubastaRepo.delete(detalleSubasta);

        DetalleSubasta detalleSubastaEliminada = detalleSubastaRepo.findById(1).orElse(null);

        Assertions.assertNull(detalleSubastaEliminada);
    }

    //Metodo test para actualizar una Detallesubasta
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarDetalleSubastaTest(){

        DetalleSubasta detalleSubasta = detalleSubastaRepo.findById(1).orElse(null);

        Subasta subasta = subastaRepo.findById(1).orElse(null);
        Usuario usuario = usuarioRepo.findById("Maria").orElse(null);

        detalleSubasta.setSubasta(subasta);
        //detalleSubasta.setUsuario(usuario);

        detalleSubastaRepo.save(detalleSubasta);

        DetalleSubasta detalleSubastaActualizada = detalleSubastaRepo.findById(1).orElse(null);

        Assertions.assertEquals(subasta,  detalleSubasta.getSubasta());
    }

    //Metodo test para listar las Detallesubastas
    @Test
    @Sql("classpath:data.sql")
    public void listarDetalleSubastaTest(){

        List<DetalleSubasta> lista = detalleSubastaRepo.findAll();

        Assertions.assertEquals(3, lista.size());
    }

}
