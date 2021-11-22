package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @Length(min=2 ,max = 150, message = "El nombre debe tener entre 5 y 150 caracteres")
    @NotBlank
    private String nombre;

    @Column(nullable = false, unique=true)
    @Email(message = "Escriba un email valido")
    @NotBlank
    private String email;

    @Column(nullable = false, length= 100)
    @Length(max = 100, message = "La contraseña debe tener maximo 100 caracteres")
    @NotBlank
    private String password;

}