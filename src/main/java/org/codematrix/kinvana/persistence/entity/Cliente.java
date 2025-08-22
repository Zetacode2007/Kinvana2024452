package org.codematrix.kinvana.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity(name = "Clientes")
//Lombok
@Data  //genera los Setter y getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode//codigo de autenticacion de la entidad
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCliente;// permite usar null en vez de 0
    @Column
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String genero; //enum ('masculino','femenino','no')
    private Integer edad;
}
