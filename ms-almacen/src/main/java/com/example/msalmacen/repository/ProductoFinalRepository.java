package com.example.msalmacen.repository;

import com.example.msalmacen.entity.ProductoFinal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoFinalRepository extends JpaRepository<ProductoFinal, Long> {
    Optional<ProductoFinal> findByCodigo(String codigo);
}
