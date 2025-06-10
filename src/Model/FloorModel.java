package Model;

import java.util.List;

import static Model.SimulationModel.MAX_PASSENGERS_IN_FLOOR;


public class FloorModel {
    private int waitingPassengers;
    private List<PassengerModel> passengers;


    public FloorModel() {
        waitingPassengers = (int)(Math.random()*MAX_PASSENGERS_IN_FLOOR);
        for(int i = 0; i < waitingPassengers; i++){
            passengers.add(new PassengerModel());
        }
    }
}
