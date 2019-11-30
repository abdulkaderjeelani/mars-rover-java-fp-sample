package position;

import java.util.HashMap;
import java.util.Map;

import direction.Direction;

public final class PositionFunctions {
    public static Position newPosition(final Integer x, final Integer y) {
        return new Position(x, y);
    }

    public static Position getPositionByHeading(final Position currentPosition, final Direction heading) {
        final Map<Direction, Position> positionByDirection = new HashMap<Direction, Position>() {
            private static final long serialVersionUID = 1L;
            {
                put(Direction.NORTH, newPosition(0, 1));
                put(Direction.SOUTH, newPosition(0, -1));
                put(Direction.EAST, newPosition(1, 0));
                put(Direction.WEST, newPosition(-1, 0));
            }
        };
        final Position delta = positionByDirection.get(heading);
        return newPosition(currentPosition.x + delta.x, currentPosition.y + delta.y);
    }
}