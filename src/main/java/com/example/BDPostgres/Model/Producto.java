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
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 50)
    private String categoria;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "id_proveedor", insertable = false, updatable = false)
    private Integer idProveedor;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    @JsonIgnore
    private Proveedor proveedor;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<DetalleVenta> detalles;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Inventario> inventario;
}

