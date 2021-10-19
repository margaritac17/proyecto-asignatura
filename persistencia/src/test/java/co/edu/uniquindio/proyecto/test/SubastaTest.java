package co.edu.uniquindio.proyecto.test;



import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
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
public class SubastaTest {

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private ProductoRepo productoRepo;

    //Metodo test de registrar una subasta
    @Test
    @Sql("classpath:data.sql")
    public void registrarSubastaTest(){

        Producto producto = productoRepo.findById(1).orElse(null);

        Subasta subasta = Subasta.builder().codigo(1).fecha_limite(LocalDate.of(2011,10,15)).producto(producto).build();

        Subasta subastaGuardada = subastaRepo.save(subasta);
        Assertions.assertNotNull(subastaGuardada);
    }



    //Metodo test para eliminar una subasta
    @Test
    @Sql("classpath:data.sql")
    public void eliminarSubastaTest(){

        Subasta subasta = subastaRepo.findById(1).orElse(null);

        subastaRepo.delete(subasta);

        Subasta subastaEliminada = subastaRepo.findById(1).orElse(null);


        Assertions.assertNull(subastaEliminada);
    }

    //Metodo test para actualizar una subasta
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarSubastaTest(){

        Subasta subasta = subastaRepo.findById(1).orElse(null);

        Producto producto = productoRepo.findById(1).orElse(null);

        subasta.setProducto(producto);

        subastaRepo.save(subasta);

       Subasta subastaActualizada = subastaRepo.findById(1).orElse(null);

        Assertions.assertEquals(producto, subasta.getProducto());
    }

    //Metodo test para listar las subastas
    @Test
    @Sql("classpath:data.sql")
    public void listarSubastaTest(){

        List<Subasta> lista = subastaRepo.findAll();
        Assertions.assertEquals(3, lista.size());
    }
}
