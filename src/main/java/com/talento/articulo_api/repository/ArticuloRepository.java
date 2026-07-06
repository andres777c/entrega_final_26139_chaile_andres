package com.talento.articulo_api.repository;

import com.talento.articulo_api.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a datos para la entidad {@link Articulo}.
 *
 * <p>Al extender {@link JpaRepository}, Spring Data genera automáticamente en
 * tiempo de ejecución la implementación con las operaciones CRUD más comunes,
 * sin necesidad de escribir consultas SQL:</p>
 *
 * <ul>
 *   <li>{@code findAll()} — devuelve todos los artículos.</li>
 *   <li>{@code findById(Long id)} — busca un artículo por su identificador.</li>
 *   <li>{@code save(Articulo a)} — inserta o actualiza un artículo.</li>
 *   <li>{@code deleteById(Long id)} — elimina un artículo por su identificador.</li>
 *   <li>{@code existsById(Long id)} — indica si existe un artículo con ese id.</li>
 * </ul>
 *
 * <p>Si más adelante se necesitan búsquedas específicas, basta con declarar
 * métodos siguiendo la convención de nombres de Spring Data (por ejemplo,
 * {@code findByNombre(String nombre)}) y la consulta se deriva sola.</p>
 */
@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
}
