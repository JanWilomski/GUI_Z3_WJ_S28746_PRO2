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
        floorCallPanel = new FloorCallPanel();
        buildingPanel = new BuildingPanel();


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

    @Override
    public void update() {
        repaint();
    }
}
