package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
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
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    //Funcion que permite realizar la prueba unitaria para registrar un comentario y valida que no sea null
    @Test
    @Sql("classpath:data.sql")
    public void registrarComentarioTest(){

        Usuario usuario =usuarioRepo.findById("125").orElse(null);
        Producto producto = productoRepo.findById(3).orElse(null);
        Comentario comentario = Comentario.builder().codigo(1).usuario(usuario).producto(producto).calificacion(3).fecha(LocalDateTime.of(2021,10,06,07,12,32)).mensaje("Producto regular, no me gustó tanto").respuesta("").build();
        Comentario comentarioGuardado= comentarioRepo.save(comentario);
        Assertions.assertNotNull(comentarioGuardado);
    }

    //Funcion que permite realizar la prueba unitaria para eliminar un comentario
    @Test
    @Sql("classpath:data.sql")
    public void eliminarComentarioTest(){

        Comentario comentario =comentarioRepo.findById(1).orElse(null);
        comentarioRepo.delete(comentario);

        Comentario comentarioEliminado = comentarioRepo.findById(1).orElse(null);
        Assertions.assertNull(comentarioEliminado);

    }

    //Funcion que permite realizar una prueba unitaria para actualizar un comentario
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarComentarioTest(){

        Comentario comentario = comentarioRepo.findById(1).orElse(null);
        comentario.setMensaje("Esta muy bueno");
        comentarioRepo.save(comentario);

        Comentario comentarioActualizado = comentarioRepo.findById(1).orElse(null);
        Assertions.assertEquals("Esta muy bueno", comentarioActualizado.getMensaje());

    }

    //Funcion que permite realizar la prueba para listar los comentarios
    @Test
    @Sql("classpath:data.sql")
    public void listarComentarioTest(){
        List<Comentario> lista = comentarioRepo.findAll();
        Assertions.assertEquals(2, lista.size());
    }


}
