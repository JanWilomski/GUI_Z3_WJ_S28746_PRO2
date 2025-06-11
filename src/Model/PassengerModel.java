package Model;

public class PassengerModel {
    public enum State {
        WAITING,
        RIDING,
        ARRIVED;
    }
    private State state;

    public PassengerModel() {
        state = State.WAITING;
    }


    //Getters
    public State getState() {
        return state;
    }


    public void setState(State state) {
        this.state = state;
    }
}
