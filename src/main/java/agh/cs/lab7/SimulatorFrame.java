package agh.cs.lab7;

import javax.swing.*;

class SimulatorFrame extends JFrame {
    public SimulatorFrame(){
        super("Game of life");

        JPanel buttonPanel = new ButtonPanel();
        add(buttonPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600,900);
        setVisible(true);
    }
}