package com.example.mscalidad.controller;

import com.example.mscalidad.entity.InspeccionCalidad;
import com.example.mscalidad.service.InspeccionCalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspecciones")
public class InspeccionCalidadController {

    @Autowired
    private InspeccionCalidadService service;

    @GetMapping
    public ResponseEntity<List<InspeccionCalidad>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspeccionCalidad> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{codigo}")
    public ResponseEntity<List<InspeccionCalidad>> obtenerPorCodigoProducto(@PathVariable String codigo) {
        return ResponseEntity.ok(service.obtenerPorCodigoProducto(codigo));
    }

    @GetMapping("/resultado/{resultado}")
    public ResponseEntity<List<InspeccionCalidad>> obtenerPorResultado(@PathVariable String resultado) {
        return ResponseEntity.ok(service.obtenerPorResultado(resultado));
    }

    @PostMapping
    public ResponseEntity<InspeccionCalidad> registrar(@RequestBody InspeccionCalidad inspeccion) {
        return ResponseEntity.ok(service.registrar(inspeccion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
