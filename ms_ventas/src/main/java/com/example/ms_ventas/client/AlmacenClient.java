package com.example.ms_ventas.client;

import com.example.ms_ventas.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-almacen-service")
public interface AlmacenClient {

    @GetMapping("/api/productos/{id}")
    ProductoDTO obtenerProductoPorId(@PathVariable("id") Long id);

    @PutMapping("/api/productos/{id}/descontar-stock")
    void descontarStock(@PathVariable("id") Long id, @RequestParam("cantidad") int cantidad);
}

