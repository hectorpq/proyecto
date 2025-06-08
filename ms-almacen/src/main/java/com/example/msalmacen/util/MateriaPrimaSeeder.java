package com.example.msalmacen.util;

import com.example.msalmacen.entity.MateriaPrima;
import com.example.msalmacen.repository.MateriaPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class MateriaPrimaSeeder implements CommandLineRunner {

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (materiaPrimaRepository.count() == 0) {
            MateriaPrima mp1 = new MateriaPrima();
            mp1.setCodigo("MP001");
            mp1.setNombre("Tela Algodón");
            mp1.setDescripcion("Tela suave 100% algodón para camisetas.");
            mp1.setTipoTela("Algodón");
            mp1.setColor("Blanco");
            mp1.setAnchoCm(150.0);
            mp1.setPesoGsm(160.0);
            mp1.setPrecioMetro(new BigDecimal("12.50"));
            mp1.setStockActual(300);
            mp1.setStockMinimo(50);
            mp1.setUnidad("METROS");
            mp1.setActivo(true);
            mp1.setFechaRegistro(LocalDateTime.now());

            MateriaPrima mp2 = new MateriaPrima();
            mp2.setCodigo("MP002");
            mp2.setNombre("Tela Poliéster");
            mp2.setDescripcion("Tela sintética para ropa deportiva.");
            mp2.setTipoTela("Poliéster");
            mp2.setColor("Negro");
            mp2.setAnchoCm(140.0);
            mp2.setPesoGsm(130.0);
            mp2.setPrecioMetro(new BigDecimal("9.80"));
            mp2.setStockActual(500);
            mp2.setStockMinimo(100);
            mp2.setUnidad("METROS");
            mp2.setActivo(true);
            mp2.setFechaRegistro(LocalDateTime.now());

            materiaPrimaRepository.save(mp1);
            materiaPrimaRepository.save(mp2);

            System.out.println("Seeder de materia prima ejecutado correctamente.");
        }
    }
}
