package com.leo.teste.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controle centralizado para excessoes
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ContaNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(ContaNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    @ExceptionHandler({ContaJaExisteException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(ContaJaExisteException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
    @ExceptionHandler({TransacaoNaoRealizadaException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(TransacaoNaoRealizadaException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {

        exception.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ops... algo nao saiu bem :-( contate o administrador do sistema...");
    }
}
