package View;

import Controller.ElevatorController;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    ElevatorController controller;
    JButton[] buttons = new JButton[11];


    public ControlPanel(ElevatorController controller) {
        this.controller = controller;


        setLayout(new GridLayout(4,3));


        for(int i = 0; i <= 10; i++){
            JButton button = new JButton(String.valueOf(i));
            add(button);
            buttons[i] = button;
            button.addActionListener(e -> {
                controller.selectTargetFloor(Integer.parseInt(((JButton)e.getSource()).getText()));
                button.setForeground(Color.RED);
            }
            );


        }



    }


    public void resetButtonColor(int floor) {
        buttons[floor].setForeground(Color.BLACK);
    }


}
