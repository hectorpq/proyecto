package com.example.ms_ventas.client;

import com.example.ms_ventas.dto.CatalogoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalogo-service")
public interface CatalogoClient {
    @GetMapping("/api/catalogo/{id}")
    CatalogoDTO obtenerCatalogo(@PathVariable("id") Long id);
}
