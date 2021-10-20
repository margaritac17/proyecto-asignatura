package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
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
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //Funcion que permite realizar la prueba unitaria para registrar una compra y valida que no sea null
    @Test
    @Sql("classpath:data.sql")
    public void registrarCompraTest() {

        Usuario usuario = usuarioRepo.findById("125").orElse(null);
        Compra compra= Compra.builder().codigo(1).usuario(usuario).fecha_compra(LocalDate.of(2011,10,15)).medio_pago("Tarjeta de credito").build();

        Compra compraGuardada = compraRepo.save(compra);
        Assertions.assertNotNull(compraGuardada);

    }

    //Funcion que permite realizar la prueba unitaria para eliminar una compra
    @Test
    @Sql("classpath:data.sql")
    public void eliminarCompraTest(){

        Compra compra =compraRepo.findById(1).orElse(null);
        compraRepo.delete(compra);

        Compra compraEliminada = compraRepo.findById(1).orElse(null);
        Assertions.assertNull(compraEliminada);

    }

    //Funcion que permite realizar una prueba unitaria para actualizar una compra
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarCompraTest(){

        Compra compra = compraRepo.findById(1).orElse(null);
        compra.setMedio_pago("Efectivo");
        compraRepo.save(compra);

        Compra compraActualizada = compraRepo.findById(1).orElse(null);
        Assertions.assertEquals("Efectivo", compraActualizada.getMedio_pago());

    }

    //Funcion que permite realizar la prueba para listar las compras
    @Test
    @Sql("classpath:data.sql")
    public void listarCompraTest(){
        List<Compra> lista = compraRepo.findAll();
        Assertions.assertEquals(3, lista.size());
    }



}
