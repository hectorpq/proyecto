package com.example.ms.proveedor.repository;


import com.example.ms.proveedor.entity.Proveedor;
import com.example.ms.proveedor.entity.Proveedor.TipoProveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // Buscar por código de proveedor
    Optional<Proveedor> findByCodigoProveedor(String codigoProveedor);

    // Verificar si existe un código de proveedor
    boolean existsByCodigoProveedor(String codigoProveedor);

    // Verificar si existe un código excluyendo un ID específico (para actualizaciones)
    boolean existsByCodigoProveedorAndIdNot(String codigoProveedor, Long id);

    // Buscar por nombre de empresa (case insensitive)
    List<Proveedor> findByNombreEmpresaContainingIgnoreCase(String nombreEmpresa);

    // Buscar proveedores activos
    List<Proveedor> findByActivoTrue();

    // Buscar proveedores por tipo
    List<Proveedor> findByTipoProveedor(TipoProveedor tipoProveedor);

    // Buscar proveedores activos por tipo
    List<Proveedor> findByActivoTrueAndTipoProveedor(TipoProveedor tipoProveedor);

    // Buscar por categoría de productos
    List<Proveedor> findByCategoriaProductosContainingIgnoreCase(String categoria);

    // Buscar por ciudad
    List<Proveedor> findByCiudadIgnoreCase(String ciudad);

    // Buscar por país
    List<Proveedor> findByPaisIgnoreCase(String pais);

    // Búsqueda general con paginación
    @Query("SELECT p FROM Proveedor p WHERE " +
            "(:searchTerm IS NULL OR " +
            "LOWER(p.nombreEmpresa) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.codigoProveedor) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.nombreContacto) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<Proveedor> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

    // Búsqueda con filtros múltiples
    @Query("SELECT p FROM Proveedor p WHERE " +
            "(:activo IS NULL OR p.activo = :activo) AND " +
            "(:tipoProveedor IS NULL OR p.tipoProveedor = :tipoProveedor) AND " +
            "(:ciudad IS NULL OR LOWER(p.ciudad) = LOWER(:ciudad)) AND " +
            "(:pais IS NULL OR LOWER(p.pais) = LOWER(:pais))")
    Page<Proveedor> findWithFilters(@Param("activo") Boolean activo,
                                    @Param("tipoProveedor") TipoProveedor tipoProveedor,
                                    @Param("ciudad") String ciudad,
                                    @Param("pais") String pais,
                                    Pageable pageable);

    // Contar proveedores activos
    long countByActivoTrue();

    // Contar proveedores por tipo
    long countByTipoProveedor(TipoProveedor tipoProveedor);
}