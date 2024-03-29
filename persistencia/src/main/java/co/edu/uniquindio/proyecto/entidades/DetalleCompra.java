package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DetalleCompra implements Serializable {
    //Declaracion  de atributos  de la clase con su respectiva  parametrizacion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @Positive
    @Column( nullable=false)
    @Positive
    private Integer unidades;

    @Column(nullable=false)
    @Positive
    private Float precio_producto;

    @Builder
    public DetalleCompra(Integer codigo, Compra compra, Producto producto, Integer unidades, Float precio_producto) {
        this.codigo = codigo;
        this.compra = compra;
        this.producto = producto;
        this.unidades = unidades;
        this.precio_producto = precio_producto;
    }
}
