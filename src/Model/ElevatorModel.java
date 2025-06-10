package Model;

public class ElevatorModel {
    private int currentFloor;
    private int passengers;
    private enum Direction {
        UP, DOWN;
    }
    private Direction direction = Direction.UP;

    public ElevatorModel() {
        currentFloor = 0;
        passengers = 0;
    }
}
