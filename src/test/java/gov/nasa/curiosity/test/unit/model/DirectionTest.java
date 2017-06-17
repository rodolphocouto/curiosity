package gov.nasa.curiosity.test.unit.model;

import gov.nasa.curiosity.domain.exception.UnrecognizedDirectionException;
import gov.nasa.curiosity.domain.model.Direction;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit testes for rover direction.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class DirectionTest {

    @Test
    public void shouldConvertNorthInitialToString() {
        Assert.assertEquals('N', Direction.NORTH.getInitial());
        Assert.assertEquals("N", Direction.NORTH.toString());
    }

    @Test
    public void shouldConvertEastInitialToString() {
        Assert.assertEquals('E', Direction.EAST.getInitial());
        Assert.assertEquals("E", Direction.EAST.toString());
    }

    @Test
    public void shouldConvertSouthInitialToString() {
        Assert.assertEquals('S', Direction.SOUTH.getInitial());
        Assert.assertEquals("S", Direction.SOUTH.toString());
    }

    @Test
    public void shouldConvertWestInitialToString() {
        Assert.assertEquals('W', Direction.WEST.getInitial());
        Assert.assertEquals("W", Direction.WEST.toString());
    }

    @Test
    public void northCoordinatesShouldBe0And1() {
        Assert.assertEquals(0, Direction.NORTH.getX());
        Assert.assertEquals(1, Direction.NORTH.getY());
    }

    @Test
    public void eastCoordinatesShouldBe1And0() {
        Assert.assertEquals(1, Direction.EAST.getX());
        Assert.assertEquals(0, Direction.EAST.getY());
    }

    @Test
    public void southCoordinatesShouldBe0And1Negative() {
        Assert.assertEquals(0, Direction.SOUTH.getX());
        Assert.assertEquals(-1, Direction.SOUTH.getY());
    }

    @Test
    public void westCoordinatesShouldBe1NegativeAnd0() {
        Assert.assertEquals(-1, Direction.WEST.getX());
        Assert.assertEquals(0, Direction.WEST.getY());
    }

    @Test
    public void northLeftShouldBeWest() {
        Assert.assertEquals(Direction.WEST, Direction.NORTH.getLeft());
    }

    @Test
    public void northRightShouldBeEast() {
        Assert.assertEquals(Direction.EAST, Direction.NORTH.getRight());
    }

    @Test
    public void eastLeftShouldBeNorth() {
        Assert.assertEquals(Direction.NORTH, Direction.EAST.getLeft());
    }

    @Test
    public void eastRightShouldBeSouth() {
        Assert.assertEquals(Direction.SOUTH, Direction.EAST.getRight());
    }

    @Test
    public void southLeftShouldBeEast() {
        Assert.assertEquals(Direction.EAST, Direction.SOUTH.getLeft());
    }

    @Test
    public void southRightShouldBeWest() {
        Assert.assertEquals(Direction.WEST, Direction.SOUTH.getRight());
    }

    @Test
    public void westLeftShouldBeSouth() {
        Assert.assertEquals(Direction.SOUTH, Direction.WEST.getLeft());
    }

    @Test
    public void westRightShouldBeNorth() {
        Assert.assertEquals(Direction.NORTH, Direction.WEST.getRight());
    }

    @Test(expected = UnrecognizedDirectionException.class)
    public void shouldThrowsUnrecognizedDirectionException() {
        try {
            Direction.ofInitial('A');
        } catch (UnrecognizedDirectionException ex) {
            Assert.assertEquals('A', ex.getDirection());
            Assert.assertEquals("'A' is not a recognized direction. Consider just these: (N,E,S,W)", ex.getMessage());
            throw ex;
        }
    }
}
