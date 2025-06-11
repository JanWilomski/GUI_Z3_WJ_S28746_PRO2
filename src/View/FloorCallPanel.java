package View;

import Controller.ElevatorController;
import Model.ElevatorModel;


import javax.swing.*;
import java.awt.*;

public class FloorCallPanel extends JPanel {

    ElevatorController controller;
    JButton[] callButtons = new JButton[11];
    JLabel[] upArrows = new JLabel[11];
    JLabel[] downArrows = new JLabel[11];


    public FloorCallPanel(ElevatorController controller) {
        this.controller = controller;

        setLayout(new GridLayout(11,3));

        for(int i = 10; i >= 0; i--){
            JButton button = new JButton(String.valueOf(i));
            add(button);
            callButtons[i] = button;
            button.addActionListener(e -> {
                if(!(controller.getModel().getFloor(Integer.parseInt(((JButton)e.getSource()).getText())).getWaitingPassengers()==0)){
                    controller.callElevator(Integer.parseInt(((JButton)e.getSource()).getText()));
                    button.setEnabled(false);
                }

            }
            );
            JLabel jl = new JLabel("▲");
            add(jl);
            upArrows[i] = jl;
            jl = new JLabel("▼");
            add(jl);
            downArrows[i] = jl;
        }


    }



    public void lightUpDirectionArrows(int floor, ElevatorModel.Direction direction){
        if(direction == ElevatorModel.Direction.UP){
            upArrows[floor].setForeground(Color.RED);
            downArrows[floor].setForeground(Color.BLACK);
        }else if(direction == ElevatorModel.Direction.DOWN){
            upArrows[floor].setForeground(Color.BLACK);
            downArrows[floor].setForeground(Color.RED);
        }
    }
}
