package co.edu.uniquindio.proyecto;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NegocioApplication {

/*
    @Autowired
    private EmailSenderService service;
*/
    public static void main(String[] args) {
        SpringApplication.run(NegocioApplication.class, args);
    }

  /*
    @EventListener(ApplicationReadyEvent.class)

    public void triggerMail() throws MessagingException {

        service.sendSimpleEmail();

    }
   */
}
