package agh.cs.lab7;

import java.util.ArrayList;
import java.util.List;


public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private IWorldMap map = null;
    private int width;
    private int height;
    private String genom;
    private MapDirection[] GenomDirections;
    private int energy;
    private int AnimalID;
    private int age;
    private List<Integer> ChildID = new ArrayList<Integer>();
    private final List<IPositionChangeObserver> observers = new ArrayList<>();
    private int deathDay;

    public Animal(IWorldMap map){
        this.map = map;
        this.width = map.getWidth();
        this.height = map.getHeight();
    }
    public Animal(IWorldMap map, Vector2d initialPosition, Genom genom, int energy){
        this.map = map;
        this.width = map.getWidth();
        this.height = map.getHeight();
        this.position = border(initialPosition);
        this.genom = genom.genomGen();
        this.GenomDirections = new OptionParser().parse(this.genom.split(" "));
        this.energy = energy;
        this.AnimalID =map.setAnimalID();
        this.age = 1;
    }
    public Animal(Animal parent1, Animal parent2){
        this.map = parent1.map;
        this.width = map.getWidth();
        this.height = map.getHeight();
        this.position = parent1.position;
        this.genom = new Genom().childGenom(parent1.getGenom(),parent2.getGenom());
        this.GenomDirections = new OptionParser().parse(this.genom.split(" "));
        this.energy = (parent1.getEnergy()+ parent2.getEnergy())/4;
        this.AnimalID =map.setAnimalID();
        parent1.addChildren(this.AnimalID);
        parent2.addChildren(this.AnimalID);
        this.age = 0;
    }
    public Vector2d getPosition() {
        return this.position;
    }
    public MapDirection[] getGenomDirections(){return this.GenomDirections;}
    public String getGenom(){return this.genom;}
    private Vector2d border(Vector2d p){
        if (this.map != null){
            this.width = this.map.getWidth();
            this.height = this.map.getHeight();
        }
        if (p.getY()>=this.height)
        {
            p= p.subtract(new Vector2d(0,this.height));
        }
        if (p.getX()>=this.width)
        {
            p= p.subtract(new Vector2d(this.width,0));
        }
        if (p.getY()<0)
        {
            p= p.add(new Vector2d(0,this.height));
        }
        if (p.getX()<0)
        {
            p= p.add(new Vector2d(this.width,0));
        }
        return p;
    }
    public void move(MapDirection direction){
        if(this.map!=null){
            Vector2d tmp = this.position;
            this.position=border(this.position.add(direction.toUnitVector()));
            positionChanged(tmp, this.position);
        }
        else {
            this.position=border(this.position.add(direction.toUnitVector()));
        }
    }
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition,newPosition, this);
        }
    }
    public void eats(int energy){
        this.energy += energy;
    }
    public int getEnergy(){return this.energy;}
    public void setEnergyLosses(int lostEnergy){
        this.energy = this.energy-lostEnergy;
    }
    public void aging(){ this.age++;}
    public int getAge(){return age;}
    public int getAnimalID(){return AnimalID;}
    public void addChildren(int id){
        ChildID.add(id);
    }
    public int numberOfChildren(){return ChildID.size();}
    public void timeToDie(int day){
        this.deathDay = day;
    }
    public int whenDay(){return this.deathDay;}
    public String toString() {
        return "A";
    }
}
