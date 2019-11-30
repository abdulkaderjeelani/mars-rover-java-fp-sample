import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class MarsRover {
    public static void main(final String[] args) {
        final Rover rover1 = newRover(Direction.NORTH, newPosition(1, 2));

        final Rover movedRover1 = run(rover1, "LMLMLMLMM");
        System.out.println(movedRover1);

        final Rover rover2 = newRover(Direction.EAST, newPosition(3, 3));
        final Rover movedRover2 = run(rover2, "MMRMMRMRRM");
        System.out.println(movedRover2);

    }

    public static Stream<Character> toCharacterStream(String s) {
        return s.chars().mapToObj(c -> (char) c);
    }

    public static Rover run(final Rover rover, final String instructions) {
        final Map<Character, Function<Rover, Rover>> instructionMap = new HashMap<Character, Function<Rover, Rover>>() {
            private static final long serialVersionUID = 1L;
            {
                put('L', MarsRover::turnLeft);
                put('R', MarsRover::turnRight);
                put('M', MarsRover::move);
            }
        };
        return toCharacterStream(instructions).reduce(rover, (rvr, current) -> instructionMap.get(current).apply(rvr),
                (rvr, current) -> current);
    }

    public static Rover newRover(final Direction heading, final Position position) {
        return new Rover(heading, position);
    }

    public static Position newPosition(final Integer x, final Integer y) {
        return new Position(x, y);
    }

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

    public static Rover turnLeft(final Rover rover) {
        List<Direction> directions = getDirections();
        final Integer leftIndex = directions.indexOf(rover.heading) - 1;
        final Integer toIndex = leftIndex < 0 ? leftIndex + directions.size() : leftIndex;
        return newRover(directions.get(toIndex), rover.position);
    }

    public static Rover turnRight(final Rover rover) {
        List<Direction> directions = getDirections();
        final Integer rightIndex = directions.indexOf(rover.heading) + 1;
        final Integer toIndex = rightIndex >= directions.size() ? rightIndex - directions.size() : rightIndex;
        return newRover(directions.get(toIndex), rover.position);
    }

    public static Rover move(final Rover rover) {
        return newRover(rover.heading, getPositionByHeading(rover.position, rover.heading));
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
