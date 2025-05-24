package com.example.mscalidad.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inspecciones_calidad")
public class InspeccionCalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoProducto;          // Código del producto inspeccionado

    private String inspector;               // Nombre del inspector o ID

    private String resultado;               // Aprobado / Rechazado

    private String observaciones;           // Comentarios u observaciones

    private LocalDateTime fechaInspeccion;  // Fecha y hora de la inspección
    private String estado;


    // Constructor vacío obligatorio para JPA
    public InspeccionCalidad() {}

    // Getters y Setters

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFechaInspeccion() {
        return fechaInspeccion;
    }

    public void setFechaInspeccion(LocalDateTime fechaInspeccion) {
        this.fechaInspeccion = fechaInspeccion;
    }
}
