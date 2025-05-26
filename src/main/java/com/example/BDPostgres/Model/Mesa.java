package com.example.BDPostgres.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMesa;

    @Column(nullable = false)
    private Integer numeroMesa;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(length = 50)
    private String estado;

    @OneToMany(mappedBy = "mesa")
    @JsonIgnore
    private List<Venta> ventas;

    @OneToMany(mappedBy = "mesa")
    @JsonIgnore
    private List<Reservacion> reservaciones;
}


