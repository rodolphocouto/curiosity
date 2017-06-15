package gov.nasa.curiosity.domain.exception;

import gov.nasa.curiosity.domain.model.Direction;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Unrecognized direction.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class UnrecognizedDirectionException extends RuntimeException {

    private final char direction;

    public UnrecognizedDirectionException(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return direction;
    }

    @Override
    public String getMessage() {
        return String.format("'%c' is not a recognized direction. Consider just these: (%s)", direction,
                Arrays.stream(Direction.values())
                        .map(Direction::toString)
                        .collect(Collectors.joining(",")));
    }
}
