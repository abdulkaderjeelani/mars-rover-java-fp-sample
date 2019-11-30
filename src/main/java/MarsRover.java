import static position.PositionFunctions.newPosition;
import static rover.RoverFunctions.newRover;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import direction.Direction;
import rover.Rover;

public final class MarsRover {
    public static void main(final String[] args) {

        final Rover rover1 = newRover(Direction.NORTH, newPosition(1, 2));
        final Rover movedRover1 = run(rover1, "LMLMLMLMM");
        System.out.println(movedRover1);

        final Rover rover2 = newRover(Direction.EAST, newPosition(3, 3));
        final Rover movedRover2 = run(rover2, "MMRMMRMRRM");
        System.out.println(movedRover2);

    }

    public static Rover run(final Rover rover_, final String instructions) {
        final Map<Character, Function<Rover, Rover>> instructionMap = getInstructions();
        return toCharacterStream(instructions).reduce(rover_, (rvr, current) -> instructionMap.get(current).apply(rvr),
                (rvr, current) -> current);
    }

    public static Stream<Character> toCharacterStream(String s) {
        return s.chars().mapToObj(c -> (char) c);
    }

    public static Map<Character, Function<Rover, Rover>> getInstructions() {
        return new HashMap<Character, Function<Rover, Rover>>() {
            private static final long serialVersionUID = 1L;
            {
                put('L', rover.RoverFunctions::turnLeft);
                put('R', rover.RoverFunctions::turnRight);
                put('M', rover.RoverFunctions::move);
            }
        };
    }

}
