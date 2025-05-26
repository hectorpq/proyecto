//package com.example.ms.proveedor.util;
//
//import com.example.ms.proveedor.entity.Proveedor;
//import com.example.ms.proveedor.repository.ProveedorRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
////@Profile("dev")
//@Profile("development")
//class ProveedorSeeder implements CommandLineRunner {
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




package com.example.ms.proveedor.util;

import com.example.ms.proveedor.entity.Proveedor;
import com.example.ms.proveedor.entity.Proveedor.TipoProveedor;
import com.example.ms.proveedor.repository.ProveedorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class ProveedorDataLoader implements CommandLineRunner {

    private final ProveedorRepository proveedorRepository;

    public ProveedorDataLoader(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public void run(String... args) {
        if (proveedorRepository.count() == 0) {
            Proveedor proveedor = new Proveedor();
            proveedor.setCodigoProveedor("PROV-001");
            proveedor.setNombreEmpresa("Ejemplo S.A.C.");
            proveedor.setNombreContacto("Juan Pérez");
            proveedor.setTelefono("+51 999999999");
            proveedor.setEmail("contacto@ejemplo.com");
            proveedor.setDireccion("Av. Siempre Viva 123");
            proveedor.setCiudad("Lima");
            proveedor.setPais("Perú");
            proveedor.setCodigoPostal("15001");
            proveedor.setRfcNit("123456789");
            proveedor.setTipoProveedor(TipoProveedor.NACIONAL);
            proveedor.setCategoriaProductos("Tecnología");
            proveedor.setCondicionesPago("30 días");
            proveedor.setDescuentoComercial(10.0);
            proveedor.setActivo(true);
            proveedor.setObservaciones("Proveedor confiable desde 2020");

            proveedorRepository.save(proveedor);
            System.out.println("Proveedor de ejemplo guardado.");
        }
    }
}
