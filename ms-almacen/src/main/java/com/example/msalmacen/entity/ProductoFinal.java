package com.example.msalmacen.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos_finales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "tipo_tela", length = 50)
    private String tipoTela;

    @Column(length = 50)
    private String color;

    @Column(name = "ancho_cm")
    private Double anchoCm;

    @Column(name = "peso_gsm")
    private Double pesoGsm;

    @Column(name = "precio_metro", precision = 10, scale = 2)
    private BigDecimal precioMetro;

    @Column(name = "stock_actual", precision = 10, scale = 2)
    private Double stockActual = 0.0;

    @Column(name = "stock_minimo", precision = 10, scale = 2)
    private Double stockMinimo = 0.0;

    @Column(length = 10)
    private String unidad = "METROS";

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
