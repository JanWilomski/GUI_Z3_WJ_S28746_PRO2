package View;

import interfaces.Observer;

import javax.swing.*;

public class ElevatorView extends JFrame implements Observer {

    public ElevatorView() {

    }

    @Override
    public void update() {
        repaint();
    }
}
