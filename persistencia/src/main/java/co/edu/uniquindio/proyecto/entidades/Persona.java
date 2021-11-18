package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@ToString
public class Persona implements Serializable {
    //Declaracion  de atributos  de la clase Personba con su respectiva  parametrizacion

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length= 150)
    @Length(max = 150)
    private String nombre;

    @Column(nullable = false, unique=true)
    @Email(message = "Escriba un email valido")
    private String email;

    @Column(nullable = false, length= 100)
    private String password;

}