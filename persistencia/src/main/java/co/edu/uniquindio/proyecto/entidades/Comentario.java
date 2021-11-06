package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Comentario implements Serializable {
    //Declaracion  de atributos  de la clase con su respectiva  parametrizacion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Lob
    @Column(nullable = false)
    private String mensaje;

    @Lob
    private String respuesta;

    @Column(nullable=false)
    private LocalDateTime fecha;

    private Integer calificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @Builder
    public Comentario(Integer codigo, Producto producto, Usuario usuario, String mensaje, String respuesta, LocalDateTime fecha, Integer calificacion) {
        this.codigo = codigo;
        this.producto = producto;
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fecha = fecha;
        this.calificacion = calificacion;
    }
}
