package gov.nasa.curiosity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;

/**
 * Starter class.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
@SpringBootApplication
public class Application implements Serializable {

    /**
     * Initializes application.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
