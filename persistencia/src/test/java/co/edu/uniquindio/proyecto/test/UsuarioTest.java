package co.edu.uniquindio.proyecto.test;

import ch.qos.logback.core.net.SyslogOutputStream;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.GeneroPersona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:usuarios.sql")
    public void registrarTest(){

        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);

        HashMap<String, String> telefonos = new HashMap<>();
        telefonos.put("casa", "171717");
        telefonos.put("celular", "9828985");
        Usuario usuario = new Usuario("123", "pepito", LocalDate.now(), GeneroPersona.MASCULINO, "pepito@email.com", telefonos, ciudad);

        Usuario usuarioGuardado= usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);

    }
    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarTest(){

        usuarioRepo.deleteById("123");

        Usuario usuarioBuscado = usuarioRepo.findById("123").orElse(null);
        Assertions.assertNull(usuarioBuscado);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void ActualizarTest(){

        Usuario guardado = usuarioRepo.findById("124").orElse(null);
        guardado.setEmail("maria_correonuevo@email.com");
        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById("124").orElse(null);
        Assertions.assertEquals("maria_correonuevo@email.com", usuarioBuscado.getEmail());

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarTest(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        usuarios.forEach( u -> System.out.println(u));

    }


}
