package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length= 10000)
    private String mensaje;

    @Column(length= 200)
    private String emisor;

    @Column(length= 18)
    private String fecha;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Chat chats;

}

