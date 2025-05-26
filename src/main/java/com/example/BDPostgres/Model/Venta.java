package com.example.BDPostgres.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    @Column(name = "id_cliente", insertable = false, updatable = false)
    private Integer idCliente;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "id_usuario", insertable = false, updatable = false)
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "id_mesa", insertable = false, updatable = false)
    private Integer idMesa;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String hora;

    @Column(nullable = false)
    private Double total;

    @Column(length = 50, nullable = false)
    private String metodoPago;

    @OneToMany(mappedBy = "venta")
    @JsonIgnore
    private List<DetalleVenta> detalles;

    @OneToMany(mappedBy = "venta")
    @JsonIgnore
    private List<IncidenciaFraude> incidencias;
}
