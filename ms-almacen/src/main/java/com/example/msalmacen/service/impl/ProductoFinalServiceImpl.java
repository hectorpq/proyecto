package com.example.msalmacen.service.impl;

import com.example.msalmacen.client.ProveedorClient;
import com.example.msalmacen.dto.ProductoFinalDTO;
import com.example.msalmacen.dto.ProveedorDTO;
import com.example.msalmacen.entity.ProductoFinal;
import com.example.msalmacen.repository.ProductoFinalRepository;
import com.example.msalmacen.service.ProductoFinalService;
import com.example.msalmacen.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoFinalServiceImpl implements ProductoFinalService {

    @Autowired
    private ProductoFinalRepository repository;

    @Autowired
    private ProveedorClient proveedorClient;

    @Autowired
    private MapperUtil mapper;
    @Autowired
    private ProductoFinalRepository productoFinalRepository;

//    @Override
//    public ProductoFinalDTO guardar(ProductoFinalDTO dto) {
//        ProductoFinal entity = mapper.dtoToEntity(dto);
//        return mapper.entityToDto(repository.save(entity));
//    }
//
//    @Override
//    public List<ProductoFinalDTO> listar() {
//        return repository.findAll().stream()
//                .map(mapper::entityToDto)
//                .toList();
//    }
//
//    @Override
//    public ProductoFinalDTO obtenerPorCodigo(String codigo) {
//        return repository.findByCodigo(codigo)
//                .map(mapper::entityToDto)
//                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
//    }
//
//
//
//
//
//
//    public ProveedorDTO obtenerInformacionProveedor(Long proveedorId) {
//        try {
//            return proveedorClient.obtenerProveedor(proveedorId);
//        } catch (Exception e) {
//            throw new RuntimeException("Error al obtener información del proveedor: " + e.getMessage());
//        }
//    }
//
//    public List<ProveedorDTO> obtenerTodosLosProveedores() {
//        try {
//            return proveedorClient.obtenerTodosProveedores();
//        } catch (Exception e) {
//            throw new RuntimeException("Error al obtener lista de proveedores: " + e.getMessage());
//        }
//    }
//
//    public List<ProveedorDTO> obtenerProveedoresActivos() {
//        try {
//            return proveedorClient.obtenerProveedoresActivos();
//        } catch (Exception e) {
//            throw new RuntimeException("Error al obtener proveedores activos: " + e.getMessage());
//        }
//    }
//
//    // Ejemplo de método que combina datos de productos con información del proveedor
//    public String obtenerDetalleProductoConProveedor(String codigoProducto, Long proveedorId) {
//        try {
//            ProveedorDTO proveedor = proveedorClient.obtenerProveedor(proveedorId);
//
//            // Aquí puedes combinar la información del producto con la del proveedor
//            return String.format("Producto: %s - Proveedor: %s (%s)",
//                    codigoProducto,
//                    proveedor.getNombreEmpresa(),
//                    proveedor.getCiudad());
//        } catch (Exception e) {
//            return "Error al obtener información: " + e.getMessage();
//        }
//    }
//}


    @Override
    public ProductoFinalDTO guardar(ProductoFinalDTO dto) {
        try {
            ProductoFinal producto = convertirAEntidad(dto);

            // Establecer fecha de registro si no existe
            if (producto.getFechaRegistro() == null) {
                producto.setFechaRegistro(LocalDateTime.now());
            }

            // Establecer activo por defecto
            if (producto.getActivo() == null) {
                producto.setActivo(true);
            }

            // Validar que el código no exista
            if (productoFinalRepository.existsByCodigo(dto.getCodigo())) {
                throw new RuntimeException("Ya existe un producto con el código: " + dto.getCodigo());
            }

            ProductoFinal productoGuardado = productoFinalRepository.save(producto);
            return convertirADTO(productoGuardado);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el producto: " + e.getMessage());
        }
    }

    @Override
    public List<ProductoFinalDTO> listar() {
        try {
            return productoFinalRepository.findAll()
                    .stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar productos: " + e.getMessage());
        }
    }

    @Override
    public ProductoFinalDTO obtenerPorCodigo(String codigo) {
        try {
            ProductoFinal producto = productoFinalRepository.findByCodigo(codigo)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con código: " + codigo));
            return convertirADTO(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener producto: " + e.getMessage());
        }
    }

    // Métodos adicionales que pueden ser útiles
    public List<ProductoFinalDTO> listarActivos() {
        try {
            return productoFinalRepository.findByActivoTrue()
                    .stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar productos activos: " + e.getMessage());
        }
    }

    public List<ProductoFinalDTO> obtenerProductosBajoStock() {
        try {
            return productoFinalRepository.findProductosBajoStock()
                    .stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener productos bajo stock: " + e.getMessage());
        }
    }

    public ProductoFinalDTO actualizarStock(String codigo, Integer nuevoStock) {
        try {
            ProductoFinal producto = productoFinalRepository.findByCodigo(codigo)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            producto.setStockActual(nuevoStock);
            ProductoFinal productoActualizado = productoFinalRepository.save(producto);

            return convertirADTO(productoActualizado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar stock: " + e.getMessage());
        }
    }

    // Método que integra información del proveedor
    public String obtenerInfoProductoConProveedor(String codigoProducto, Long proveedorId) {
        try {
            ProductoFinalDTO producto = obtenerPorCodigo(codigoProducto);
            ProveedorDTO proveedor = proveedorClient.obtenerProveedor(proveedorId);

            return String.format(
                    "Producto: %s (%s) - Stock: %d %s - Proveedor: %s - Ciudad: %s",
                    producto.getNombre(),
                    producto.getCodigo(),
                    producto.getStockActual(),
                    producto.getUnidad(),
                    proveedor.getNombreEmpresa(),
                    proveedor.getCiudad()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener información completa: " + e.getMessage());
        }
    }

    // Método para verificar proveedores disponibles
    public List<ProveedorDTO> obtenerProveedoresDisponibles() {
        try {
            return proveedorClient.obtenerProveedoresActivos();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proveedores: " + e.getMessage());
        }
    }

    // Métodos de conversión
    private ProductoFinal convertirAEntidad(ProductoFinalDTO dto) {
        return ProductoFinal.builder()
                .codigo(dto.getCodigo())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .tipoTela(dto.getTipoTela())
                .color(dto.getColor())
                .anchoCm(dto.getAnchoCm())
                .pesoGsm(dto.getPesoGsm())
                .precioMetro(dto.getPrecioMetro())
                .stockActual(dto.getStockActual() != null ? dto.getStockActual() : 0)
                .stockMinimo(dto.getStockMinimo() != null ? dto.getStockMinimo() : 0)
                .unidad(dto.getUnidad() != null ? dto.getUnidad() : "METROS")
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .fechaRegistro(dto.getFechaRegistro() != null ? dto.getFechaRegistro() : LocalDateTime.now())
                .build();
    }

    private ProductoFinalDTO convertirADTO(ProductoFinal entity) {
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
}