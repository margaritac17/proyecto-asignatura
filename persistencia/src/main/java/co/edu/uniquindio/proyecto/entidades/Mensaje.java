package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 10000)
    private String mensaje;

    @Column(length = 200)
    private String emisor;

    @Column(length = 100)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Chat chats;

    @Builder
    public Mensaje(Integer codigo, String mensaje, String emisor, LocalDate fecha, Chat chats) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.fecha = fecha;
        this.chats = chats;
    }
}

