package agh.cs.lab7;

import java.util.Random;

public class Simulation {
    private IWorldMap map;
    private Animal[] animalsTable;
    private IEngine simulationEngine;
    public Simulation(int fieldHeight, int fieldWidth, int grassNumber, int numberOfAnimals, int animalEnergy, int grassEnergy){
        this.map = new GrassField(grassNumber,fieldWidth,fieldHeight,grassEnergy);
        this.animalsTable = animalsGenerator(numberOfAnimals, animalEnergy);
        this.simulationEngine = new SimulationEngine(this.map, this.animalsTable);
    }

    private Animal[] animalsGenerator(int numberOfAnimals, int energy){
        Random generator = new Random();
        Animal [] animals = new Animal[numberOfAnimals];
        for (int i = 0; i < numberOfAnimals; i++) {
            animals[i] = new Animal(map, new Vector2d(generator.nextInt(map.getWidth()),generator.nextInt(map.getHeight())), new Genom(), energy);
        }
        return animals;
    }

    public void simulateDay(){
        try {
            simulationEngine.run();
        }catch(IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }

    public IWorldMap getMap() {
        return map;
    }
}
