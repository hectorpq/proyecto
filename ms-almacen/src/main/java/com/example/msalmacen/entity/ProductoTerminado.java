package com.example.msalmacen.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto_terminado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoTerminado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;      // ← Asegúrate de que esté presente

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual = 0;

    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo = 0;

    @Column(nullable = false)
    private Boolean activo = true;
}
