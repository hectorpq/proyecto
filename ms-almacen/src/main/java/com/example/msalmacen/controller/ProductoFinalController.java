package com.example.msalmacen.controller;

import com.example.msalmacen.dto.ProductoFinalDTO;
import com.example.msalmacen.service.ProductoFinalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoFinalController {


    @Autowired
    private ProductoFinalService service;

    @PostMapping
    public ProductoFinalDTO guardar(@RequestBody ProductoFinalDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping
    public List<ProductoFinalDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{codigo}")
    public ProductoFinalDTO obtener(@PathVariable String codigo) {
        return service.obtenerPorCodigo(codigo);
    }
}
