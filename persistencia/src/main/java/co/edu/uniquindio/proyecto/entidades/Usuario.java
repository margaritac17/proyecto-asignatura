package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {
    //Declaracion  de atributos  de la clase con su respectiva  parametrizacion

    @ElementCollection
    @ToString.Exclude
    private List<String> telefonos;

    @Column(nullable = false, unique=true)
    private String username;

    @ManyToOne
   // @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productosVenta;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleSubasta> detalleSubastas;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chats;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productosFavoritos;

    @Builder
    public Usuario(String codigo, String nombre, String email, String password, String username, Ciudad ciudad) {
        super(codigo, nombre, email, password);
        this.username = username;
        this.ciudad = ciudad;
    }
}
