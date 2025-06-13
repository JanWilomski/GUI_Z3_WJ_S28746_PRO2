package View;

import Controller.ElevatorController;
import Model.PassengerModel;

import java.awt.*;

public class PassengerView {
    private int x;
    private int y;
    private PassengerModel model;
    private int radius;
    private ElevatorController controller;


    public PassengerView(PassengerModel model, ElevatorController controller, int x, int y) {
        this.model = model;
        this.controller = controller;
        this.x = x;
        this.y = y;
        this.radius = 10;
    }



    public void draw(Graphics g) {
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public boolean contains(int mouseX,int mouseY){
        int dx = mouseX - x;
        int dy = mouseY - y;
        return (dx*dx + dy*dy) <= radius*radius;
    }


    public void onClick() {
        if (model.getState() == PassengerModel.State.RIDING) {
            controller.removePassenger(model);
        }
    }


}
