package com.example.msalmacen.service.impl;

import com.example.msalmacen.dto.ProductoFinalDTO;
import com.example.msalmacen.entity.ProductoFinal;
import com.example.msalmacen.repository.ProductoFinalRepository;
import com.example.msalmacen.service.ProductoFinalService;
import com.example.msalmacen.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoFinalServiceImpl implements ProductoFinalService {

    @Autowired
    private ProductoFinalRepository repository;

    @Autowired
    private MapperUtil mapper;

    @Override
    public ProductoFinalDTO guardar(ProductoFinalDTO dto) {
        ProductoFinal entity = mapper.dtoToEntity(dto);
        return mapper.entityToDto(repository.save(entity));
    }

    @Override
    public List<ProductoFinalDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public ProductoFinalDTO obtenerPorCodigo(String codigo) {
        return repository.findByCodigo(codigo)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}