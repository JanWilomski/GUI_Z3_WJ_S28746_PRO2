package Model;

public class SimulationModel {
    private static final int FLOORS_COUNT = 11;
    private static final int MAX_PASSENGERS_IN_ELEVATOR = 5;
    private static final FloorModel[] floors = new FloorModel[FLOORS_COUNT];
    static final int MAX_PASSENGERS_IN_FLOOR = 5;


    public SimulationModel() {
        for (int i = 0; i < floors.length; i++) {
            floors[i] = new FloorModel();
        }

    }
}
