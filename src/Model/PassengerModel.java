package Model;

public class PassengerModel {
    private enum State {
        WAITING,
        RIDING,
        ARRIVED;
    }
    private State state;

    public PassengerModel() {
        state = State.WAITING;
    }
}
