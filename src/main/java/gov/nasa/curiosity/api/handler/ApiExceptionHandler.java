package gov.nasa.curiosity.api.handler;

import gov.nasa.curiosity.domain.exception.PositionOutOfBoundsException;
import gov.nasa.curiosity.domain.exception.UnrecognizedMovementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Exception handler.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UnrecognizedMovementException.class)
    public ResponseEntity<String> handleUnrecognizedMovementException(UnrecognizedMovementException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(PositionOutOfBoundsException.class)
    public ResponseEntity<String> handlePositionOutOfBoundsException(PositionOutOfBoundsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public void handleNotFoundException() {
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void handleHttpRequestMethodNotSupportedException() {
    }
}
