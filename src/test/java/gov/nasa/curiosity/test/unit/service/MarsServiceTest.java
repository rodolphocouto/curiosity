package gov.nasa.curiosity.test.unit.service;

import gov.nasa.curiosity.domain.exception.PositionOutOfBoundsException;
import gov.nasa.curiosity.domain.model.Bounds;
import gov.nasa.curiosity.domain.model.Direction;
import gov.nasa.curiosity.domain.model.Movement;
import gov.nasa.curiosity.domain.model.Position;
import gov.nasa.curiosity.domain.service.MarsService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Unit testes for mars services.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class MarsServiceTest {

    private final Bounds bounds = Bounds.of(0, 5, 0, 5);

    @Test
    public void shouldMove1PositionForwardFromLeftBottom() {
        final Position initialPosition = Position.of(0, 0, Direction.NORTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(0, 1, Direction.NORTH), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEastFromLeftBottom() {
        final Position initialPosition = Position.of(0, 0, Direction.NORTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(1, 1, Direction.EAST), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEastAnd1PositionToSouthFromLeftBottom() {
        final Position initialPosition = Position.of(0, 0, Direction.NORTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(1, 0, Direction.SOUTH), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEastAnd1PositionToSouthAnd1PositionToWestFromLeftBottom() {
        final Position initialPosition = Position.of(0, 0, Direction.NORTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(0, 0, Direction.WEST), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardFromRightBottom() {
        final Position initialPosition = Position.of(5, 0, Direction.NORTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(5, 1, Direction.NORTH), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToWestFromRightBottom() {
        final Position initialPosition = Position.of(5, 0, Direction.NORTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(4, 1, Direction.WEST), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToWestAnd1PositionToSouthFromRightBottom() {
        final Position initialPosition = Position.of(5, 0, Direction.NORTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(4, 0, Direction.SOUTH), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToWestAnd1PositionToSouthAnd1PositionToEastFromRightBottom() {
        final Position initialPosition = Position.of(5, 0, Direction.NORTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(5, 0, Direction.EAST), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardFromLeftTop() {
        final Position initialPosition = Position.of(0, 5, Direction.SOUTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(0, 4, Direction.SOUTH), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEastFromLeftTop() {
        final Position initialPosition = Position.of(0, 5, Direction.SOUTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(1, 4, Direction.EAST), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEastAnd1PositionToSouthFromLeftTop() {
        final Position initialPosition = Position.of(0, 5, Direction.SOUTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(1, 5, Direction.NORTH), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEastAnd1PositionToSouthAnd1PositionToWestFromLeftTop() {
        final Position initialPosition = Position.of(0, 5, Direction.SOUTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE,
                Movement.LEFT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(0, 5, Direction.WEST), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardFromRightTop() {
        final Position initialPosition = Position.of(5, 5, Direction.SOUTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(5, 4, Direction.SOUTH), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToWestFromRightTop() {
        final Position initialPosition = Position.of(5, 5, Direction.SOUTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(4, 4, Direction.WEST), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToWestAnd1PositionToSouthFromRightTop() {
        final Position initialPosition = Position.of(5, 5, Direction.SOUTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(4, 5, Direction.NORTH), finalPosition);
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToWestAnd1PositionToSouthAnd1PositionToEastFromRightTop() {
        final Position initialPosition = Position.of(5, 5, Direction.SOUTH);
        final Position finalPosition = new MarsService(bounds, initialPosition).move(Arrays.asList(
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE,
                Movement.RIGHT,
                Movement.MOVE
        ));

        Assert.assertEquals(Position.of(5, 5, Direction.EAST), finalPosition);
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveForwardFromNorth() {
        final Position initialPosition = Position.of(0, 0, Direction.NORTH);

        try {
            new MarsService(bounds, initialPosition).move(Arrays.asList(
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE
            ));
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(0, 6, Direction.NORTH), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (0,6,N) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveForwardFromEast() {
        final Position initialPosition = Position.of(0, 0, Direction.EAST);

        try {
            new MarsService(bounds, initialPosition).move(Arrays.asList(
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE
            ));
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(6, 0, Direction.EAST), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (6,0,E) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveForwardFromSouth() {
        final Position initialPosition = Position.of(0, 5, Direction.SOUTH);

        try {
            new MarsService(bounds, initialPosition).move(Arrays.asList(
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE
            ));
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(0, -1, Direction.SOUTH), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (0,-1,S) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = PositionOutOfBoundsException.class)
    public void shouldThrowsPositionOutOfBoundsExceptionWhenMoveForwardFromWest() {
        final Position initialPosition = Position.of(5, 0, Direction.WEST);

        try {
            new MarsService(bounds, initialPosition).move(Arrays.asList(
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE,
                    Movement.MOVE
            ));
        } catch (PositionOutOfBoundsException ex) {
            Assert.assertEquals(Position.of(-1, 0, Direction.WEST), ex.getPosition());
            Assert.assertEquals(bounds, ex.getBounds());
            Assert.assertEquals("The (-1,0,W) position is out of the bounds (0 to 5, 0 to 5)", ex.getMessage());
            throw ex;
        }
    }
}
