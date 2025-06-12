package View;

import Controller.ElevatorController;
import Controller.SimulationController;
import Model.SimulationModel;
import interfaces.Observer;

import javax.swing.*;
import java.awt.*;

public class ElevatorView extends JFrame implements Observer {

    ElevatorController controller;
    SimulationModel model;

    ControlPanel controlPanel;
    FloorCallPanel floorCallPanel;
    BuildingPanel buildingPanel;

    public ElevatorView(ElevatorController controller, SimulationModel model) {
        super("Elevator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.controller = controller;
        this.model = model;

        controlPanel = new ControlPanel(controller);
        floorCallPanel = new FloorCallPanel(controller);
        buildingPanel = new BuildingPanel(controller);


        setLayout(new BorderLayout());

        JPanel threeSectionsPanel = new JPanel();
        threeSectionsPanel.setLayout(new GridLayout(1,3));
        threeSectionsPanel.add(controlPanel);
        threeSectionsPanel.add(buildingPanel);
        threeSectionsPanel.add(floorCallPanel);
        add(threeSectionsPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            controller.startSimulation();
        });



        pack();
        setLocationRelativeTo(null);
        setVisible(true);



    }


    public void disableStartButton(){
        ((JButton)getContentPane().getComponent(1)).setEnabled(false);
    }

    public void enableAllButtons(){

        for(int i = 0; i < 11; i++){
            controlPanel.buttons[i].setEnabled(true);
        }

    }




    @Override
    public void update() {
        for(int i = 0; i < 11; i++){
            if(!model.getFloor(i).isElevatorCalled()){
                controlPanel.resetButtonColor(i);
                floorCallPanel.enableCallButton(i);
                floorCallPanel.turnOffDirectionArrow(i);


            }else floorCallPanel.turnOnDirectionArrow(i, model.getElevator().getDirection());

        }
        repaint();
    }
}
