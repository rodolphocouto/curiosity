package gov.nasa.curiosity.domain.service;

import gov.nasa.curiosity.domain.model.Bounds;
import gov.nasa.curiosity.domain.model.Movement;
import gov.nasa.curiosity.domain.model.Position;

import java.util.List;

/**
 * Mars services.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
public class MarsService {

    private final Bounds bounds;
    private final Position initialPosition;

    public MarsService(Bounds bounds, Position initialPosition) {
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
