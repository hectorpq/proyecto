package com.example.ms.proveedor.dto;
import com.example.ms.proveedor.entity.Proveedor.TipoProveedor;
import com.example.ms.proveedor.entity.Proveedor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ProveedorResponseDTO {

    private Long id;
    private String codigoProveedor;
    private String nombreEmpresa;
    private String nombreContacto;
    private String telefono;
    private String email;
    private String direccion;
    private String ciudad;
    private String pais;
    private String codigoPostal;
    private String rfcNit;
    private TipoProveedor tipoProveedor;
    private String categoriaProductos;
    private String condicionesPago;
    private Double descuentoComercial;
    private Boolean activo;
    private String observaciones;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public TipoProveedor getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(TipoProveedor tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getCategoriaProductos() {
        return categoriaProductos;
    }

    public void setCategoriaProductos(String categoriaProductos) {
        this.categoriaProductos = categoriaProductos;
    }

    public Double getDescuentoComercial() {
        return descuentoComercial;
    }

    public void setDescuentoComercial(Double descuentoComercial) {
        this.descuentoComercial = descuentoComercial;
    }

    public String getRfcNit() {
        return rfcNit;
    }

    public void setRfcNit(String rfcNit) {
        this.rfcNit = rfcNit;
    }

    public String getCondicionesPago() {
        return condicionesPago;
    }

    public void setCondicionesPago(String condicionesPago) {
        this.condicionesPago = condicionesPago;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}