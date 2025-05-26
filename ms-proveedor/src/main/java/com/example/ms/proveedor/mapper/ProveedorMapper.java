package com.example.ms.proveedor.mapper;


import com.example.ms.proveedor.dto.ProveedorCreateDTO;
import com.example.ms.proveedor.dto.ProveedorResponseDTO;
import com.example.ms.proveedor.dto.ProveedorUpdateDTO;
import com.example.ms.proveedor.entity.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    // Convertir de CreateDTO a Entity
    public Proveedor toEntity(ProveedorCreateDTO createDTO) {
        if (createDTO == null) {
            return null;
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setCodigoProveedor(createDTO.getCodigoProveedor());
        proveedor.setNombreEmpresa(createDTO.getNombreEmpresa());
        proveedor.setNombreContacto(createDTO.getNombreContacto());
        proveedor.setTelefono(createDTO.getTelefono());
        proveedor.setEmail(createDTO.getEmail());
        proveedor.setDireccion(createDTO.getDireccion());
        proveedor.setCiudad(createDTO.getCiudad());
        proveedor.setPais(createDTO.getPais());
        proveedor.setCodigoPostal(createDTO.getCodigoPostal());
        proveedor.setRfcNit(createDTO.getRfcNit());
        proveedor.setTipoProveedor(createDTO.getTipoProveedor());
        proveedor.setCategoriaProductos(createDTO.getCategoriaProductos());
        proveedor.setCondicionesPago(createDTO.getCondicionesPago());
        proveedor.setDescuentoComercial(createDTO.getDescuentoComercial());
        proveedor.setActivo(createDTO.getActivo() != null ? createDTO.getActivo() : true);
        proveedor.setObservaciones(createDTO.getObservaciones());

        return proveedor;
    }

    // Convertir de Entity a ResponseDTO
    public ProveedorResponseDTO toResponseDTO(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }

        ProveedorResponseDTO responseDTO = new ProveedorResponseDTO();
        responseDTO.setId(proveedor.getId());
        responseDTO.setCodigoProveedor(proveedor.getCodigoProveedor());
        responseDTO.setNombreEmpresa(proveedor.getNombreEmpresa());
        responseDTO.setNombreContacto(proveedor.getNombreContacto());
        responseDTO.setTelefono(proveedor.getTelefono());
        responseDTO.setEmail(proveedor.getEmail());
        responseDTO.setDireccion(proveedor.getDireccion());
        responseDTO.setCiudad(proveedor.getCiudad());
        responseDTO.setPais(proveedor.getPais());
        responseDTO.setCodigoPostal(proveedor.getCodigoPostal());
        responseDTO.setRfcNit(proveedor.getRfcNit());
        responseDTO.setTipoProveedor(proveedor.getTipoProveedor());
        responseDTO.setCategoriaProductos(proveedor.getCategoriaProductos());
        responseDTO.setCondicionesPago(proveedor.getCondicionesPago());
        responseDTO.setDescuentoComercial(proveedor.getDescuentoComercial());
        responseDTO.setActivo(proveedor.getActivo());
        responseDTO.setObservaciones(proveedor.getObservaciones());
        responseDTO.setFechaCreacion(proveedor.getFechaCreacion());
        responseDTO.setFechaActualizacion(proveedor.getFechaActualizacion());

        return responseDTO;
    }

    // Actualizar Entity desde UpdateDTO
    public void updateEntityFromDTO(ProveedorUpdateDTO updateDTO, Proveedor proveedor) {
        if (updateDTO == null || proveedor == null) {
            return;
        }

        if (updateDTO.getNombreEmpresa() != null) {
            proveedor.setNombreEmpresa(updateDTO.getNombreEmpresa());
        }
        if (updateDTO.getNombreContacto() != null) {
            proveedor.setNombreContacto(updateDTO.getNombreContacto());
        }
        if (updateDTO.getTelefono() != null) {
            proveedor.setTelefono(updateDTO.getTelefono());
        }
        if (updateDTO.getEmail() != null) {
            proveedor.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getDireccion() != null) {
            proveedor.setDireccion(updateDTO.getDireccion());
        }
        if (updateDTO.getCiudad() != null) {
            proveedor.setCiudad(updateDTO.getCiudad());
        }
        if (updateDTO.getPais() != null) {
            proveedor.setPais(updateDTO.getPais());
        }
        if (updateDTO.getCodigoPostal() != null) {
            proveedor.setCodigoPostal(updateDTO.getCodigoPostal());
        }
        if (updateDTO.getRfcNit() != null) {
            proveedor.setRfcNit(updateDTO.getRfcNit());
        }
        if (updateDTO.getTipoProveedor() != null) {
            proveedor.setTipoProveedor(updateDTO.getTipoProveedor());
        }
        if (updateDTO.getCategoriaProductos() != null) {
            proveedor.setCategoriaProductos(updateDTO.getCategoriaProductos());
        }
        if (updateDTO.getCondicionesPago() != null) {
            proveedor.setCondicionesPago(updateDTO.getCondicionesPago());
        }
        if (updateDTO.getDescuentoComercial() != null) {
            proveedor.setDescuentoComercial(updateDTO.getDescuentoComercial());
        }
        if (updateDTO.getActivo() != null) {
            proveedor.setActivo(updateDTO.getActivo());
        }
        if (updateDTO.getObservaciones() != null) {
            proveedor.setObservaciones(updateDTO.getObservaciones());
        }
    }
}