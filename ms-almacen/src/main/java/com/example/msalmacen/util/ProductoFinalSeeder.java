package com.example.msalmacen.util;


import com.example.msalmacen.entity.ProductoFinal;
import com.example.msalmacen.repository.ProductoFinalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ProductoFinalSeeder implements CommandLineRunner {

    @Autowired
    private ProductoFinalRepository productoFinalRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productoFinalRepository.count() == 0) {
            ProductoFinal producto1 = new ProductoFinal();
            producto1.setCodigo("PF001");
            producto1.setNombre("Tela Denim Azul");
            producto1.setDescripcion("Tela resistente tipo denim de alta calidad.");
            producto1.setTipoTela("Denim");
            producto1.setColor("Azul");
            producto1.setAnchoCm(150.0);
            producto1.setPesoGsm(320.0);
            producto1.setPrecioMetro(new BigDecimal("25.50"));
            producto1.setStockActual(500);
            producto1.setStockMinimo(100);
            producto1.setUnidad("METROS");
            producto1.setActivo(true);
            producto1.setFechaRegistro(LocalDateTime.now());

            ProductoFinal producto2 = new ProductoFinal();
            producto2.setCodigo("PF002");
            producto2.setNombre("Tela Algodón Blanca");
            producto2.setDescripcion("Tela suave de algodón para camisas.");
            producto2.setTipoTela("Algodón");
            producto2.setColor("Blanco");
            producto2.setAnchoCm(140.0);
            producto2.setPesoGsm(220.0);
            producto2.setPrecioMetro(new BigDecimal("18.75"));
            producto2.setStockActual(300);
            producto2.setStockMinimo(80);
            producto2.setUnidad("METROS");
            producto2.setActivo(true);
            producto2.setFechaRegistro(LocalDateTime.now());

            productoFinalRepository.save(producto1);
            productoFinalRepository.save(producto2);

            System.out.println("Seeder de productos finales ejecutado correctamente.");
        }
    }
}