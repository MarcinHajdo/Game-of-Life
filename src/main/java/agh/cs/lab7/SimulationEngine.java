package agh.cs.lab7;

public class SimulationEngine implements IEngine {

    IWorldMap simulatedMap;

    SimulationEngine(IWorldMap map, Animal[] animals){
        this.simulatedMap = map;

        for (int i=0;i<animals.length;i++) {
            this.simulatedMap.place(animals[i]);
        }
    }

    @Override
    public void run() {
        simulatedMap.run();
        simulatedMap.eating();
        simulatedMap.reproduction();
        simulatedMap.atTheEndOfDay();
    }
}