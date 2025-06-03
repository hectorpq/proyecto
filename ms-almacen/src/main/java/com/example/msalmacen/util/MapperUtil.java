package com.example.msalmacen.util;

import com.example.msalmacen.dto.ProductoFinalDTO;
import com.example.msalmacen.entity.ProductoFinal;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    public ProductoFinalDTO entityToDto(ProductoFinal entity) {
        return ProductoFinalDTO.builder()
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .tipoTela(entity.getTipoTela())
                .color(entity.getColor())
                .anchoCm(entity.getAnchoCm())
                .pesoGsm(entity.getPesoGsm())
                .precioMetro(entity.getPrecioMetro())
                .stockActual(entity.getStockActual())
                .stockMinimo(entity.getStockMinimo())
                .unidad(entity.getUnidad())
                .activo(entity.getActivo())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }

    public ProductoFinal dtoToEntity(ProductoFinalDTO dto) {
        return ProductoFinal.builder()
                .codigo(dto.getCodigo())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .tipoTela(dto.getTipoTela())
                .color(dto.getColor())
                .anchoCm(dto.getAnchoCm())
                .pesoGsm(dto.getPesoGsm())
                .precioMetro(dto.getPrecioMetro())
                .stockActual(dto.getStockActual())
                .stockMinimo(dto.getStockMinimo())
                .unidad(dto.getUnidad())
                .activo(dto.getActivo())
                .fechaRegistro(dto.getFechaRegistro())
                .build();
    }
}
