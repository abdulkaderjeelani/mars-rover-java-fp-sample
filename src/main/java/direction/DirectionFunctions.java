package direction;

import java.util.ArrayList;
import java.util.List;

public final class DirectionFunctions {
    public static final List<Direction> getDirections() {
        return new ArrayList<Direction>() {
            private static final long serialVersionUID = 1L;
            {
                add(Direction.NORTH);
                add(Direction.EAST);
                add(Direction.SOUTH);
                add(Direction.WEST);
            }
        };
    }
}
