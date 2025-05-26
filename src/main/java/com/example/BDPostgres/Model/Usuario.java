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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(length = 50, nullable = false)
    private String rol;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Venta> ventas;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<IncidenciaFraude> incidencias;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<CierreCaja> cierres;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Turno> turnos;
}
