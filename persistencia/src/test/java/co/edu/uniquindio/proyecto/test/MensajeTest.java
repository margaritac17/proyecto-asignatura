package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
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
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    @Autowired
    private ChatRepo chatRepo;

    //Funcion que permite realizar la prueba unitaria para registrar un mensaje y se comprueba que no sea null
    @Test
    @Sql("classpath:data.sql")
    public void registrarMensajeTest(){

        Chat chat = chatRepo.findById(1).orElse(null);
        Mensaje mensaje = Mensaje.builder().codigo(4).emisor("dario").fecha(LocalDate.of(2011,10,15)).mensaje("Holi").chats(chat).build();

        Mensaje mensajeGuardado= mensajeRepo.save(mensaje);
        Assertions.assertNotNull(mensajeGuardado);
    }

    //Funcion que permite realizar la prueba unitaria para eliminar un mensaje y comprobar que se elimino mediante el assertNull
    @Test
    @Sql("classpath:data.sql")
    public void eliminarMensajeTest(){

        Mensaje mensaje =mensajeRepo.findById(1).orElse(null);
        mensajeRepo.delete(mensaje);

        Mensaje mensajeEliminado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertNull(mensajeEliminado);

    }

    //Funcion que permite realizar la prueba unitaria para actualizar un mensaje
    @Test
    @Sql("classpath:data.sql")
    public void ActualizarMensajeTest(){

        Mensaje mensaje =mensajeRepo.findById(1).orElse(null);
        mensaje.setMensaje("mensaje prueba");
        mensajeRepo.save(mensaje);

        Mensaje mensajeActualizado =mensajeRepo.findById(1).orElse(null);
        Assertions.assertEquals("mensaje prueba", mensajeActualizado.getMensaje());

    }

    //Funcion que permite realizar la prueba unitaria para listar los mensajes y validar mediante el tamaño de la lista
    @Test
    @Sql("classpath:data.sql")
    public void listarMensajeTest(){
        List<Mensaje> lista = mensajeRepo.findAll();
        Assertions.assertEquals(3, lista.size());
    }


}
