package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @Column(length = 120, nullable=false, unique=true)
    private String email;

    @ElementCollection
    @Column(nullable = false)
    private Map<String,String> numTelefonos;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;


    @OneToMany(mappedBy = "usuarioPrestamo")
    @ToString.Exclude
    private List<Prestamo> prestamos;

    public Usuario(String codigo, String nombre, LocalDate fechaNacimiento, GeneroPersona genero, String email, Map<String, String> numTelefonos, Ciudad ciudad) {
        super(codigo, nombre, fechaNacimiento, genero);
        this.email = email;
        this.numTelefonos = numTelefonos;
        this.ciudad = ciudad;
    }

}
