package com.consultora.ligapadel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Clase que controla las excepciones globales.
 * Usa el patro AOP (Aspect-Orient Programming) mediante la anotación @RestControllerAdvice
 * Centralizamos excepciones en esta clase para no tener que controlarlas en cada linea de código necesaria.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> manejarIllegalArgumentException(IllegalArgumentException e) {
        Map<String, String> respuesta = Map.of("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> manejarRuntimeException(RuntimeException e) {
        Map<String, String> respuesta = Map.of("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
