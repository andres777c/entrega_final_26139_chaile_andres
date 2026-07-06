package com.talento.articulo_api.exception;

/**
 * Excepción que se lanza cuando se solicita un recurso que no existe en la base
 * de datos (por ejemplo, un artículo con un id inexistente).
 *
 * <p>Al extender {@link RuntimeException} no obliga a declararla con
 * {@code throws}, y será interceptada por el manejador global de errores para
 * devolver una respuesta HTTP 404 con un mensaje descriptivo.</p>
 */
public class RecursoNoEncontradoException extends RuntimeException {

    /**
     * @param mensaje detalle de qué recurso no se encontró
     */
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
