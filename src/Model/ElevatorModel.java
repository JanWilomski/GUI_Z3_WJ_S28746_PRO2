package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElevatorModel {
    private int currentFloor;

    public enum Direction {
        UP, DOWN;
    }
    private Direction direction = Direction.UP;
    private List<PassengerModel> passengersInElevator;
    private Set<Integer> targetFloors;
    public int currentTargetFloor;

    public ElevatorModel() {
        currentFloor = 0;
        passengersInElevator = new ArrayList<>();
        targetFloors=new HashSet<>();
        currentTargetFloor=0;

    }




    //Getters
    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }
    public List<PassengerModel> getPassengersInElevator() {
        return passengersInElevator;
    }

    //Setters
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Set<Integer> getTargetFloors() {
        return targetFloors;
    }

}
