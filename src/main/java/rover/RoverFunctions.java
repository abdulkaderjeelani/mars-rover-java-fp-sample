package rover;

import position.Position;
import static position.PositionFunctions.getPositionByHeading;

import direction.Direction;
import static direction.DirectionFunctions.getDirections;

import java.util.List;

public final class RoverFunctions {
    public static Rover newRover(final Direction heading, final Position position) {
        return new Rover(heading, position);
    }

    public static Rover turnLeft(final Rover rover) {
        final List<Direction> directions = getDirections();
        final Integer leftIndex = directions.indexOf(rover.heading) - 1;
        final Integer toIndex = leftIndex < 0 ? leftIndex + directions.size() : leftIndex;
        return newRover(directions.get(toIndex), rover.position);
    }

    public static Rover turnRight(final Rover rover) {
        final List<Direction> directions = getDirections();
        final Integer rightIndex = directions.indexOf(rover.heading) + 1;
        final Integer toIndex = rightIndex >= directions.size() ? rightIndex - directions.size() : rightIndex;
        return newRover(directions.get(toIndex), rover.position);
    }

    public static Rover move(final Rover rover) {
        return newRover(rover.heading, getPositionByHeading(rover.position, rover.heading));
    }
}