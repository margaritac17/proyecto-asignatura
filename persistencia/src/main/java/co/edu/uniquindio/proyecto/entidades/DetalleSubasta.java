package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DetalleSubasta implements Serializable {

    //Declaracion  de atributos  de la clase con su respectiva  parametrizacion
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subasta subasta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Column(nullable=false)
    private Double valor;

    @Column(nullable=false)
    private LocalDate fecha_subasta;


    //constructor de la clase
    @Builder
    public DetalleSubasta(Integer codigo, Subasta subasta, Usuario usuario, Double valor, LocalDate fecha_subasta) {
        this.codigo = codigo;
        this.subasta = subasta;
        this.usuario = usuario;
        this.valor = valor;
        this.fecha_subasta = fecha_subasta;
    }
}
