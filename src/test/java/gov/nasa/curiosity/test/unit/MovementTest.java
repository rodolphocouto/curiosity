package gov.nasa.curiosity.test.unit;

import gov.nasa.curiosity.domain.exception.UnrecognizedMovementException;
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
        final Position position = Movement.LEFT.move(Position.of(0, 0, Direction.NORTH));

        Assert.assertEquals(0, position.getX());
        Assert.assertEquals(0, position.getY());
        Assert.assertEquals(Direction.WEST, position.getDirection());
    }

    @Test
    public void shouldMoveToRightFromNorth() {
        final Position position = Movement.RIGHT.move(Position.of(2, 5, Direction.NORTH));

        Assert.assertEquals(2, position.getX());
        Assert.assertEquals(5, position.getY());
        Assert.assertEquals(Direction.EAST, position.getDirection());
    }

    @Test
    public void shouldMoveForwardFromNorth() {
        final Position position = Movement.MOVE.move(Position.of(0, 0, Direction.NORTH));

        Assert.assertEquals(0, position.getX());
        Assert.assertEquals(1, position.getY());
        Assert.assertEquals(Direction.NORTH, position.getDirection());
    }

    @Test
    public void shouldMoveForwardFromNorthWithNegativeCoordinates() {
        final Position position = Movement.MOVE.move(Position.of(-1, -1, Direction.NORTH));

        Assert.assertEquals(-1, position.getX());
        Assert.assertEquals(0, position.getY());
        Assert.assertEquals(Direction.NORTH, position.getDirection());
    }

    @Test
    public void shouldMoveToLeftFromEast() {
        final Position position = Movement.LEFT.move(Position.of(20, 2, Direction.EAST));

        Assert.assertEquals(20, position.getX());
        Assert.assertEquals(2, position.getY());
        Assert.assertEquals(Direction.NORTH, position.getDirection());
    }

    @Test
    public void shouldMoveToRightFromEast() {
        final Position position = Movement.RIGHT.move(Position.of(7, 9, Direction.EAST));

        Assert.assertEquals(7, position.getX());
        Assert.assertEquals(9, position.getY());
        Assert.assertEquals(Direction.SOUTH, position.getDirection());
    }

    @Test
    public void shouldMoveForwardFromEast() {
        final Position position = Movement.MOVE.move(Position.of(5, 10, Direction.EAST));

        Assert.assertEquals(6, position.getX());
        Assert.assertEquals(10, position.getY());
        Assert.assertEquals(Direction.EAST, position.getDirection());
    }

    @Test
    public void shouldMoveForwardFromEastWithNegativeCoordinates() {
        final Position position = Movement.MOVE.move(Position.of(-5, -10, Direction.EAST));

        Assert.assertEquals(-4, position.getX());
        Assert.assertEquals(-10, position.getY());
        Assert.assertEquals(Direction.EAST, position.getDirection());
    }

    @Test
    public void shouldMoveToLeftFromSouth() {
        final Position position = Movement.LEFT.move(Position.of(15, 25, Direction.SOUTH));

        Assert.assertEquals(15, position.getX());
        Assert.assertEquals(25, position.getY());
        Assert.assertEquals(Direction.EAST, position.getDirection());
    }

    @Test
    public void shouldMoveToRightFromSouth() {
        final Position position = Movement.RIGHT.move(Position.of(99, 999, Direction.SOUTH));

        Assert.assertEquals(99, position.getX());
        Assert.assertEquals(999, position.getY());
        Assert.assertEquals(Direction.WEST, position.getDirection());
    }

    @Test
    public void shouldMoveForwardFromSouth() {
        final Position position = Movement.MOVE.move(Position.of(3, 2, Direction.SOUTH));

        Assert.assertEquals(3, position.getX());
        Assert.assertEquals(1, position.getY());
        Assert.assertEquals(Direction.SOUTH, position.getDirection());
    }

    @Test
    public void shouldMoveForwardFromSouthWithNegativeCoordinates() {
        final Position position = Movement.MOVE.move(Position.of(-3, -2, Direction.SOUTH));

        Assert.assertEquals(-3, position.getX());
        Assert.assertEquals(-3, position.getY());
        Assert.assertEquals(Direction.SOUTH, position.getDirection());
    }

    @Test
    public void shouldMoveToLeftFromWest() {
        final Position position = Movement.LEFT.move(Position.of(17, 33, Direction.WEST));

        Assert.assertEquals(17, position.getX());
        Assert.assertEquals(33, position.getY());
        Assert.assertEquals(Direction.SOUTH, position.getDirection());
    }

    @Test
    public void shouldMoveToRightFromWest() {
        final Position position = Movement.RIGHT.move(Position.of(56, 78, Direction.WEST));

        Assert.assertEquals(56, position.getX());
        Assert.assertEquals(78, position.getY());
        Assert.assertEquals(Direction.NORTH, position.getDirection());
    }

    @Test
    public void shouldMoveForwardFromWest() {
        final Position position = Movement.MOVE.move(Position.of(3, 4, Direction.WEST));

        Assert.assertEquals(2, position.getX());
        Assert.assertEquals(4, position.getY());
        Assert.assertEquals(Direction.WEST, position.getDirection());
    }

    @Test
    public void shouldMoveForwardFromWestWithNegativeCoordinates() {
        final Position position = Movement.MOVE.move(Position.of(-3, -4, Direction.WEST));

        Assert.assertEquals(-4, position.getX());
        Assert.assertEquals(-4, position.getY());
        Assert.assertEquals(Direction.WEST, position.getDirection());
    }
}
