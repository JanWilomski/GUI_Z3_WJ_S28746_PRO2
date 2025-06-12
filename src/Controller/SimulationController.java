package Controller;

import Model.ElevatorModel;
import Model.PassengerModel;
import Model.SimulationModel;
import javax.swing.Timer;
import java.util.List;
import java.util.Set;

public class SimulationController {
    private SimulationModel model;
    private Timer simulationTimer;
    private static final int TIMER_DELAY = 1000; // 100ms
    private ElevatorController elevatorController;

    public SimulationController(SimulationModel model, ElevatorController elevatorController) {
        this.model = model;
        initializeTimer();
        this.elevatorController=elevatorController;
    }

    private void initializeTimer() {
        simulationTimer = new Timer(TIMER_DELAY, e -> updateSimulation());
    }

    private void updateSimulation() {
        moveElevator();
        updatePassengers();
        checkSimulationEnd();
        System.out.println(elevatorController.getModel().getElevator().getCurrentFloor());

    }




    public void startSimulation() {
        simulationTimer.start();
    }

    public void stopSimulation() {
        simulationTimer.stop();
    }






    private void checkSimulationEnd(){

    }


    public void moveElevator(){
        Set<Integer> targets = model.getElevator().getTargetFloors();

        if(targets.isEmpty()) return;

        int currentFloor = model.getElevator().getCurrentFloor();
        int nextTargetFloor = findNextTargetFloor(currentFloor, targets);
        if(nextTargetFloor == -1) return;

        if(nextTargetFloor > currentFloor){
            model.getElevator().setDirection(ElevatorModel.Direction.UP);
            model.getElevator().setCurrentFloor(currentFloor + 1);
        }else if(nextTargetFloor < currentFloor){
            model.getElevator().setDirection(ElevatorModel.Direction.DOWN);
            model.getElevator().setCurrentFloor(currentFloor - 1);
        }

        currentFloor=model.getElevator().getCurrentFloor();
        if(currentFloor == nextTargetFloor){
            targets.remove(nextTargetFloor);
            model.getFloor(nextTargetFloor).setElevatorCalled(false);
        }
        model.notifyObservers();
    }

    private int findNextTargetFloor(int currentFloor, Set<Integer> targets) {
        if(targets.isEmpty()) return -1;

        int targetFloor = findTargetInDirection(currentFloor, targets, model.getElevator().getDirection());

        if(targetFloor == -1){
            model.getElevator().setDirection(model.getElevator().getDirection().equals(ElevatorModel.Direction.UP) ? ElevatorModel.Direction.DOWN : ElevatorModel.Direction.UP);
            targetFloor = findTargetInDirection(currentFloor, targets, model.getElevator().getDirection());
        }

        return targetFloor;
    }
    private int findTargetInDirection(int currentFloor, Set<Integer> targets, ElevatorModel.Direction direction){
        int closestTarget = -1;
        int closestDistance = Integer.MAX_VALUE;

        for (int target : targets) {
            if ((direction == ElevatorModel.Direction.UP && target > currentFloor) ||
                    (direction == ElevatorModel.Direction.DOWN && target < currentFloor)) {

                int distance = Math.abs(target - currentFloor);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestTarget = target;
                }
            }
        }

        return closestTarget;


    }


    private void updatePassengers(){
        int currentFloor = model.getElevator().getCurrentFloor();
        if(model.getFloor(currentFloor).isElevatorCalled()){
            handleEnteringPassengers(currentFloor);
        }

    }

    private void handleEnteringPassengers(int currentFloor) {
        List<PassengerModel> waitingPassengers = model.getFloor(currentFloor).getPassengers();
        List<PassengerModel> elevatorPassengers = model.getElevator().getPassengersInElevator();

        if (waitingPassengers.isEmpty()) return;

        int availableSpace = model.getMaxPassengersInElevator() - elevatorPassengers.size();
        int passengersToBoard = Math.min(availableSpace, waitingPassengers.size());

        for (int i = 0; i < passengersToBoard; i++) {
            PassengerModel passenger = waitingPassengers.remove(waitingPassengers.size() - 1);
            passenger.setState(PassengerModel.State.RIDING);
            elevatorPassengers.add(passenger);
        }
        model.notifyObservers();
    }
}