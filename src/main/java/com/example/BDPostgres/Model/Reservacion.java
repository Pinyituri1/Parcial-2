package com.example.BDPostgres.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservacion;

    @Column(name = "id_cliente", insertable = false, updatable = false)
    private Integer idCliente;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnore  // Esto evita que la relación con Cliente se muestre en Swagger
    private Cliente cliente;

    @Column(name = "id_mesa", insertable = false, updatable = false)
    private Integer idMesa;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    @JsonIgnore  // Esto evita que la relación con Mesa se muestre en Swagger
    private Mesa mesa;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String hora;

    @Column(length = 50)
    private String estado;
}
