package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
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
    //Declaracion  de atributos  de la clase con su respectiva  parametrizacion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(nullable = false, length= 100)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String nombrePublicacion;

    @Lob
    @NotBlank
    @Column(nullable = false, length= 1000)
    private String descripcion;

    @PositiveOrZero
    @Column( nullable=false)
    private Integer unidades;

    @Future
    @Column(nullable=false)
    private LocalDateTime fecha_limite;

    @Positive
    @Column(nullable = false)
    private Float precio;

    @Column( nullable=false)
    @PositiveOrZero
    private Float descuento;

    @ElementCollection
    @Column(nullable = false)
    private List<Categoria> categorias;

    @ElementCollection
    private List<String> imagenes;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Chat> chats;

    @ManyToMany(mappedBy = "productosFavoritos")
    @ToString.Exclude
    private List<Usuario> favoritos;

    @Builder
    public Producto(String nombre, String nombrePublicacion,String descripcion, Integer unidades, LocalDateTime fecha_limite, Float precio, Float descuento, Usuario vendedor) {
   //     this.codigo = codigo;
        this.nombre = nombre;
        this.nombrePublicacion = nombrePublicacion;
       this.descripcion = descripcion;
        this.unidades = unidades;
        this.fecha_limite = fecha_limite;
        this.precio = precio;
        this.descuento = descuento;
        this.vendedor = vendedor;
      //  this.ciudad = ciudad;
    }


}
