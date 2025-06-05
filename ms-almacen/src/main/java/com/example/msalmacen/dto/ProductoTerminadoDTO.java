package com.example.msalmacen.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductoTerminadoDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private List<ComposicionDTO> composiciones;
}
