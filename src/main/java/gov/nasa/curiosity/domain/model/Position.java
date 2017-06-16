package gov.nasa.curiosity.domain.model;

import java.util.Objects;

/**
 * Rover position.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class Position {

    private final int x;
    private final int y;
    private final Direction direction;

    private Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public static Position of(int x, int y, Direction direction) {
        return new Position(x, y, direction);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x)
                && Objects.equals(y, position.y)
                && Objects.equals(direction, position.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%s)", x, y, direction);
    }
}
