package View;

import Controller.ElevatorController;

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
    private static final float ANIMATION_SPEED = 0.05f;


    public BuildingPanel(ElevatorController controller) {
        this.controller = controller;

        passengers = new ArrayList<>();
        setBackground(Color.LIGHT_GRAY);
        
        smoothPosition = 0.0f;
        
        animationTimer = new Timer(ANIMATION_DELAY, e -> updateAnimation());
        animationTimer.start();


    }

    private void updateAnimation() {
        float targetPosition = controller.getModel().getElevator().getCurrentPosition();

        // Płynny ruch w czasie 100ms (czas jednego kroku windy):
        float timeDelta = ANIMATION_DELAY / 100.0f; // 16ms / 100ms = 0.16

        if (Math.abs(smoothPosition - targetPosition) > 0.01f) {
            if (smoothPosition < targetPosition) {
                smoothPosition += timeDelta; // Dokładnie 100ms na piętro
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



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        calculateDimensions();


        drawShaft(g);
        drawFloorLines(g);
        drawElevator(g);



    }

}
