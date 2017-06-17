package gov.nasa.curiosity.test.unit.model;

import gov.nasa.curiosity.domain.model.Direction;
import gov.nasa.curiosity.domain.model.Position;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit testes for rover position.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class PositionTest {

    @Test
    public void shouldCreatePosition() {
        final Position position = Position.of(5, 10, Direction.WEST);

        Assert.assertEquals(5, position.getX());
        Assert.assertEquals(10, position.getY());
        Assert.assertEquals(Direction.WEST, position.getDirection());
    }

    @Test
    public void shouldCreateNegativePosition() {
        final Position position = Position.of(-2, -20, Direction.EAST);

        Assert.assertEquals(-2, position.getX());
        Assert.assertEquals(-20, position.getY());
        Assert.assertEquals(Direction.EAST, position.getDirection());
    }

    @Test
    public void shouldConvertPositionToString() {
        final Position position = Position.of(0, 8, Direction.NORTH);
        Assert.assertEquals("(0,8,N)", position.toString());
    }

    @Test
    public void shouldConvertNegativePositionToString() {
        final Position position = Position.of(-10, -5, Direction.SOUTH);
        Assert.assertEquals("(-10,-5,S)", position.toString());
    }

    @Test
    public void shouldEquals() {
        Assert.assertTrue(Position.of(0, 0, Direction.NORTH).equals(Position.of(0, 0, Direction.NORTH)));
    }

    @Test
    public void shouldXNotEquals() {
        Assert.assertFalse(Position.of(0, 0, Direction.NORTH).equals(Position.of(1, 0, Direction.NORTH)));
    }

    @Test
    public void shouldYNotEquals() {
        Assert.assertFalse(Position.of(0, 0, Direction.NORTH).equals(Position.of(0, 1, Direction.NORTH)));
    }

    @Test
    public void shouldDirectionNotEquals() {
        Assert.assertFalse(Position.of(0, 0, Direction.NORTH).equals(Position.of(0, 0, Direction.SOUTH)));
    }
}
