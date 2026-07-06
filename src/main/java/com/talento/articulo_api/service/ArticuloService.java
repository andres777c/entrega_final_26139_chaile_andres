package com.talento.articulo_api.service;

import com.talento.articulo_api.model.Articulo;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de la capa de negocio para la gestión de artículos.
 *
 * <p>Define las operaciones disponibles sin atarlas a una implementación
 * concreta, de modo que el controlador dependa de esta abstracción y no de
 * los detalles de acceso a datos.</p>
 */
public interface ArticuloService {

    /**
     * Obtiene el listado completo de artículos.
     *
     * @return lista con todos los artículos registrados
     */
    List<Articulo> listarArticulos();

    /**
     * Busca un artículo por su identificador.
     *
     * @param id identificador a buscar
     * @return el artículo envuelto en un {@link Optional}, o vacío si no existe
     */
    Optional<Articulo> obtenerArticuloPorId(Long id);

    /**
     * Persiste un nuevo artículo.
     *
     * @param articulo datos del artículo a guardar
     * @return el artículo ya persistido, con su id asignado
     */
    Articulo guardarArticulo(Articulo articulo);

    /**
     * Actualiza los datos de un artículo existente.
     *
     * @param id       identificador del artículo a modificar
     * @param articulo nuevos datos del artículo
     * @return el artículo actualizado
     */
    Articulo actualizarArticulo(Long id, Articulo articulo);

    /**
     * Elimina un artículo por su identificador.
     *
     * @param id identificador del artículo a eliminar
     */
    void eliminarArticulo(Long id);
}
