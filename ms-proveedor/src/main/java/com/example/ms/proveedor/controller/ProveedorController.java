package com.example.ms.proveedor.controller;

import com.example.ms.proveedor.dto.ProveedorCreateDTO;
import com.example.ms.proveedor.dto.ProveedorResponseDTO;
import com.example.ms.proveedor.dto.ProveedorUpdateDTO;
import com.example.ms.proveedor.entity.Proveedor.TipoProveedor;
import com.example.ms.proveedor.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
//@Slf4j
@Tag(name = "Proveedores", description = "API para gestión de proveedores")
public class ProveedorController {

    private static final Logger log = LoggerFactory.getLogger(ProveedorController.class);




//    @GetMapping
//    public String ejemplo() {
//        log.info("Mensaje de ejemplo");
//        return "OK sigue asi mijo";
//    }


    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    @Operation(summary = "Crear un nuevo proveedor")
    public ResponseEntity<ProveedorResponseDTO> crearProveedor(@Valid @RequestBody ProveedorCreateDTO createDTO) {
        log.info("POST /api/v1/proveedores - Creando proveedor: {}", createDTO.getCodigoProveedor());

        ProveedorResponseDTO responseDTO = proveedorService.crearProveedor(createDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID")
    public ResponseEntity<ProveedorResponseDTO> obtenerProveedorPorId(
            @Parameter(description = "ID del proveedor") @PathVariable Long id) {
        log.info("GET /api/v1/proveedores/{} - Obteniendo proveedor", id);

        ProveedorResponseDTO responseDTO = proveedorService.obtenerProveedorPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/codigo/{codigo}")
    @Operation(summary = "Obtener proveedor por código")
    public ResponseEntity<ProveedorResponseDTO> obtenerProveedorPorCodigo(
            @Parameter(description = "Código del proveedor") @PathVariable String codigo) {
        log.info("GET /api/v1/proveedores/codigo/{} - Obteniendo proveedor", codigo);

        ProveedorResponseDTO responseDTO = proveedorService.obtenerProveedorPorCodigo(codigo);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todos los proveedores con paginación")
    public ResponseEntity<Page<ProveedorResponseDTO>> listarProveedores(
            @Parameter(description = "Número de página") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Campo de ordenamiento") @RequestParam(defaultValue = "nombreEmpresa") String sortBy,
            @Parameter(description = "Dirección de ordenamiento") @RequestParam(defaultValue = "asc") String sortDir) {
        log.info("GET /api/v1/proveedores - Listando proveedores con paginación");

        Page<ProveedorResponseDTO> proveedores = proveedorService.listarProveedoresConPaginacion(page, size, sortBy, sortDir);
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/todos")
    @Operation(summary = "Listar todos los proveedores sin paginación")
    public ResponseEntity<List<ProveedorResponseDTO>> listarTodosLosProveedores() {
        log.info("GET /api/v1/proveedores/todos - Listando todos los proveedores");

        List<ProveedorResponseDTO> proveedores = proveedorService.listarTodosLosProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/activos")
    @Operation(summary = "Listar proveedores activos")
    public ResponseEntity<List<ProveedorResponseDTO>> listarProveedoresActivos() {
        log.info("GET /api/v1/proveedores/activos - Listando proveedores activos");

        List<ProveedorResponseDTO> proveedores = proveedorService.listarProveedoresActivos();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar proveedores")
    public ResponseEntity<Page<ProveedorResponseDTO>> buscarProveedores(
            @Parameter(description = "Término de búsqueda") @RequestParam String searchTerm,
            @Parameter(description = "Número de página") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size) {
        log.info("GET /api/v1/proveedores/buscar - Buscando proveedores: {}", searchTerm);

        Page<ProveedorResponseDTO> proveedores = proveedorService.buscarProveedores(searchTerm, page, size);
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/filtrar")
    @Operation(summary = "Filtrar proveedores")
    public ResponseEntity<Page<ProveedorResponseDTO>> filtrarProveedores(
            @Parameter(description = "Estado activo") @RequestParam(required = false) Boolean activo,
            @Parameter(description = "Tipo de proveedor") @RequestParam(required = false) TipoProveedor tipoProveedor,
            @Parameter(description = "Ciudad") @RequestParam(required = false) String ciudad,
            @Parameter(description = "País") @RequestParam(required = false) String pais,
            @Parameter(description = "Número de página") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size) {
        log.info("GET /api/v1/proveedores/filtrar - Filtrando proveedores");

        Page<ProveedorResponseDTO> proveedores = proveedorService.filtrarProveedores(
                activo, tipoProveedor, ciudad, pais, page, size);
        return ResponseEntity.ok(proveedores);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor")
    public ResponseEntity<ProveedorResponseDTO> actualizarProveedor(
            @Parameter(description = "ID del proveedor") @PathVariable Long id,
            @Valid @RequestBody ProveedorUpdateDTO updateDTO) {
        log.info("PUT /api/v1/proveedores/{} - Actualizando proveedor", id);

        ProveedorResponseDTO responseDTO = proveedorService.actualizarProveedor(id, updateDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado del proveedor")
    public ResponseEntity<ProveedorResponseDTO> cambiarEstadoProveedor(
            @Parameter(description = "ID del proveedor") @PathVariable Long id,
            @Parameter(description = "Nuevo estado") @RequestParam Boolean activo) {
        log.info("PATCH /api/v1/proveedores/{}/estado - Cambiando estado a: {}", id, activo);

        ProveedorResponseDTO responseDTO = proveedorService.cambiarEstadoProveedor(id, activo);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor (soft delete)")
    public ResponseEntity<Void> eliminarProveedor(
            @Parameter(description = "ID del proveedor") @PathVariable Long id) {
        log.info("DELETE /api/v1/proveedores/{} - Eliminando proveedor", id);

        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/permanente")
    @Operation(summary = "Eliminar proveedor permanentemente")
    public ResponseEntity<Void> eliminarProveedorPermanentemente(
            @Parameter(description = "ID del proveedor") @PathVariable Long id) {
        log.info("DELETE /api/v1/proveedores/{}/permanente - Eliminando proveedor permanentemente", id);

        proveedorService.eliminarProveedorPermanentemente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estadisticas")
    @Operation(summary = "Obtener estadísticas de proveedores")
    public ResponseEntity<ProveedorService.ProveedorStatsDTO> obtenerEstadisticas() {
        log.info("GET /api/v1/proveedores/estadisticas - Obteniendo estadísticas");

        ProveedorService.ProveedorStatsDTO stats = proveedorService.obtenerEstadisticas();
        return ResponseEntity.ok(stats);
    }
}