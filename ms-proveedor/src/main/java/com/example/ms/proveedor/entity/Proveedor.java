package com.example.ms.proveedor.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "proveedores")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_proveedor", unique = true, nullable = false, length = 20)
    @NotBlank(message = "El código del proveedor es obligatorio")
    @Size(max = 20, message = "El código no puede exceder 20 caracteres")
    private String codigoProveedor;

    @Column(name = "nombre_empresa", nullable = false, length = 100)
    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombreEmpresa;

    @Column(name = "nombre_contacto", length = 80)
    @Size(max = 80, message = "El nombre de contacto no puede exceder 80 caracteres")
    private String nombreContacto;

    @Column(name = "telefono", length = 20)
    @Pattern(regexp = "^[+]?[0-9\\s\\-()]{7,20}$", message = "Formato de teléfono inválido")
    private String telefono;

    @Column(name = "email", length = 100)
    @Email(message = "Formato de email inválido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    private String email;

    @Column(name = "direccion", length = 200)
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    private String direccion;

    @Column(name = "ciudad", length = 50)
    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    private String ciudad;

    @Column(name = "pais", length = 50)
    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    private String pais;

    @Column(name = "codigo_postal", length = 10)
    @Size(max = 10, message = "El código postal no puede exceder 10 caracteres")
    private String codigoPostal;

    @Column(name = "rfc_nit", length = 20)
    @Size(max = 20, message = "El RFC/NIT no puede exceder 20 caracteres")
    private String rfcNit;

    @Column(name = "tipo_proveedor", length = 30)
    @Enumerated(EnumType.STRING)
    private TipoProveedor tipoProveedor;

    @Column(name = "categoria_productos", length = 100)
    @Size(max = 100, message = "La categoría no puede exceder 100 caracteres")
    private String categoriaProductos;

    @Column(name = "condiciones_pago", length = 50)
    @Size(max = 50, message = "Las condiciones de pago no pueden exceder 50 caracteres")
    private String condicionesPago;

    @Column(name = "descuento_comercial")
    @DecimalMin(value = "0.0", message = "El descuento no puede ser negativo")
    @DecimalMax(value = "100.0", message = "El descuento no puede ser mayor a 100%")
    private Double descuentoComercial;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    @Size(max = 500, message = "Las observaciones no pueden exceder 500 caracteres")
    private String observaciones;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public enum TipoProveedor {
        NACIONAL,
        INTERNACIONAL,
        DISTRIBUIDOR,
        FABRICANTE,
        MAYORISTA,
        MINORISTA
    }


    public Proveedor() {
    }

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public String getRfcNit() {
        return rfcNit;
    }

    public void setRfcNit(String rfcNit) {
        this.rfcNit = rfcNit;
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

    public String getCondicionesPago() {
        return condicionesPago;
    }

    public void setCondicionesPago(String condicionesPago) {
        this.condicionesPago = condicionesPago;
    }

    public Double getDescuentoComercial() {
        return descuentoComercial;
    }

    public void setDescuentoComercial(Double descuentoComercial) {
        this.descuentoComercial = descuentoComercial;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}