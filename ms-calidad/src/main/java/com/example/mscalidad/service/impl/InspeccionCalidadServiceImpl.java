package com.example.mscalidad.service;

import com.example.mscalidad.entity.InspeccionCalidad;
import com.example.mscalidad.repository.InspeccionCalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspeccionCalidadServiceImpl implements InspeccionCalidadService {

    @Autowired
    private InspeccionCalidadRepository repository;

    @Override
    public List<InspeccionCalidad> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public Optional<InspeccionCalidad> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<InspeccionCalidad> obtenerPorCodigoProducto(String codigoProducto) {
        return repository.findByCodigoProducto(codigoProducto);
    }

    @Override
    public List<InspeccionCalidad> obtenerPorResultado(String resultado) {
        return repository.findByResultado(resultado);
    }

    @Override
    public InspeccionCalidad registrar(InspeccionCalidad inspeccion) {
        return repository.save(inspeccion);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
