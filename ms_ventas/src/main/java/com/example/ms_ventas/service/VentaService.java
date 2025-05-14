package com.example.ms_ventas.service;

import com.example.ms_ventas.entity.Venta;
import java.util.List;

public interface VentaService {
    List<Venta> listar();
    Venta obtenerPorId(Integer id);
    Venta guardar(Venta venta);
    Venta actualizar(Integer id, Venta venta);
    void eliminar(Integer id);
}
