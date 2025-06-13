package Controller;

import Model.ElevatorModel;
import Model.PassengerModel;
import Model.SimulationModel;
import View.ElevatorView;

import java.util.List;
import java.util.Set;

import static Model.PassengerModel.State.*;


public class ElevatorController {
    private SimulationModel model;
    private SimulationController simulationController;
    private ElevatorView view;

    public ElevatorController() {
        model = new SimulationModel();
        simulationController = new SimulationController(model,this);
        view = new ElevatorView(this, model);
        model.addObserver(view);

    }



    // Metody obsługi zdarzeń od użytkownika
    public void startSimulation() {
        model.setSimulationRunning(true);
        simulationController.startSimulation();
        System.out.println("Simulation started");
        view.disableStartButton();
        view.updateCallButtonsAvailability();



    }

    public void callElevator(int floor) {

        model.getFloor(floor).setElevatorCalled(true);
        model.getElevator().getTargetFloors().add(floor);
        model.notifyObservers();
    }


    public void removePassenger(PassengerModel passenger) {
        model.getElevator().getPassengersInElevator().remove(passenger);
        passenger.setState(ARRIVED);

        simulationController.handleEnteringPassengers(model.getElevator().getCurrentFloor());

        model.notifyObservers();
    }


    public void selectTargetFloor(int targetFloor) {

        model.getElevator().getTargetFloors().add(targetFloor);
        model.getFloor(targetFloor).setElevatorCalled(true);
        model.notifyObservers();
    }


    public SimulationModel getModel() {
        return model;
    }


    public void endSimulation() {
        view.enableStartButton();
    }




}