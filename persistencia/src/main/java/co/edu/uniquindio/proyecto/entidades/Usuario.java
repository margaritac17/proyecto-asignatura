package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @ElementCollection
    private List<String> telefonos;

    @Column(nullable = false, unique=true)
    private String username;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    private List<Producto> productosVenta;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<DetalleSubasta> detalleSubastas;


    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Chat> chats;

    @ManyToMany
    private List<Producto> productosFavoritos;

    @Builder
    public Usuario(String codigo, String nombre, String email, String password, String username, Ciudad ciudad) {
        this.username = username;
        this.ciudad = ciudad;
    }

    public Usuario(List<String> telefonos, String username, Ciudad ciudad, List<Producto> productosVenta, List<Comentario> comentarios, List<Compra> compras, List<DetalleSubasta> detalleSubastas, List<Chat> chats, List<Producto> productosFavoritos) {
        this.telefonos = telefonos;
        this.username = username;
        this.ciudad = ciudad;
        this.productosVenta = productosVenta;
        this.comentarios = comentarios;
        this.compras = compras;
        this.detalleSubastas = detalleSubastas;
        this.chats = chats;
        this.productosFavoritos = productosFavoritos;
    }
}
