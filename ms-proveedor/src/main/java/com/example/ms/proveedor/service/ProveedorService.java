//package com.example.jpc.student.service;
//import com.example.jpc.student.service.StudentService;
//import java.util.List;
//import java.util.Optional;
//
//
//import com.example.jpc.student.dto.StudentDTO;
//import java.util.List;
//
//public interface StudentService {
//    List<StudentDTO> getAllStudents();
//    StudentDTO getStudentById(Long id);
//    StudentDTO createStudent(StudentDTO studentDTO);
//    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
//    void deleteStudent(Long id);
//}


package com.example.ms.proveedor.service;

import com.example.ms.proveedor.controller.ProveedorController;
import com.example.ms.proveedor.dto.ProveedorCreateDTO;
import com.example.ms.proveedor.dto.ProveedorResponseDTO;
import com.example.ms.proveedor.dto.ProveedorUpdateDTO;
import com.example.ms.proveedor.entity.Proveedor;
import com.example.ms.proveedor.entity.Proveedor.TipoProveedor;
import com.example.ms.proveedor.exception.ProveedorNotFoundException;
import com.example.ms.proveedor.exception.DuplicateProveedorException;
import com.example.ms.proveedor.mapper.ProveedorMapper;
import com.example.ms.proveedor.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Slf4j
@Transactional
public class ProveedorService {



    private static final Logger log = LoggerFactory.getLogger(ProveedorController.class);

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    // Crear proveedor
    public ProveedorResponseDTO crearProveedor(ProveedorCreateDTO createDTO) {
        log.info("Creando proveedor con código: {}", createDTO.getCodigoProveedor());

        // Verificar que no exista el código
        if (proveedorRepository.existsByCodigoProveedor(createDTO.getCodigoProveedor())) {
            throw new DuplicateProveedorException("Ya existe un proveedor con el código: " + createDTO.getCodigoProveedor());
        }

        Proveedor proveedor = proveedorMapper.toEntity(createDTO);
        Proveedor savedProveedor = proveedorRepository.save(proveedor);

        log.info("Proveedor creado exitosamente con ID: {}", savedProveedor.getId());
        return proveedorMapper.toResponseDTO(savedProveedor);
    }

    // Obtener proveedor por ID
    @Transactional(readOnly = true)
    public ProveedorResponseDTO obtenerProveedorPorId(Long id) {
        log.info("Buscando proveedor con ID: {}", id);

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException("Proveedor no encontrado con ID: " + id));

