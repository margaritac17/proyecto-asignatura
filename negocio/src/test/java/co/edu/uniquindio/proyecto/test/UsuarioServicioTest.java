package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registraTest(){
        Usuario usuario = new Usuario("123", "Pepito", "pepe@email.com", "1234","elpepe", null);
        try {
           Usuario usuarioRespuesta=  usuarioServicio.registrarUsuario(usuario);
            Assertions.assertNotNull(usuarioRespuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarTest(){
        try {
           usuarioServicio.eliminarUsuario("123");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listar() throws Exception {
        Usuario usuario = new Usuario("123", "Marcos", "pepe@email.com", "1234","elpepe", null);
       usuarioServicio.registrarUsuario(usuario);
        List<Usuario> lista=usuarioServicio.listarUsuario();
        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarTest(){
        try {
            Usuario u = usuarioServicio.obtenerUsuario("123");
            u.setPassword("1234567");
            usuarioServicio.actualizarUsuario(u);

            Usuario modificado= usuarioServicio.obtenerUsuario("123");
            Assertions.assertEquals("1234567", modificado.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test()
    public void loginTest(){
        try {
            Usuario usuario=usuarioServicio.iniciarSesion("pepe@email.com", "12345");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false,e.getMessage());
            e.printStackTrace();
        }
    }

}
