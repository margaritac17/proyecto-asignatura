package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private ProductoRepo productoRepo;
/*
    //Funcion que permite realizar la prueba unitaria para registrar un producto y valida que no sea null
    @Test
    @Sql("classpath:data.sql")
    public void registrarProductoTest(){

        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        Usuario usuario =usuarioRepo.findById("123").orElse(null);

        Producto producto = Producto.builder().codigo(1).descripcion("Telefono Samsung s21 como nuevo").descuento(0f).fecha_limite(LocalDate.of(2021,11,21)).nombre("Samsung S21").nombrePublicacion("Samsung S21 x").precio(4000000f).unidades(3).vendedor(usuario).ciudad(ciudad).build();
        Producto productoGuardado= productoRepo.save(producto);
        Assertions.assertNotNull(productoGuardado);

    }

    //Funcion que permite realizar la prueba unitaria para eliminar un producto
    @Test
    @Sql("classpath:data.sql")
    public void eliminarProductoTest(){

        Producto producto =productoRepo.findById(1).orElse(null);
        productoRepo.delete(producto);

        Producto productoEliminado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(productoEliminado);

    }

    //Funcion que permite realizar una prueba unitaria para actualizar un producto
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarProductoTest(){

        Producto producto = productoRepo.findById(1).orElse(null);
        producto.setUnidades(5);
        productoRepo.save(producto);

        Producto productoActualizado = productoRepo.findById(1).orElse(null);
        Assertions.assertEquals(5, productoActualizado.getUnidades());

    }

    //Funcion que permite realizar la prueba para listar los productos
    @Test
    @Sql("classpath:data.sql")
    public void listarProductoTest(){
        List<Producto> lista = productoRepo.findAll();
        Assertions.assertEquals(3, lista.size());
    }
*/
    @Test
    @Sql("classpath:data.sql")
    public void promedioCalificacionTest(){
      Integer cal= productoRepo.calificacionPromedio(1);
        Assertions.assertEquals(2, cal);
    }


}
