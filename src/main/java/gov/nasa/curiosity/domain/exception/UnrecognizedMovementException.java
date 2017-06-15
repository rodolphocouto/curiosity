package gov.nasa.curiosity.domain.exception;

import gov.nasa.curiosity.domain.model.Movement;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Unrecognized movement.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class UnrecognizedMovementException extends RuntimeException {

    private final char movement;

    public UnrecognizedMovementException(char movement) {
        this.movement = movement;
    }

    public char getMovement() {
        return movement;
    }

    @Override
    public String getMessage() {
        return String.format("'%c' is not a recognized movement. Consider just these: (%s)", movement,
                Arrays.stream(Movement.values())
                        .map(Movement::toString)
                        .collect(Collectors.joining(",")));
    }
}
