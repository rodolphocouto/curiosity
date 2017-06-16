package gov.nasa.curiosity.test.unit;

import gov.nasa.curiosity.domain.model.Bounds;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit testes for bounds of area.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class BoundsTest {

    @Test
    public void shouldCreateBounds() {
        final Bounds bounds = Bounds.of(0, 10, 0, 20);

        Assert.assertEquals(0, bounds.getMinX());
        Assert.assertEquals(10, bounds.getMaxX());
        Assert.assertEquals(0, bounds.getMinY());
        Assert.assertEquals(20, bounds.getMaxY());
    }

    @Test
    public void shouldCreateNegativeBounds() {
        final Bounds bounds = Bounds.of(-10, -2, -20, -10);

        Assert.assertEquals(-10, bounds.getMinX());
        Assert.assertEquals(-2, bounds.getMaxX());
        Assert.assertEquals(-20, bounds.getMinY());
        Assert.assertEquals(-10, bounds.getMaxY());
    }

    @Test
    public void shouldConvertBoundsToString() {
        final Bounds bounds = Bounds.of(0, 10, 0, 20);
        Assert.assertEquals("(0 to 10, 0 to 20)", bounds.toString());
    }

    @Test
    public void shouldConvertNegativeBoundsToString() {
        final Bounds bounds = Bounds.of(-10, -2, -20, -10);
        Assert.assertEquals("(-10 to -2, -20 to -10)", bounds.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgumentExceptionToX() {
        Bounds.of(1, 0, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowsIllegalArgumentExceptionToY() {
        Bounds.of(2, 2, 1, 0);
    }

    @Test
    public void shouldEquals() {
        Assert.assertTrue(Bounds.of(0, 0, 0, 0).equals(Bounds.of(0, 0, 0, 0)));
    }

    @Test
    public void shouldMinXNotEquals() {
        Assert.assertFalse(Bounds.of(0, 0, 0, 0).equals(Bounds.of(-1, 0, 0, 0)));
    }

    @Test
    public void shouldMaxXNotEquals() {
        Assert.assertFalse(Bounds.of(0, 0, 0, 0).equals(Bounds.of(0, 1, 0, 0)));
    }

    @Test
    public void shouldMinYNotEquals() {
        Assert.assertFalse(Bounds.of(0, 0, 0, 0).equals(Bounds.of(0, 0, -1, 0)));
    }

    @Test
    public void shouldMaxYNotEquals() {
        Assert.assertFalse(Bounds.of(0, 0, 0, 0).equals(Bounds.of(0, 0, 0, 1)));
    }
}
