package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length= 100)
    private String nombre;

    @Positive
    @Column( nullable=false)
    private Integer unidades;

    @Column(nullable = false, length= 1000)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable=false)
    private LocalDateTime fecha_limite;


    @Column( nullable=false)
    private Double descuento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @ElementCollection
    @Column(nullable = false)
    private List<String> imagenes;

    //Codigo vendedor?
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;


    @ManyToMany(mappedBy = "productosFavoritos")
    private List<Usuario> usuariosFavoritos;

    @ElementCollection
    @Column(nullable = false)
    private List<Categoria> categorias;

    public Producto(Integer codigo, String nombre, Integer unidades, String descripcion, Double precio, LocalDateTime fecha_limite, Double descuento, Ciudad ciudad, Usuario vendedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha_limite = fecha_limite;
        this.descuento = descuento;
        this.ciudad = ciudad;
        this.vendedor = vendedor;
    }
}