        return proveedorMapper.toResponseDTO(proveedor);
    }

    // Obtener proveedor por código
    @Transactional(readOnly = true)
    public ProveedorResponseDTO obtenerProveedorPorCodigo(String codigo) {
        log.info("Buscando proveedor con código: {}", codigo);

        Proveedor proveedor = proveedorRepository.findByCodigoProveedor(codigo)
                .orElseThrow(() -> new ProveedorNotFoundException("Proveedor no encontrado con código: " + codigo));

        return proveedorMapper.toResponseDTO(proveedor);
    }

    // Listar todos los proveedores
    @Transactional(readOnly = true)
    public List<ProveedorResponseDTO> listarTodosLosProveedores() {
        log.info("Listando todos los proveedores");

        List<Proveedor> proveedores = proveedorRepository.findAll(Sort.by("nombreEmpresa"));
        return proveedores.stream()
                .map(proveedorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Listar proveedores activos
    @Transactional(readOnly = true)
    public List<ProveedorResponseDTO> listarProveedoresActivos() {
        log.info("Listando proveedores activos");

        List<Proveedor> proveedores = proveedorRepository.findByActivoTrue();
        return proveedores.stream()
                .map(proveedorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Listar proveedores con paginación
    @Transactional(readOnly = true)
    public Page<ProveedorResponseDTO> listarProveedoresConPaginacion(int page, int size, String sortBy, String sortDir) {
        log.info("Listando proveedores con paginación - Page: {}, Size: {}", page, size);

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Proveedor> proveedoresPage = proveedorRepository.findAll(pageable);

        return proveedoresPage.map(proveedorMapper::toResponseDTO);
    }

    // Buscar proveedores
    @Transactional(readOnly = true)
    public Page<ProveedorResponseDTO> buscarProveedores(String searchTerm, int page, int size) {
        log.info("Buscando proveedores con término: {}", searchTerm);

        Pageable pageable = PageRequest.of(page, size, Sort.by("nombreEmpresa"));
        Page<Proveedor> proveedoresPage = proveedorRepository.findBySearchTerm(searchTerm, pageable);

        return proveedoresPage.map(proveedorMapper::toResponseDTO);
    }

    // Filtrar proveedores
    @Transactional(readOnly = true)
    public Page<ProveedorResponseDTO> filtrarProveedores(Boolean activo, TipoProveedor tipoProveedor,
                                                         String ciudad, String pais, int page, int size) {
        log.info("Filtrando proveedores - Activo: {}, Tipo: {}, Ciudad: {}, País: {}",
                activo, tipoProveedor, ciudad, pais);

        Pageable pageable = PageRequest.of(page, size, Sort.by("nombreEmpresa"));
        Page<Proveedor> proveedoresPage = proveedorRepository.findWithFilters(activo, tipoProveedor, ciudad, pais, pageable);

        return proveedoresPage.map(proveedorMapper::toResponseDTO);
    }

    // Actualizar proveedor
    public ProveedorResponseDTO actualizarProveedor(Long id, ProveedorUpdateDTO updateDTO) {
        log.info("Actualizando proveedor con ID: {}", id);

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException("Proveedor no encontrado con ID: " + id));

        proveedorMapper.updateEntityFromDTO(updateDTO, proveedor);
        Proveedor updatedProveedor = proveedorRepository.save(proveedor);

        log.info("Proveedor actualizado exitosamente con ID: {}", updatedProveedor.getId());
        return proveedorMapper.toResponseDTO(updatedProveedor);
    }

    // Activar/Desactivar proveedor
    public ProveedorResponseDTO cambiarEstadoProveedor(Long id, Boolean activo) {
        log.info("Cambiando estado del proveedor ID: {} a activo: {}", id, activo);

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException("Proveedor no encontrado con ID: " + id));

        proveedor.setActivo(activo);
        Proveedor updatedProveedor = proveedorRepository.save(proveedor);

        log.info("Estado del proveedor cambiado exitosamente");
        return proveedorMapper.toResponseDTO(updatedProveedor);
    }

    // Eliminar proveedor (soft delete)
    public void eliminarProveedor(Long id) {
        log.info("Eliminando proveedor con ID: {}", id);

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException("Proveedor no encontrado con ID: " + id));

        proveedor.setActivo(false);
        proveedorRepository.save(proveedor);

        log.info("Proveedor eliminado (desactivado) exitosamente");
    }

    // Eliminar proveedor permanentemente
    public void eliminarProveedorPermanentemente(Long id) {
        log.info("Eliminando permanentemente proveedor con ID: {}", id);

        if (!proveedorRepository.existsById(id)) {
            throw new ProveedorNotFoundException("Proveedor no encontrado con ID: " + id);
        }

        proveedorRepository.deleteById(id);
        log.info("Proveedor eliminado permanentemente");
    }

    // Obtener estadísticas
    @Transactional(readOnly = true)
    public ProveedorStatsDTO obtenerEstadisticas() {
        log.info("Obteniendo estadísticas de proveedores");

        long totalProveedores = proveedorRepository.count();
        long proveedoresActivos = proveedorRepository.countByActivoTrue();
        long proveedoresNacionales = proveedorRepository.countByTipoProveedor(TipoProveedor.NACIONAL);
        long proveedoresInternacionales = proveedorRepository.countByTipoProveedor(TipoProveedor.INTERNACIONAL);

        return new ProveedorStatsDTO(totalProveedores, proveedoresActivos,
                proveedoresNacionales, proveedoresInternacionales);
    }

    // DTO para estadísticas
    public static class ProveedorStatsDTO {
        private final long totalProveedores;
        private final long proveedoresActivos;
        private final long proveedoresNacionales;
        private final long proveedoresInternacionales;

        public ProveedorStatsDTO(long totalProveedores, long proveedoresActivos,
                                 long proveedoresNacionales, long proveedoresInternacionales) {
            this.totalProveedores = totalProveedores;
            this.proveedoresActivos = proveedoresActivos;
            this.proveedoresNacionales = proveedoresNacionales;
            this.proveedoresInternacionales = proveedoresInternacionales;
        }

        // Getters
        public long getTotalProveedores() { return totalProveedores; }
        public long getProveedoresActivos() { return proveedoresActivos; }
        public long getProveedoresNacionales() { return proveedoresNacionales; }
        public long getProveedoresInternacionales() { return proveedoresInternacionales; }
    }
}