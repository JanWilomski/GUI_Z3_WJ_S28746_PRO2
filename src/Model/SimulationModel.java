package Model;

import interfaces.Observable;
import interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class SimulationModel implements Observable {
    private static final int FLOORS_COUNT = 11;
    private static final int MAX_PASSENGERS_IN_ELEVATOR = 5;
    static final int MAX_PASSENGERS_IN_FLOOR = 5;


    private final FloorModel[] floors = new FloorModel[FLOORS_COUNT];
    private final ElevatorModel elevator = new ElevatorModel();
    private boolean simulationRunning;
    private List<Observer> observers = new ArrayList<>();


    public SimulationModel() {
        for (int i = 0; i < floors.length; i++) {
            floors[i] = new FloorModel();
        }

    }






    public FloorModel[] getFloors() { return floors; }
    public ElevatorModel getElevator() { return elevator; }
    public boolean isSimulationRunning() { return simulationRunning; }
    public FloorModel getFloor(int floorNumber) { return floors[floorNumber]; }
    public int getMaxPassengersInElevator() { return MAX_PASSENGERS_IN_ELEVATOR; }



    public void setSimulationRunning(boolean simulationRunning) {
        this.simulationRunning = simulationRunning;
        //notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
