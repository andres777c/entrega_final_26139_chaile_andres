package com.talento.articulo_api.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un artículo persistido en la tabla {@code articulo}.
 *
 * <p>Cada instancia equivale a un registro de la base de datos. JPA se encarga
 * de mapear los atributos de esta clase a las columnas correspondientes.</p>
 */
@Entity
@Table(name = "articulo")
public class Articulo {

    /** Identificador único, generado por la base de datos de forma autoincremental. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre descriptivo del artículo. */
    private String nombre;

    /** Precio unitario del artículo. */
    private Double precio;

    /** Constructor vacío requerido por JPA. */
    public Articulo() {
    }

    /**
     * Crea un artículo con todos sus datos.
     *
     * @param id     identificador del artículo
     * @param nombre nombre del artículo
     * @param precio precio del artículo
     */
    public Articulo(Long id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
