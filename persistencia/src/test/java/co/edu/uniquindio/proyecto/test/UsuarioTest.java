package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
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

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest(){
/*
        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);

        HashMap<String, String> telefonos = new HashMap<>();
        telefonos.put("casa", "171717");
        telefonos.put("celular", "9828985");
        Usuario usuario = new Usuario("123", "pepito", LocalDate.now(), GeneroPersona.MASCULINO, "pepito@email.com", telefonos, ciudad);

        Usuario usuarioGuardado= usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);
*/
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        usuarioRepo.deleteById("123");

        Usuario usuarioBuscado = usuarioRepo.findById("123").orElse(null);
        Assertions.assertNull(usuarioBuscado);

    }

    @Test
    @Sql("classpath:data.sql")
    public void ActualizarTest(){

        Usuario guardado = usuarioRepo.findById("124").orElse(null);
        guardado.setEmail("maria_correonuevo@email.com");
        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById("124").orElse(null);
        Assertions.assertEquals("maria_correonuevo@email.com", usuarioBuscado.getEmail());

    }

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        usuarios.forEach( u -> System.out.println(u));

    }

    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){
        List<Usuario> lista = usuarioRepo.findByNombreContains("Maria");
        lista.forEach(System.out::println);

    }
    @Test
    @Sql("classpath:data.sql")
    public void filtrarEmailTest(){
        Optional<Usuario> usuario = usuarioRepo.findByEmail("carlos@email.com");
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
}
