package gov.nasa.curiosity.domain.exception;

import gov.nasa.curiosity.domain.model.Bounds;
import gov.nasa.curiosity.domain.model.Position;

/**
 * Position out of bounds.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class PositionOutOfBoundsException extends RuntimeException {

    private final Position position;
    private final Bounds bounds;

    public PositionOutOfBoundsException(Position position, Bounds bounds) {
        this.position = position;
        this.bounds = bounds;
    }

    public Position getPosition() {
        return position;
    }

    public Bounds getBounds() {
        return bounds;
    }

    @Override
    public String getMessage() {
        return String.format("The %s position is out of the bounds %s", position, bounds);
    }
}
