//package com.example.msalmacen.controller;
//
//import com.example.msalmacen.dto.ProductoFinalDTO;
//import com.example.msalmacen.service.ProductoFinalService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/productos")
//@RequiredArgsConstructor
//public class ProductoFinalController {
//
//
//    @Autowired
//    private ProductoFinalService service;
//
//    @PostMapping
//    public ProductoFinalDTO guardar(@RequestBody ProductoFinalDTO dto) {
//        return service.guardar(dto);
//    }
//
//    @GetMapping
//    public List<ProductoFinalDTO> listar() {
//        return service.listar();
//    }
//
//    @GetMapping("/{codigo}")
//    public ProductoFinalDTO obtener(@PathVariable String codigo) {
//        return service.obtenerPorCodigo(codigo);
//    }
//}


package com.example.msalmacen.controller;

import com.example.msalmacen.dto.ProductoFinalDTO;
import com.example.msalmacen.dto.ProveedorDTO;
import com.example.msalmacen.service.ProductoFinalService;
import com.example.msalmacen.service.impl.ProductoFinalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoFinalController {

    @Autowired
    private ProductoFinalService productoFinalService;

    @Autowired
    private ProductoFinalServiceImpl productoFinalServiceImpl; // Para métodos adicionales

    @PostMapping
    public ResponseEntity<ProductoFinalDTO> crearProducto(@Valid @RequestBody ProductoFinalDTO productoDTO) {
        try {
            ProductoFinalDTO nuevoProducto = productoFinalService.guardar(productoDTO);
            return ResponseEntity.ok(nuevoProducto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoFinalDTO>> listarProductos() {
        try {
            List<ProductoFinalDTO> productos = productoFinalService.listar();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ProductoFinalDTO>> listarProductosActivos() {
        try {
            List<ProductoFinalDTO> productos = productoFinalServiceImpl.listarActivos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProductoFinalDTO> obtenerProductoPorCodigo(@PathVariable String codigo) {
        try {
            ProductoFinalDTO producto = productoFinalService.obtenerPorCodigo(codigo);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bajo-stock")
    public ResponseEntity<List<ProductoFinalDTO>> obtenerProductosBajoStock() {
        try {
            List<ProductoFinalDTO> productos = productoFinalServiceImpl.obtenerProductosBajoStock();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{codigo}/stock")
    public ResponseEntity<ProductoFinalDTO> actualizarStock(@PathVariable String codigo,
                                                            @RequestParam Integer nuevoStock) {
        try {
            ProductoFinalDTO producto = productoFinalServiceImpl.actualizarStock(codigo, nuevoStock);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoints que integran con el servicio de proveedores
    @GetMapping("/proveedores")
    public ResponseEntity<List<ProveedorDTO>> obtenerProveedoresDisponibles() {
        try {
            List<ProveedorDTO> proveedores = productoFinalServiceImpl.obtenerProveedoresDisponibles();
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{codigo}/proveedor/{proveedorId}/info")
    public ResponseEntity<String> obtenerInfoProductoConProveedor(@PathVariable String codigo,
                                                                  @PathVariable Long proveedorId) {
        try {
            String info = productoFinalServiceImpl.obtenerInfoProductoConProveedor(codigo, proveedorId);
            return ResponseEntity.ok(info);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Endpoint para obtener estadísticas del almacén
    @GetMapping("/estadisticas")
    public ResponseEntity<String> obtenerEstadisticas() {
        try {
            List<ProductoFinalDTO> todosProductos = productoFinalService.listar();
            List<ProductoFinalDTO> productosActivos = productoFinalServiceImpl.listarActivos();
            List<ProductoFinalDTO> productosBajoStock = productoFinalServiceImpl.obtenerProductosBajoStock();

            String estadisticas = String.format(
                    "Estadísticas del Almacén:\n" +
                            "- Total de productos: %d\n" +
                            "- Productos activos: %d\n" +
                            "- Productos con bajo stock: %d\n" +
                            "- Productos inactivos: %d",
                    todosProductos.size(),
                    productosActivos.size(),
                    productosBajoStock.size(),
                    todosProductos.size() - productosActivos.size()
            );

            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener estadísticas");
        }
    }
}