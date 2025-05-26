package com.example.BDPostgres.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CierreCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCierre;

    @Column(name = "id_usuario", insertable = false, updatable = false)
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private LocalDate fecha;

    private String hora;

    private Double montoDeclarado;

    private Double montoReal;

    private Double diferencia;
}


