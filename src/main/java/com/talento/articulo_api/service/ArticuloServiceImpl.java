package com.talento.articulo_api.service;

import com.talento.articulo_api.model.Articulo;
import com.talento.articulo_api.repository.ArticuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de {@link ArticuloService}.
 *
 * <p>Delega las operaciones de persistencia en {@link ArticuloRepository},
 * que se recibe por inyección de dependencias a través del constructor.</p>
 */
@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository articuloRepository;

    /**
     * @param articuloRepository repositorio inyectado por Spring
     */
    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Override
    public List<Articulo> listarArticulos() {
        return articuloRepository.findAll();
    }

    @Override
    public Optional<Articulo> obtenerArticuloPorId(Long id) {
        return articuloRepository.findById(id);
    }

    @Override
    public Articulo guardarArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    public Articulo actualizarArticulo(Long id, Articulo articulo) {
        // Fijamos el id recibido para que save() actualice el registro existente
        // en lugar de crear uno nuevo.
        articulo.setId(id);
        return articuloRepository.save(articulo);
    }

    @Override
    public void eliminarArticulo(Long id) {
        articuloRepository.deleteById(id);
    }
}
