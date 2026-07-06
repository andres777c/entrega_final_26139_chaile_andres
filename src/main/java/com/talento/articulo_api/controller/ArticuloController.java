package com.talento.articulo_api.controller;

import com.talento.articulo_api.exception.RecursoNoEncontradoException;
import com.talento.articulo_api.model.Articulo;
import com.talento.articulo_api.service.ArticuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone las operaciones sobre artículos.
 *
 * <p>Todas las rutas cuelgan de {@code /api/articulos}. Cada método traduce una
 * petición HTTP a la operación correspondiente de {@link ArticuloService} y
 * devuelve la respuesta con el código de estado adecuado. Los errores (id
 * inexistente o id no numérico) los resuelve el manejador global de
 * excepciones.</p>
 */
@CrossOrigin(origins = "*") // Habilita el consumo de la API desde cualquier origen (CORS)
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;

    /**
     * @param articuloService servicio de negocio inyectado por Spring
     */
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    /**
     * Lista todos los artículos.
     *
     * @return colección de artículos (GET /api/articulos)
     */
    @GetMapping
    public List<Articulo> listar() {
        return articuloService.listarArticulos();
    }

    /**
     * Devuelve un artículo puntual.
     *
     * @param id identificador buscado
     * @return el artículo encontrado (GET /api/articulos/{id})
     * @throws RecursoNoEncontradoException si no existe un artículo con ese id
     */
    @GetMapping("/{id}")
    public Articulo obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró un artículo con el id " + id));
    }

    /**
     * Registra un nuevo artículo.
     *
     * @param articulo datos enviados en el cuerpo de la petición
     * @return el artículo creado, ya con su id (POST /api/articulos)
     */
    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo) {
        return articuloService.guardarArticulo(articulo);
    }

    /**
     * Modifica un artículo existente.
     *
     * @param id       identificador del artículo a actualizar
     * @param articulo nuevos datos del artículo
     * @return el artículo actualizado (PUT /api/articulos/{id})
     * @throws RecursoNoEncontradoException si no existe un artículo con ese id
     */
    @PutMapping("/{id}")
    public Articulo actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            throw new RecursoNoEncontradoException("No se encontró un artículo con el id " + id);
        }
        return articuloService.actualizarArticulo(id, articulo);
    }

    /**
     * Elimina un artículo.
     *
     * @param id identificador del artículo a eliminar
     * @return respuesta 204 sin contenido si se eliminó (DELETE /api/articulos/{id})
     * @throws RecursoNoEncontradoException si no existe un artículo con ese id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            throw new RecursoNoEncontradoException("No se encontró un artículo con el id " + id);
        }
        articuloService.eliminarArticulo(id);
        return ResponseEntity.noContent().build();
    }
}
