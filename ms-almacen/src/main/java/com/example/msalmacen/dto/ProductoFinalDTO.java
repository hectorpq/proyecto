package com.example.msalmacen.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoFinalDTO {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipoTela;
    private String color;
    private Double anchoCm;
    private Double pesoGsm;
    private BigDecimal precioMetro;
    private Double stockActual;
    private Double stockMinimo;
    private String unidad;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
}
