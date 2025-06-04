//package com.example.msalmacen.repository;
//
//import com.example.msalmacen.entity.ProductoFinal;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface ProductoFinalRepository extends JpaRepository<ProductoFinal, Long> {
//    Optional<ProductoFinal> findByCodigo(String codigo);
//}

package com.example.msalmacen.repository;

import com.example.msalmacen.entity.ProductoFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoFinalRepository extends JpaRepository<ProductoFinal, Long> {

    // Buscar por código
    Optional<ProductoFinal> findByCodigo(String codigo);

    // Verificar si existe por código
    boolean existsByCodigo(String codigo);

    // Buscar productos activos
    List<ProductoFinal> findByActivoTrue();

    // Buscar por nombre (ignorando mayúsculas/minúsculas)
    List<ProductoFinal> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por tipo de tela
    List<ProductoFinal> findByTipoTelaIgnoreCase(String tipoTela);

    // Buscar por color
    List<ProductoFinal> findByColorIgnoreCase(String color);

    // Buscar productos con stock bajo (stock actual <= stock mínimo)
    @Query("SELECT p FROM ProductoFinal p WHERE p.stockActual <= p.stockMinimo AND p.activo = true")
    List<ProductoFinal> findProductosBajoStock();

    // Buscar productos por rango de precio
    @Query("SELECT p FROM ProductoFinal p WHERE p.precioMetro BETWEEN :precioMin AND :precioMax AND p.activo = true")
    List<ProductoFinal> findByRangoPrecio(@Param("precioMin") Double precioMin, @Param("precioMax") Double precioMax);

    // Buscar productos con stock mayor a cierta cantidad
    @Query("SELECT p FROM ProductoFinal p WHERE p.stockActual > :stockMinimo AND p.activo = true")
    List<ProductoFinal> findByStockMayorA(@Param("stockMinimo") Integer stockMinimo);

    // Buscar productos por múltiples criterios
    @Query("SELECT p FROM ProductoFinal p WHERE " +
            "(:tipoTela IS NULL OR p.tipoTela = :tipoTela) AND " +
            "(:color IS NULL OR p.color = :color) AND " +
            "(:activo IS NULL OR p.activo = :activo)")
    List<ProductoFinal> findByMultiplesCriterios(@Param("tipoTela") String tipoTela,
                                                 @Param("color") String color,
                                                 @Param("activo") Boolean activo);

    // Contar productos por tipo de tela
    @Query("SELECT COUNT(p) FROM ProductoFinal p WHERE p.tipoTela = :tipoTela AND p.activo = true")
    Long countByTipoTela(@Param("tipoTela") String tipoTela);

    // Obtener productos ordenados por stock ascendente
    @Query("SELECT p FROM ProductoFinal p WHERE p.activo = true ORDER BY p.stockActual ASC")
    List<ProductoFinal> findAllOrderByStockAsc();

    // Obtener productos más recientes
    @Query("SELECT p FROM ProductoFinal p WHERE p.activo = true ORDER BY p.fechaRegistro DESC")
    List<ProductoFinal> findAllOrderByFechaRegistroDesc();
}