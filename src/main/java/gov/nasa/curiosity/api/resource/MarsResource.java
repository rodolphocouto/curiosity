package gov.nasa.curiosity.api.resource;

import gov.nasa.curiosity.domain.model.Movement;
import gov.nasa.curiosity.domain.service.MarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mars resources.
 *
 * @author Rodolpho Couto
 * @since 1.0
 */
@RestController
@RequestMapping("/mars")
public class MarsResource {

    private final MarsService marsService;

    @Autowired
    public MarsResource(MarsService marsService) {
        this.marsService = marsService;
    }

    @PostMapping(path = "/{command}")
    public ResponseEntity<String> move(@PathVariable("command") String command) {
        final List<Movement> movements = command.chars()
                .mapToObj(c -> (char) c)
                .map(Movement::ofInitial)
                .collect(Collectors.toList());

        return ResponseEntity.ok(String.valueOf(marsService.move(movements)));
    }
}
