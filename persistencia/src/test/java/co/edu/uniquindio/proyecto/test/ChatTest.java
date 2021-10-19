package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Test
    @Sql("classpath:data.sql")
    public void registrarChatTest(){

        Producto producto = productoRepo.findById(1).orElse(null);
        Usuario usuario = usuarioRepo.findById("Maria").orElse(null);

        Chat chat= Chat.builder().codigo(1).producto(producto).usuario(usuario).build();

        Chat chatGuardado= chatRepo.save(chat);
        Assertions.assertNotNull(chatGuardado);
    }


    @Test
    @Sql("classpath:data.sql")
    public void eliminarChatTest(){

        Chat chat =chatRepo.findById(1).orElse(null);
        chatRepo.delete(chat);

        Chat chatEliminado =chatRepo.findById(1).orElse(null);
        Assertions.assertNull(chatEliminado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void ActualizarDetalleCompraTest(){

        Chat chat =chatRepo.findById(1).orElse(null);
        Usuario usuario = usuarioRepo.findById("Maria").orElse(null);

        chat.setUsuario(usuario);
        chatRepo.save(chat);

        Chat chatActualizado =chatRepo.findById(1).orElse(null);
        Assertions.assertEquals(usuario, chat.getUsuario());
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarDetalleCompraTest(){
        List<Chat> lista = chatRepo.findAll();
        Assertions.assertEquals(3, lista.size());
    }



}
