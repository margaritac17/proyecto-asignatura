package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private ProductoRepo productoRepo;

    //Funcion que permite realizar la prueba unitaria para registrar un detalleCompra
    @Test
    @Sql("classpath:data.sql")
    public void registrarDetalleCompraTest() {

        Compra compra = compraRepo.findById(1).orElse(null);
        Producto producto = productoRepo.findById(1).orElse(null);

        DetalleCompra detalleCompra= DetalleCompra.builder().codigo(1).compra(compra).producto(producto).unidades(5).precio_producto(3.000000).build();
        DetalleCompra detalleCompraGuardada = detalleCompraRepo.save(detalleCompra);
        Assertions.assertNotNull(detalleCompraGuardada);

    }

    //Funcion que permite realizar la prueba unitaria para eliminar un detalleCompra
    @Test
    @Sql("classpath:data.sql")
    public void eliminarDetalleCompraTest(){

        DetalleCompra detalleCompra =detalleCompraRepo.findById(1).orElse(null);
        detalleCompraRepo.delete(detalleCompra);

        DetalleCompra detalleCompraEliminado = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertNull(detalleCompraEliminado);

    }

    //Funcion que permite realizar la prueba unitaria para actualizar un detalleCompra
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarDetalleCompraTest(){

        DetalleCompra detalleCompra =detalleCompraRepo.findById(1).orElse(null);
        detalleCompra.setUnidades(10);
        detalleCompraRepo.save(detalleCompra);

        DetalleCompra detalleCompraActualizada = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertEquals(10, detalleCompraActualizada.getUnidades());
    }

    //Funcion que permite realizar la prueba para validar la cantidad de detalleCompra creados mediante el tamaño de la lista
    @Test
    @Sql("classpath:data.sql")
    public void listarDetalleCompraTest(){
        List<DetalleCompra> lista = detalleCompraRepo.findAll();
        Assertions.assertEquals(3, lista.size());
    }

}
