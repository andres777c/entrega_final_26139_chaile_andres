package com.talento.articulo_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para toda la API.
 *
 * <p>Centraliza el tratamiento de errores para que los controladores no tengan
 * que ocuparse de armar las respuestas de error. Cada método interceptor
 * traduce un tipo de excepción a una respuesta HTTP con un cuerpo JSON
 * uniforme.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Construye el cuerpo JSON común a todas las respuestas de error.
     *
     * @param estado  código de estado HTTP a devolver
     * @param mensaje descripción legible del error
     * @return mapa con los datos del error, serializado como JSON por Spring
     */
    private Map<String, Object> construirCuerpo(HttpStatus estado, String mensaje) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("timestamp", LocalDateTime.now());
        cuerpo.put("status", estado.value());
        cuerpo.put("error", estado.getReasonPhrase());
        cuerpo.put("mensaje", mensaje);
        return cuerpo;
    }

    /**
     * Maneja el caso en que se busca un recurso inexistente.
     *
     * @param ex excepción con el detalle del recurso no encontrado
     * @return respuesta 404 con el mensaje del error
     */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(construirCuerpo(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    /**
     * Maneja el caso en que un parámetro de la URL no puede convertirse al tipo
     * esperado (por ejemplo, un id que no es un número).
     *
     * @param ex excepción de conversión de tipo lanzada por Spring
     * @return respuesta 400 con un mensaje explicativo
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> manejarTipoInvalido(MethodArgumentTypeMismatchException ex) {
        String mensaje = "El parámetro '" + ex.getName() + "' debe ser un número. Valor recibido: '"
                + ex.getValue() + "'";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(construirCuerpo(HttpStatus.BAD_REQUEST, mensaje));
    }
}
