//package com.example.ms.proveedor.util;
//
//import com.example.ms.proveedor.entity.Proveedor;
//import com.example.ms.proveedor.repository.ProveedorRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("dev")
//public class ProveedorSeeder implements CommandLineRunner {
//
//    private final ProveedorRepository proveedorRepository;
//
//    public ProveedorSeeder(ProveedorRepository proveedorRepository) {
//        this.proveedorRepository = proveedorRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (proveedorRepository.count() == 0) {
//            Proveedor proveedor1 = new Proveedor("PRV001", "Tech Solutions", "Luis González", "+51987654321", "contacto@tech.com", "Av. Perú 123");
//            Proveedor proveedor2 = new Proveedor("PRV002", "Distribuciones Globales", "María Fernández", "+51983456789", "ventas@distribuciones.com", "Calle Comercio 456");
//            Proveedor proveedor3 = new Proveedor("PRV003", "Fábrica XYZ", "Carlos Rojas", "+51985678901", "info@fxyz.com", "Jr. Industrial 789");
//
//            proveedorRepository.save(proveedor1);
//            proveedorRepository.save(proveedor2);
//            proveedorRepository.save(proveedor3);
//
//            System.out.println("✅ Proveedores insertados correctamente.");
//        } else {
//            System.out.println("⚠️ Los proveedores ya existen. No se insertaron datos.");
//        }
//    }
//}
