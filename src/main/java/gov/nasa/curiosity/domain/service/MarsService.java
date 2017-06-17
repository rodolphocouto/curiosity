package gov.nasa.curiosity.domain.service;

import gov.nasa.curiosity.domain.model.Bounds;
import gov.nasa.curiosity.domain.model.Movement;
import gov.nasa.curiosity.domain.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Mars services.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
@Service
public class MarsService {

    private final Bounds bounds;
    private final Position initialPosition;

    @Autowired
    public MarsService(@Value("mars.bounds") Bounds bounds,
                       @Value("mars.initialPosition") Position initialPosition) {
        this.bounds = bounds;
        this.initialPosition = initialPosition;
    }

    public Position move(List<Movement> movements) {
        Position position = initialPosition;

        for (Movement movement : movements) {
            position = movement.move(position, bounds);
        }

        return position;
    }
}
