package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {
    //Declaracion  de atributos  de la clase con su respectiva  parametrizacion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Column(nullable=false)
    private LocalDate fecha_compra;

    @Column(nullable = false)
    private String medio_pago;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @Builder
    public Compra(Integer codigo, Usuario usuario, LocalDate fecha_compra, String medio_pago) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.fecha_compra = fecha_compra;
        this.medio_pago = medio_pago;
    }
}
