package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @Column(length = 120, nullable=false, unique=true)
    private String email;

    @ElementCollection
    @Column(nullable = false)
    private Map<String,String> numTelefono;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuarioPrestamo")
    private List<Prestamo> prestamos;

}
