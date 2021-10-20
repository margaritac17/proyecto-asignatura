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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    //Funcion que permite realizar la prueba unitaria para registrar un usuario
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){

        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        Usuario usuario = Usuario.builder().codigo("126").nombre("Luisito").email("luisito@email.com").password("12345").username("Luisito").ciudad(ciudad).build();

        Usuario usuarioGuardado= usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);
    }

    //Funcion que permite realizar la prueba unitaria para eliminar un usuario
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        Usuario usuario =usuarioRepo.findById("125").orElse(null);
        usuarioRepo.delete(usuario);

        Usuario usuarioEliminado = usuarioRepo.findById("125").orElse(null);
        Assertions.assertNull(usuarioEliminado);

    }

    //Funcion que permite realizar la prueba unitaria para actualizar una usuario
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarTest(){

        Usuario usuario = usuarioRepo.findById("125").orElse(null);
        usuario.setPassword("1111");
        usuarioRepo.save(usuario);

        Usuario usuarioActualizado = usuarioRepo.findById("125").orElse(null);
        Assertions.assertEquals("1111", usuarioActualizado.getPassword());

    }

    //Funcion que permite realizar la prueba unitaria para validar la cantidad de usuarios mediante el tamaño de la lista
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Usuario> lista = usuarioRepo.findAll();
        Assertions.assertEquals(3, lista.size());
    }


    /*
    NO HACE PARTE DEL CRUD

    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){
        List<Usuario> lista = usuarioRepo.findByNombreContains("Maria");
        lista.forEach(System.out::println);

    }
    @Test
    @Sql("classpath:data.sql")
    public void filtrarEmailTest(){
        Optional<Usuario> usuario = usuarioRepo.findByEmail("pepe@email.com");
       if(usuario.isPresent()){
           System.out.println(usuario.get());
       }else{
           System.out.println("No existe ese correo");
       }
    }

    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest() {

    Pageable paginador= PageRequest.of(0,2);

    Page<Usuario> lista= usuarioRepo.findAll(paginador);
    System.out.println(lista.stream().collect(Collectors.toList()));

    }

    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest() {

        List<Usuario> lista= usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);

    }
    */

}
