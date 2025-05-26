package com.example.BDPostgres.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(length = 100)
    private String email;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column(length = 50)
    private String tipoCliente;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Venta> ventas;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Reservacion> reservaciones;
}
