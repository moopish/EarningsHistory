package ehist.mem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * === Employer Class ===
 * </p><p>
 * Date : March 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public class Employer {

    private String name;
    private final List<Position> positions;

    public Employer(String name) {
        this.name = name;
        this.positions = new ArrayList<>();
    }

    public void addPosition(Position position) {
        positions.add(position);
    }

    public String getName() {
        return name;
    }

    public List<Position> getPositions() {
        return (Collections.unmodifiableList(positions));
    }
}
