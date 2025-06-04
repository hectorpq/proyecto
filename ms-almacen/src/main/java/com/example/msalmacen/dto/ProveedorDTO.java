package com.example.msalmacen.dto;

import lombok.*;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class ProveedorDTO {
    private Long id;
    private String nombre;
    private String ruc;
    private String direccion;

    public ProveedorDTO() {
    }

    public ProveedorDTO(Long id, String nombre, String ruc, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
