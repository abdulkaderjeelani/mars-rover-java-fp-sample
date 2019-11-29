public class Rover {

    public final Direction heading;
    public final Position position;

    public Rover(Direction heading, Position position) {
        this.heading = heading;
        this.position = position;
    }

    @Override
    public String toString() {
        return this.heading.toString() + " - " + this.position.x + "," + this.position.y;
    }
}