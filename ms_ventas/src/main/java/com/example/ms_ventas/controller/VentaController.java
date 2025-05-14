package com.example.ms_ventas.controller;

import com.example.ms_ventas.dto.VentaResponseDTO;
import com.example.ms_ventas.entity.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private List<Venta> ventas = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Venta registrarVenta(@RequestBody Venta venta) {
        venta.setId(ventas.size() + 1);
        ventas.add(venta);
        return venta;
    }

    @GetMapping
    public List<Venta> listarVentas() {
        return ventas;
    }

    @GetMapping("/{id}")
    public VentaResponseDTO obtenerVentaDetallada(@PathVariable Integer id) {
        Venta venta = ventas.stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);

        if (venta == null) {
            return null;
        }

        Object cliente = restTemplate.getForObject("http://ms-cliente-service/clientes/" + venta.getClienteId(), Object.class);
        Object categoria = restTemplate.getForObject("http://ms-catalogo-service/categorias/" + venta.getCategoriaId(), Object.class);

        return new VentaResponseDTO(cliente, categoria);
    }
}
