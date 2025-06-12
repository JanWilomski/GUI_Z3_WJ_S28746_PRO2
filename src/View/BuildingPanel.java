package View;

import Controller.ElevatorController;
import Model.PassengerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingPanel extends JPanel {

    ElevatorController controller;
    private List<PassengerView> passengers;

    private int shaftX, shaftY, shaftWidth, shaftHeight;
    private int elevatorWidth, elevatorHeight;
    private int floorHeight;

    private float smoothPosition;
    private Timer animationTimer;

    private static final int ANIMATION_DELAY = 16;



    public BuildingPanel(ElevatorController controller) {
        this.controller = controller;

        passengers = new ArrayList<>();
        setBackground(Color.LIGHT_GRAY);
        
        smoothPosition = 0.0f;
        
        animationTimer = new Timer(ANIMATION_DELAY, e -> updateAnimation());
        animationTimer.start();


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });


    }

    private void handleMouseClick(int x, int y) {

        for (PassengerView passenger : passengers) {
            if (passenger.contains(x, y)) {
                passenger.onClick();
                break;
            }
        }
    }

    private void updateAnimation() {
        float targetPosition = controller.getModel().getElevator().getCurrentPosition();


        float timeDelta = ANIMATION_DELAY / 50.0f;

        if (Math.abs(smoothPosition - targetPosition) > 0.01f) {
            if (smoothPosition < targetPosition) {
                smoothPosition += timeDelta;
                if (smoothPosition > targetPosition) smoothPosition = targetPosition;
            } else {
                smoothPosition -= timeDelta;
                if (smoothPosition < targetPosition) smoothPosition = targetPosition;
            }
            repaint();
        }
    }

    private void calculateDimensions() {
        floorHeight = getHeight() / 11;


        shaftX = 20;
        shaftY = 0;
        shaftWidth = getWidth() / 3;
        shaftHeight = getHeight();


        elevatorWidth = shaftWidth - 10;
        elevatorHeight = floorHeight - 5;
    }


    private void drawShaft(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.drawRect(shaftX, shaftY, shaftWidth, shaftHeight);
    }

    private void drawFloorLines(Graphics g) {
        g.setColor(Color.BLACK);
        for (int floor = 0; floor <= 10; floor++) {
            int y = getHeight()/11 * floor;
            g.drawLine(shaftX, y, shaftX + shaftWidth, y);
        }
    }

    private void drawElevator(Graphics g) {
        int elevatorX = shaftX + 5;
        int elevatorY = (int)((10 - smoothPosition) * floorHeight + 5);

        g.setColor(Color.YELLOW);
        g.fillRect(elevatorX, elevatorY, elevatorWidth, elevatorHeight);

        g.setColor(Color.BLACK);
        g.drawRect(elevatorX, elevatorY, elevatorWidth, elevatorHeight);
    }


    private void addWaitingPassengers() {
        for (int floor = 0; floor <= 10; floor++) {
            List<PassengerModel> waitingOnFloor = controller.getModel().getFloor(floor).getPassengers();

            int baseX = shaftX + shaftWidth + 20;
            int baseY = (10 - floor) * floorHeight + (floorHeight / 2);


            for (int i = 0; i < waitingOnFloor.size(); i++) {
                PassengerModel passenger = waitingOnFloor.get(i);

                int passengerX = baseX + (i * 30);
                int passengerY = baseY;

                PassengerView view = new PassengerView(passenger, controller, passengerX, passengerY);
                passengers.add(view);
            }
        }
    }

    private void addRidingPassengers() {
        List<PassengerModel> ridingPassengers = controller.getModel().getElevator().getPassengersInElevator();

        // Pozycja windy:
        int elevatorX = shaftX + 5;
        int elevatorY = (int)((10 - smoothPosition) * floorHeight + 5);

        // Rozmieść pasażerów w linii w windzie:
        for (int i = 0; i < ridingPassengers.size(); i++) {
            PassengerModel passenger = ridingPassengers.get(i);

            int passengerX = elevatorX + 10 + (i * 15); // W windzie, mniejszy odstęp
            int passengerY = elevatorY + elevatorHeight / 2; // Środek windy

            PassengerView view = new PassengerView(passenger, controller, passengerX, passengerY);
            passengers.add(view);
        }
    }

    private void drawPassengers(Graphics g) {
        for (PassengerView passenger : passengers) {
            passenger.draw(g);
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        calculateDimensions();

        passengers.clear();
        addWaitingPassengers();
        addRidingPassengers();


        drawShaft(g);
        drawFloorLines(g);
        drawElevator(g);
        drawPassengers(g);




    }

}
