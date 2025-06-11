package Model;

import java.util.ArrayList;
import java.util.List;

import static Model.SimulationModel.MAX_PASSENGERS_IN_FLOOR;


public class FloorModel {
    private List<PassengerModel> passengers;
    private boolean elevatorCalled;



    public FloorModel() {
        int waitingPassengers = (int)(Math.random()*MAX_PASSENGERS_IN_FLOOR);
        passengers=new ArrayList<>();
        for(int i = 0; i < waitingPassengers; i++){
            passengers.add(new PassengerModel());
        }
        elevatorCalled=false;
    }





    //Getters
    public int getWaitingPassengers() {
        return passengers.size();
    }
    public List<PassengerModel> getPassengers() {
        return passengers;
    }
    public boolean isElevatorCalled() {
        return elevatorCalled;
    }



    public void setElevatorCalled(boolean elevatorCalled) {
        this.elevatorCalled = elevatorCalled;
    }
}
