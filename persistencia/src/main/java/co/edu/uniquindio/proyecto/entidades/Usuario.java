package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {
    //Declaracion  de atributos  de la clase con su respectiva  parametrizacion

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

    //Constructor
    @Builder
    public Usuario(String codigo, String nombre, String email, String password, String username, Ciudad ciudad) {
        super(codigo, nombre, email, password);
        this.username = username;
        this.ciudad = ciudad;
    }

}
