package com.example.ms.proveedor.dto;
import com.example.ms.proveedor.entity.Proveedor.TipoProveedor;
import com.example.ms.proveedor.entity.Proveedor;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ProveedorUpdateDTO {

    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombreEmpresa;

    @Size(max = 80, message = "El nombre de contacto no puede exceder 80 caracteres")
    private String nombreContacto;

    @Pattern(regexp = "^[+]?[0-9\\s\\-()]{7,20}$", message = "Formato de teléfono inválido")
    private String telefono;

    @Email(message = "Formato de email inválido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    private String email;

    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    private String direccion;

    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    private String ciudad;

    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    private String pais;

    @Size(max = 10, message = "El código postal no puede exceder 10 caracteres")
    private String codigoPostal;

    @Size(max = 20, message = "El RFC/NIT no puede exceder 20 caracteres")
    private String rfcNit;

    private TipoProveedor tipoProveedor;

    @Size(max = 100, message = "La categoría no puede exceder 100 caracteres")
    private String categoriaProductos;

    @Size(max = 50, message = "Las condiciones de pago no pueden exceder 50 caracteres")
    private String condicionesPago;

    @DecimalMin(value = "0.0", message = "El descuento no puede ser negativo")
    @DecimalMax(value = "100.0", message = "El descuento no puede ser mayor a 100%")
    private Double descuentoComercial;

    private Boolean activo;

    @Size(max = 500, message = "Las observaciones no pueden exceder 500 caracteres")
    private String observaciones;


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

    public String getCategoriaProductos() {
        return categoriaProductos;
    }

    public void setCategoriaProductos(String categoriaProductos) {
        this.categoriaProductos = categoriaProductos;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}