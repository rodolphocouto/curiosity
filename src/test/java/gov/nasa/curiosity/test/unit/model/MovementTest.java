package gov.nasa.curiosity.test.unit.model;

import gov.nasa.curiosity.domain.exception.PositionOutOfBoundsException;
import gov.nasa.curiosity.domain.exception.UnrecognizedMovementException;
import gov.nasa.curiosity.domain.model.Bounds;
import gov.nasa.curiosity.domain.model.Direction;
import gov.nasa.curiosity.domain.model.Movement;
import gov.nasa.curiosity.domain.model.Position;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit testes for rover movement.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class MovementTest {

    @Test
    public void shouldConvertLeftInitialToString() {
        Assert.assertEquals('L', Movement.LEFT.getInitial());
        Assert.assertEquals("L", Movement.LEFT.toString());
    }

    @Test
    public void shouldConvertRightInitialToString() {
        Assert.assertEquals('R', Movement.RIGHT.getInitial());
        Assert.assertEquals("R", Movement.RIGHT.toString());
    }

    @Test
    public void shouldConvertMoveInitialToString() {
        Assert.assertEquals('M', Movement.MOVE.getInitial());
        Assert.assertEquals("M", Movement.MOVE.toString());
    }

    @Test
    public void shouldConvertInitialToLeft() {
        Assert.assertEquals(Movement.LEFT, Movement.ofInitial('L'));
    }

    @Test
    public void shouldConvertInitialToRight() {
        Assert.assertEquals(Movement.RIGHT, Movement.ofInitial('R'));
    }

    @Test
    public void shouldConvertInitialToMove() {
        Assert.assertEquals(Movement.MOVE, Movement.ofInitial('M'));
    }

    @Test(expected = UnrecognizedMovementException.class)
    public void shouldThrowsUnrecognizedMovementException() {
        try {
            Movement.ofInitial('A');
        } catch (UnrecognizedMovementException ex) {
            Assert.assertEquals('A', ex.getMovement());
            Assert.assertEquals("'A' is not a recognized movement. Consider just these: (L,R,M)", ex.getMessage());
            throw ex;
        }
    }

    @Test
    public void shouldMoveToLeftFromNorth() {
        final Position position = Movement.LEFT.move(Position.of(0, 0, Direction.NORTH), Bounds.of(0, 1, 0, 1));
        Assert.assertEquals(Position.of(0, 0, Direction.WEST), position);
    }

    @Test
    public void shouldMoveToRightFromNorth() {
        final Position position = Movement.RIGHT.move(Position.of(2, 5, Direction.NORTH), Bounds.of(0, 2, 0, 5));
        Assert.assertEquals(Position.of(2, 5, Direction.EAST), position);
    }

    @Test
    public void shouldMoveForwardFromNorth() {
        final Position position = Movement.MOVE.move(Position.of(0, 0, Direction.NORTH), Bounds.of(0, 1, 0, 1));
        Assert.assertEquals(Position.of(0, 1, Direction.NORTH), position);
    }

    @Test
    public void shouldMoveForwardFromNorthWithNegativeCoordinates() {
        final Position position = Movement.MOVE.move(Position.of(-1, -1, Direction.NORTH), Bounds.of(-2, 0, -2, 0));
        Assert.assertEquals(Position.of(-1, 0, Direction.NORTH), position);
    }

    @Test
    public void shouldMoveToLeftFromEast() {
        final Position position = Movement.LEFT.move(Position.of(20, 2, Direction.EAST), Bounds.of(0, 20, 0, 2));
        Assert.assertEquals(Position.of(20, 2, Direction.NORTH), position);
    }

    @Test
    public void shouldMoveToRightFromEast() {
        final Position position = Movement.RIGHT.move(Position.of(7, 9, Direction.EAST), Bounds.of(0, 7, 0, 9));
        Assert.assertEquals(Position.of(7, 9, Direction.SOUTH), position);
    }

    @Test
    public void shouldMoveForwardFromEast() {
        final Position position = Movement.MOVE.move(Position.of(5, 10, Direction.EAST), Bounds.of(0, 6, 0, 10));
        Assert.assertEquals(Position.of(6, 10, Direction.EAST), position);
    }

    @Test
    public void shouldMoveForwardFromEastWithNegativeCoordinates() {
        final Position position = Movement.MOVE.move(Position.of(-5, -10, Direction.EAST), Bounds.of(-5, 0, -10, 0));
        Assert.assertEquals(Position.of(-4, -10, Direction.EAST), position);
    }

    @Test
    public void shouldMoveToLeftFromSouth() {
        final Position position = Movement.LEFT.move(Position.of(15, 25, Direction.SOUTH), Bounds.of(0, 15, 0, 25));
        Assert.assertEquals(Position.of(15, 25, Direction.EAST), position);
    }

    @Test
    public void shouldMoveToRightFromSouth() {
        final Position position = Movement.RIGHT.move(Position.of(99, 999, Direction.SOUTH), Bounds.of(0, 100, 0, 1000));
        Assert.assertEquals(Position.of(99, 999, Direction.WEST), position);
    }

    @Test
    public void shouldMoveForwardFromSouth() {
        final Position position = Movement.MOVE.move(Position.of(3, 2, Direction.SOUTH), Bounds.of(0, 5, 0, 3));
        Assert.assertEquals(Position.of(3, 1, Direction.SOUTH), position);
    }

    @Test
    public void shouldMoveForwardFromSouthWithNegativeCoordinates() {
        final Position position = Movement.MOVE.move(Position.of(-3, -2, Direction.SOUTH), Bounds.of(-5, -1, -5, -1));
        Assert.assertEquals(Position.of(-3, -3, Direction.SOUTH), position);
    }

    @Test
    public void shouldMoveToLeftFromWest() {
        final Position position = Movement.LEFT.move(Position.of(17, 33, Direction.WEST), Bounds.of(0, 20, 0, 35));
        Assert.assertEquals(Position.of(17, 33, Direction.SOUTH), position);
    }

    @Test
    public void shouldMoveToRightFromWest() {
        final Position position = Movement.RIGHT.move(Position.of(56, 78, Direction.WEST), Bounds.of(0, 60, 0, 80));
        Assert.assertEquals(Position.of(56, 78, Direction.NORTH), position);
    }

    @Test
    public void shouldMoveForwardFromWest() {
        final Position position = Movement.MOVE.move(Position.of(3, 4, Direction.WEST), Bounds.of(0, 5, 0, 5));
        Assert.assertEquals(Position.of(2, 4, Direction.WEST), position);
    }

    @Test
    public void shouldMoveForwardFromWestWithNegativeCoordinates() {
        final Position position = Movement.MOVE.move(Position.of(-3, -4, Direction.WEST), Bounds.of(-5, 0, -5, 0));
        Assert.assertEquals(Position.of(-4, -4, Direction.WEST), position);
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveToLeftFromNorth() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.LEFT.move(Position.of(0, 6, Direction.NORTH), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(0, 6, Direction.WEST), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (0,6,W) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveToRightFromNorth() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.RIGHT.move(Position.of(0, 6, Direction.NORTH), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(0, 6, Direction.EAST), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (0,6,E) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveForwardFromNorth() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.MOVE.move(Position.of(0, 5, Direction.NORTH), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(0, 6, Direction.NORTH), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (0,6,N) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveToLeftFromEast() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.LEFT.move(Position.of(6, 0, Direction.EAST), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(6, 0, Direction.NORTH), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (6,0,N) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveToRightFromEast() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.RIGHT.move(Position.of(6, 0, Direction.EAST), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(6, 0, Direction.SOUTH), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (6,0,S) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveForwardFromEast() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.MOVE.move(Position.of(5, 0, Direction.EAST), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(6, 0, Direction.EAST), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (6,0,E) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveToLeftFromSouth() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.LEFT.move(Position.of(0, -1, Direction.SOUTH), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(0, -1, Direction.EAST), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (0,-1,E) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveToRightFromSouth() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.RIGHT.move(Position.of(0, -1, Direction.SOUTH), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(0, -1, Direction.WEST), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (0,-1,W) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveForwardFromSouth() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.MOVE.move(Position.of(0, 0, Direction.SOUTH), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(0, -1, Direction.SOUTH), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (0,-1,S) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveToLeftFromWest() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.LEFT.move(Position.of(-1, 0, Direction.WEST), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(-1, 0, Direction.SOUTH), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (-1,0,S) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveToRightFromWest() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.RIGHT.move(Position.of(-1, 0, Direction.WEST), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(-1, 0, Direction.NORTH), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (-1,0,N) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveForwardFromWest() {
        final Bounds bounds = Bounds.of(0, 5, 0, 5);

        try {
            Movement.MOVE.move(Position.of(0, 0, Direction.WEST), bounds);
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(-1, 0, Direction.WEST), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (-1,0,W) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }
}
