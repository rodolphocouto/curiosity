package gov.nasa.curiosity.test.unit;

import gov.nasa.curiosity.domain.exception.UnrecognizedMovementException;
import gov.nasa.curiosity.domain.model.Movement;
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
}
