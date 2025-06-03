package com.example.msalmacen.service;

import com.example.msalmacen.dto.ProductoFinalDTO;

import java.util.List;

public interface ProductoFinalService {
    ProductoFinalDTO guardar(ProductoFinalDTO dto);
    List<ProductoFinalDTO> listar();
    ProductoFinalDTO obtenerPorCodigo(String codigo);
}
