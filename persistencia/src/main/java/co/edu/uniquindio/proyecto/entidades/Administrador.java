package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

//@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable {

    //Declaracion  de atributos  de la clase Administrador con su respectiva  parametrizacion
    @Id
    @Column(length= 18)
    @EqualsAndHashCode.Include
    private String codigoAdmin;

}
