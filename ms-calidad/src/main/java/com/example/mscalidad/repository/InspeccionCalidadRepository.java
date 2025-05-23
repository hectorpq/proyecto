package com.example.mscalidad.repository;


import com.example.mscalidad.entity.InspeccionCalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspeccionCalidadRepository extends JpaRepository<InspeccionCalidad, Long> {

    List<InspeccionCalidad> findByCodigoProducto(String codigoProducto);

    List<InspeccionCalidad> findByResultado(String resultado);
}
