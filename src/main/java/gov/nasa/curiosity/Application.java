package gov.nasa.curiosity;

import gov.nasa.curiosity.domain.model.Bounds;
import gov.nasa.curiosity.domain.model.Direction;
import gov.nasa.curiosity.domain.model.Position;
import gov.nasa.curiosity.domain.service.MarsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * Starter class.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
@SpringBootApplication
public class Application implements Serializable {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MarsService marsService(@Value("${mars.bounds.min-x}") int minX,
                                   @Value("${mars.bounds.max-x}") int maxX,
                                   @Value("${mars.bounds.min-y}") int minY,
                                   @Value("${mars.bounds.max-y}") int maxY,
                                   @Value("${mars.initial-position.x}") int x,
                                   @Value("${mars.initial-position.y}") int y,
                                   @Value("${mars.initial-position.direction}") char direction) {

        return new MarsService(Bounds.of(minX, maxX, minY, maxY), Position.of(x, y, Direction.ofInitial(direction)));
    }
}
