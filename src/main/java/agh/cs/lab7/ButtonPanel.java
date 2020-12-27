package agh.cs.lab7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonPanel extends JPanel implements ActionListener {
    private int animalEnergy =50, grassEnergy=10, height =30, width =20, numberOfGrasses =500, numberOfAnimals =5;
    private JTextField animalEnergyField, grassEnergyField, heightField, widthField, numberOfGrassesField, numberOfAnimalsField;
    private Timer timer;
    private JTextField speedField;
    private final JButton startingButton, continuingButton, stoppingButton, printToJsonButton;
    private JPanel mapGraphics;
    private Simulation Simulation;
    private ParserToJSON parser = new ParserToJSON();;
    private JTextArea jTextFieldStats;

    public ButtonPanel() {
        startingButton = createButton("New Game", 120,100,100,20);
        stoppingButton = createNonVisibleButton("Stop Game",120,120,100,20);
        continuingButton = createNonVisibleButton("Continue Game",120,120,120,20);
        printToJsonButton = createButton("Print to JSON",120,140,120,20);
        widthField = createTextField("Width", 20,25,50,20,"30",20,5, 50,20);
        heightField = createTextField("Height", 80,25,50,20,"20",80,5, 50,20);
        speedField = createTextField("Speed", 140,25,50,20, "10",140,5, 50,20);
        numberOfGrassesField = createTextField("Grasses", 20,75,50,20, "500",20,55, 50,20);
        numberOfAnimalsField = createTextField("Animals", 80,75,50,20, "5",80,55, 50,20);
        animalEnergyField = createTextField("Animals Energy", 20,125,100,20, "50",20,105, 50,20);
        grassEnergyField = createTextField("Grass Energy", 20,175,100,20, "10",20,155, 50,20);

        jTextFieldStats = new JTextArea("Stat presentation \n press New Game button\n to start");
        jTextFieldStats.setBounds(20,200, 200,220);
        add(jTextFieldStats);

        timer = new Timer(100,this);
        setLayout(null);
        setPreferredSize(new Dimension(1, 1));
    }
    private JButton createButton(String text, int x, int y, int width, int height){
        JButton Button = new JButton(text);
        Button.addActionListener(this);
        Button.setBounds(x, y, width, height);
        add(Button);
        return Button;
    }
    private JButton createNonVisibleButton(String text, int x, int y, int width, int height){
        JButton Button = createButton(text, x, y, width, height);
        Button.setVisible(false);
        return Button;
    }
    private JTextField createTextField(String text, int x, int y, int width, int height, String text2,
                                 int x2, int y2, int width2, int height2){
        JTextArea jTextArea = new JTextArea(text);
        jTextArea.setBounds(x, y, width, height);
        jTextArea.setBackground(null);
        add(jTextArea);
        JTextField Field = new JTextField(text2);
        Field.setBounds(x2,y2, width2,height2);
        add(Field);
        return Field;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == startingButton){
            stoppingButton.setVisible(true);
            this.height = Integer.parseInt(heightField.getText());
            this.width = Integer.parseInt(widthField.getText());
            this.numberOfGrasses = Integer.parseInt(numberOfGrassesField.getText());
            this.numberOfAnimals = Integer.parseInt(numberOfAnimalsField.getText());
            this.animalEnergy = Integer.parseInt(animalEnergyField.getText());
            this.grassEnergy = Integer.parseInt(grassEnergyField.getText());
            timer = new Timer(1000/Integer.parseInt(speedField.getText()),this);
            simulation();
            timer.start();
        }
        else if(source == stoppingButton){
            continuingButton.setVisible(true);
            stoppingButton.setVisible(false);
            timer.stop();
        }
        else if(source == continuingButton){
            continuingButton.setVisible(false);
            stoppingButton.setVisible(true);
            timer.start();
        }
        else if(source == printToJsonButton)
            parser.printToJson();
        else {
            ArrayList<Integer>  stats = new ArrayList<>();
            stats.add(Simulation.getMap().whatDayIsItToday());
            stats.add(Simulation.getMap().getAnimalsNumber());
            stats.add(Simulation.getMap().getMeanLifeLength());
            stats.add(Simulation.getMap().getMeanChildrenNumber());
            stats.add(Simulation.getMap().getMeanEnergy());
            parser.setStats(stats);
            parser.statsDay();
            jTextFieldStats.setText(parser.toString());
            renderMap();
        }
    }
    private void simulation() {
        Simulation = new Simulation(this.height, this.width, this.numberOfGrasses, this.numberOfAnimals, this.animalEnergy, this.grassEnergy);
        Simulation.simulateDay();
        drawMap(Simulation.getMap());
    }
    private void renderMap(){
        if(Simulation.getMap().getAnimalsLocation().size() == 0){
            stoppingButton.setVisible(false);
            timer.stop();
        }else{
            Simulation.simulateDay();
            mapGraphics = new MapPanel(Simulation.getMap());
            drawMap(Simulation.getMap());
        }
    }
    private void drawMap(IWorldMap map) {
        if (mapGraphics != null){
            remove(mapGraphics);
        }
        mapGraphics = new MapPanel(map);
        mapGraphics.setBounds(400, 50, 5000, 5000);

        mapGraphics.setVisible(true);
        add(mapGraphics);
        mapGraphics.repaint();
        mapGraphics.revalidate();
    }
}
