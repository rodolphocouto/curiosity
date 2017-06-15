package gov.nasa.curiosity.domain.model;

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
        public Position move(Position position) {
            return Position.of(
                    position.getX(),
                    position.getY(),
                    position.getDirection().getLeft());
        }
    },
    RIGHT('R') {
        @Override
        public Position move(Position position) {
            return Position.of(
                    position.getX(),
                    position.getY(),
                    position.getDirection().getRight());
        }
    },
    MOVE('M') {
        @Override
        public Position move(Position position) {
            return Position.of(
                    position.getX() + position.getDirection().getX(),
                    position.getY() + position.getDirection().getY(),
                    position.getDirection());
        }
    };

    private final char initial;

    Movement(char initial) {
        this.initial = initial;
    }

    public abstract Position move(Position position);

    public static Movement ofInitial(char initial) throws UnrecognizedMovementException {
        return Arrays.stream(Movement.values())
                .filter(m -> Objects.equals(initial, m.getInitial()))
                .findFirst()
                .orElseThrow(() -> new UnrecognizedMovementException(initial));
    }

    public char getInitial() {
        return initial;
    }

    @Override
    public String toString() {
        return String.valueOf(initial);
    }
}
