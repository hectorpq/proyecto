package com.example.ms.proveedor.repository;


import com.example.ms.proveedor.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Proveedor, Long> {
    boolean existsByDocumentNumber(String documentNumber);
    Optional<Proveedor> findByDocumentNumber(String documentNumber);
}