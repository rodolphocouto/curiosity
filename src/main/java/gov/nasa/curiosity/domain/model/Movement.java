package gov.nasa.curiosity.domain.model;

import gov.nasa.curiosity.domain.exception.PositionOutOfBoundsException;
import gov.nasa.curiosity.domain.exception.UnrecognizedMovementException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Rover movement.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public enum Movement {

    LEFT('L') {
        @Override
        public Position move(Position position, Bounds bounds) {
            return validatePosition(Position.of(
                    position.getX(),
                    position.getY(),
                    position.getDirection().getLeft()),
                    bounds);
        }
    },
    RIGHT('R') {
        @Override
        public Position move(Position position, Bounds bounds) {
            return validatePosition(Position.of(
                    position.getX(),
                    position.getY(),
                    position.getDirection().getRight()),
                    bounds);
        }
    },
    MOVE('M') {
        @Override
        public Position move(Position position, Bounds bounds) throws PositionOutOfBoundsException {
            return validatePosition(Position.of(
                    position.getX() + position.getDirection().getX(),
                    position.getY() + position.getDirection().getY(),
                    position.getDirection()),
                    bounds);
        }
    };

    private final char initial;

    Movement(char initial) {
        this.initial = initial;
    }

    public abstract Position move(Position position, Bounds bounds) throws PositionOutOfBoundsException;

    public static Movement ofInitial(char initial) throws UnrecognizedMovementException {
        return Arrays.stream(Movement.values())
                .filter(m -> Objects.equals(initial, m.getInitial()))
                .findFirst()
                .orElseThrow(() -> new UnrecognizedMovementException(initial));
    }

    private static Position validatePosition(Position position, Bounds bounds) {
        if (position.getX() < bounds.getMinX()
                || position.getX() > bounds.getMaxX()
                || position.getY() < bounds.getMinY()
                || position.getY() > bounds.getMaxY()) {
            throw new PositionOutOfBoundsException(position, bounds);
        }

        return position;
    }

    public char getInitial() {
        return initial;
    }

    @Override
    public String toString() {
        return String.valueOf(initial);
    }
}
