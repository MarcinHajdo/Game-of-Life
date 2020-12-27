package agh.cs.lab7;

import java.util.*;

import static java.lang.StrictMath.sqrt;

public class GrassField extends AbstractWorldMap {
    private int n=0;
    private int energy;
    Map<Vector2d, Grass> grasses = new HashMap<>();
    Random generator = new Random();
    public GrassField(int n,int width,int height, int energy){
        super(width,height);
        this.n=n;
        this.energy=energy;
        for(int i=0;i<this.n;i++){
            Grass g = new Grass(new Vector2d(generator.nextInt((int) sqrt(this.n*10)),generator.nextInt((int)sqrt(this.n*10))),this, this.energy);
            if (this.isOccupied(g.getPosition())){
                --i;
            }
            else {
                //Grasses.add(g);
                grasses.put(g.getPosition(),g);
            }
        }
    }
    @Override
    public void eating(){
        Collection<Grass> value = grasses.values();
        ArrayList<Grass> listOfgrasses = new ArrayList<>(value);
        int eaten = 0;
        for(int i=0;i<listOfgrasses.size();i++) {
            if(animals.get(listOfgrasses.get(i).getPosition())!=null){
                if(animals.get(listOfgrasses.get(i).getPosition()).size()>0){
                    int eating_energy=energy/animals.get(listOfgrasses.get(i).getPosition()).size();
                    for(int j=0;j<animals.get(listOfgrasses.get(i).getPosition()).size();j++){
                        animals.get(listOfgrasses.get(i).getPosition()).get(j).eats(eating_energy);
                    }
                }
                grasses.remove(listOfgrasses.get(i).getPosition());
                eaten++;
            }
        }
        for(int i=0;i<eaten;i++){
            Grass g = new Grass(new Vector2d(generator.nextInt((int) sqrt(this.n*10)),generator.nextInt((int)sqrt(this.n*10))),this, this.energy);
            if (this.isOccupied(g.getPosition())){
                --i;
            }
            else {
                //Grasses.add(g);
                grasses.put(g.getPosition(),g);
            }
        }
    }
    public void reproduction() {
        List<Vector2d> SexPositions = new ArrayList<>();
        for (int i = 0; i < listOfAnimals.size(); i++) {
            if (SexPositions.contains(listOfAnimals.get(i).getPosition()) == false) {
                if (animals.get(listOfAnimals.get(i).getPosition()).size() > 1) {
                    SexPositions.add(listOfAnimals.get(i).getPosition());
                    animals.get(listOfAnimals.get(i).getPosition()).sort(Comparator.comparing(Animal::getEnergy).reversed());
                    System.out.println(animals.get(listOfAnimals.get(i).getPosition()).get(0).getEnergy());
                    System.out.println(animals.get(listOfAnimals.get(i).getPosition()).get(1).getEnergy());
                    this.place(new Animal(animals.get(listOfAnimals.get(i).getPosition()).get(0), animals.get(listOfAnimals.get(i).getPosition()).get(1)));
                    animals.get(listOfAnimals.get(i).getPosition()).get(0).setEnergyLosses(animals.get(listOfAnimals.get(i).getPosition()).get(0).getEnergy() / 2);
                    animals.get(listOfAnimals.get(i).getPosition()).get(1).setEnergyLosses(animals.get(listOfAnimals.get(i).getPosition()).get(1).getEnergy() / 2);
                    //do kids
                }
            }
        }
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)){
            return true;
        }
        else if(grasses.get(position)==null){
            return false;
        }
        else {
            return true;
        }
    }
    public Map<Vector2d, Grass> getGrassesLocation(){return grasses;}
    public int getGrassesNumber(){
        return grasses.size();
    }
    @Override
    public Object objectAt(Vector2d position) {
        if(super.objectAt(position)==null){
            return grasses.get(position);
        }
        else {
            return super.objectAt(position);
        }
    }
}
