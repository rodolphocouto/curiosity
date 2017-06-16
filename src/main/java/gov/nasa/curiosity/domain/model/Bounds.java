package gov.nasa.curiosity.domain.model;

import java.util.Objects;

/**
 * Bounds of area.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class Bounds {

    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    private Bounds(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public static Bounds of(int minX, int maxX, int minY, int maxY) {
        if (minX > maxX || minY > maxY) {
            throw new IllegalArgumentException("Min value cannot be greater than max value");
        }

        return new Bounds(minX, maxX, minY, maxY);
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bounds bounds = (Bounds) o;
        return Objects.equals(minX, bounds.minX)
                && Objects.equals(maxX, bounds.maxX)
                && Objects.equals(minY, bounds.minY)
                && Objects.equals(maxY, bounds.maxY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minX, maxX, minY, maxY);
    }

    @Override
    public String toString() {
        return String.format("(%d to %d, %d to %d)", minX, maxX, minY, maxY);
    }
}
