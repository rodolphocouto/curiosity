package gov.nasa.curiosity.domain.model;

import gov.nasa.curiosity.domain.exception.UnrecognizedDirectionException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Rover direction.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public enum Direction {

    NORTH('N', 0, 1) {
        @Override
        public Direction getLeft() {
            return WEST;
        }

        @Override
        public Direction getRight() {
            return EAST;
        }
    },
    EAST('E', 1, 0) {
        @Override
        public Direction getLeft() {
            return NORTH;
        }

        @Override
        public Direction getRight() {
            return SOUTH;
        }
    },
    SOUTH('S', 0, -1) {
        @Override
        public Direction getLeft() {
            return EAST;
        }

        @Override
        public Direction getRight() {
            return WEST;
        }
    },
    WEST('W', -1, 0) {
        @Override
        public Direction getLeft() {
            return SOUTH;
        }

        @Override
        public Direction getRight() {
            return NORTH;
        }
    };

    private final char initial;
    private final int x;
    private final int y;

    Direction(char initial, int x, int y) {
        this.initial = initial;
        this.x = x;
        this.y = y;
    }

    public abstract Direction getLeft();

    public abstract Direction getRight();

    public static Direction ofInitial(char initial) throws UnrecognizedDirectionException {
        return Arrays.stream(Direction.values())
                .filter(d -> Objects.equals(initial, d.getInitial()))
                .findFirst()
                .orElseThrow(() -> new UnrecognizedDirectionException(initial));
    }

    public char getInitial() {
        return initial;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.valueOf(initial);
    }
}
