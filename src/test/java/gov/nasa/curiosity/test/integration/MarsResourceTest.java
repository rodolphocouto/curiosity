package gov.nasa.curiosity.test.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Integration testes for mars endpoint.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MarsResourceTest {

    private static final String PATH = "/mars/{command}";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldMoveToLeft() {
        final ResponseEntity<String> response = post("L");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(0,0,W)", response.getBody());
    }

    @Test
    public void shouldMoveToRight() {
        final ResponseEntity<String> response = post("R");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(0,0,E)", response.getBody());
    }

    @Test
    public void shouldMove1PositionForward() {
        final ResponseEntity<String> response = post("M");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(0,1,N)", response.getBody());
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEast() {
        final ResponseEntity<String> response = post("MRM");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(1,1,E)", response.getBody());
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEastAnd1PositionToSouth() {
        final ResponseEntity<String> response = post("MRMRM");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(1,0,S)", response.getBody());
    }

    @Test
    public void shouldMove1PositionForwardAnd1PositionToEastAnd1PositionToSouthAnd1PositionToWest() {
        final ResponseEntity<String> response = post("MRMRMRM");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(0,0,W)", response.getBody());
    }

    @Test
    public void shouldMove5PositionsForward() {
        final ResponseEntity<String> response = post("MMMMM");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(0,5,N)", response.getBody());
    }

    @Test
    public void shouldMove5PositionsForwardAnd5PositionsToEast() {
        final ResponseEntity<String> response = post("MMMMMRMMMMM");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(5,5,E)", response.getBody());
    }

    @Test
    public void shouldMove5PositionsForwardAnd5PositionsToEastAnd5PositionsToSouth() {
        final ResponseEntity<String> response = post("MMMMMRMMMMMRMMMMM");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(5,0,S)", response.getBody());
    }

    @Test
    public void shouldMove5PositionsForwardAnd5PositionsToEastAnd5PositionsToSouthAnd1PositionToWest() {
        final ResponseEntity<String> response = post("MMMMMRMMMMMRMMMMMRMMMMM");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(0,0,W)", response.getBody());
    }

    @Test
    public void shouldMove2PositionsForwardAndMoveToRight() {
        final ResponseEntity<String> response = post("MMRMMRMM");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(2,0,S)", response.getBody());
    }

    @Test
    public void shouldMove2PositionsForwardAndMoveToLeft() {
        final ResponseEntity<String> response = post("MML");

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("(0,2,W)", response.getBody());
    }

    @Test
    public void shouldThrowsUnrecognizedMovementException() {
        final ResponseEntity<String> response = post("AAA");

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("'A' is not a recognized movement. Consider just these: (L,R,M)", response.getBody());
    }

    @Test
    public void shouldThrowsPositionOutOfBoundsExceptionToNorth() {
        final ResponseEntity<String> response = post("MMMMMMMMMMMMMMMMMMMMMMMM");

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("The (0,6,N) position is out of the bounds (0 to 5, 0 to 5)", response.getBody());
    }

    @Test
    public void shouldThrowsPositionOutOfBoundsExceptionToEast() {
        final ResponseEntity<String> response = post("RMMMMMMMMMMMMMMMMMMMMMMMM");

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("The (6,0,E) position is out of the bounds (0 to 5, 0 to 5)", response.getBody());
    }

    @Test
    public void shouldThrowsPositionOutOfBoundsExceptionToSouth() {
        final ResponseEntity<String> response = post("RRMMMMMMMMMMMMMMMMMMMMMMMM");

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("The (0,-1,S) position is out of the bounds (0 to 5, 0 to 5)", response.getBody());
    }

    @Test
    public void shouldThrowsPositionOutOfBoundsExceptionToWest() {
        final ResponseEntity<String> response = post("LMMMMMMMMMMMMMMMMMMMMMMMM");

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("The (-1,0,W) position is out of the bounds (0 to 5, 0 to 5)", response.getBody());
    }

    @Test
    public void shouldNotFound() {
        Arrays.asList("/", "/mars", "/aaa")
                .stream()
                .forEach(path -> {
                    final ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.POST, HttpEntity.EMPTY, String.class);

                    Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                    Assert.assertNull(response.getBody());
                });
    }

    @Test
    public void shouldMethodNotAllowed() {
        Arrays.asList(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
                .stream()
                .forEach(method -> {
                    final ResponseEntity<String> response = restTemplate.exchange(PATH, method, HttpEntity.EMPTY, String.class, "M");

                    Assert.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
                    Assert.assertNull(response.getBody());
                });
    }

    private ResponseEntity<String> post(String command) {
        return restTemplate.exchange(PATH, HttpMethod.POST, HttpEntity.EMPTY, String.class, command);
    }
}
