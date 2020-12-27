package agh.cs.lab7;

import java.util.*;


abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    private int width;
    private int height;
    private int AnimalIDCounter=0;
    private int daysNumber=1;
    protected Map<Vector2d, List<Animal>> animals = new HashMap<>();
    List<Animal> listOfAnimals = Collections.synchronizedList(new LinkedList<>());
    List<Animal> listOfDeathAnimals = Collections.synchronizedList(new LinkedList<>());
    public  AbstractWorldMap(int width,int height){
        this.width = width;
        this.height = height;
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        animals.get(oldPosition).remove(animal);
        listOfAnimals.remove(objectAt(oldPosition));
        if(animals.get(oldPosition).size()==0){
            animals.remove(oldPosition);
        }
        List<Animal> tmp = animals.get(newPosition);
        if(tmp==null){
            animals.put(newPosition,new LinkedList<>());
        }
        animals.get(newPosition).add(animal);
    }
    public boolean place(Animal animal) {
        animal.addObserver(this);
        listOfAnimals.add(animal);
        animals.computeIfAbsent(animal.getPosition(),k -> new LinkedList<>());
        animals.get(animal.getPosition()).add(animal);
        return true;
    }

    @Override
    public void run() {
        Random generator = new Random();
        for (int i = 0; i < listOfAnimals.size(); i++){
            Vector2d v = listOfAnimals.get(i).getPosition();
            listOfAnimals.get(i).move(listOfAnimals.get(i).getGenomDirections()[generator.nextInt(32)]);
        }
    }
    public boolean isOccupied(Vector2d position) {
        if(animals.get(position) == null){
            return false;
        }
        else {
            return true;
        }
    }
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }
    public int getHeight(){return this.height;}
    public int getWidth(){return  this.width;}

    public void eating() {

    }
    public void reproduction(){}
    public int setAnimalID(){
        AnimalIDCounter+=1;
        return AnimalIDCounter;
    }
    public void atTheEndOfDay(){
        List<Animal> newDeathCases = Collections.synchronizedList(new LinkedList<>());
        for(int i=0;i<listOfAnimals.size();i++){
            listOfAnimals.get(i).setEnergyLosses(1);
            listOfAnimals.get(i).aging();
            if(listOfAnimals.get(i).getEnergy()==0){
                newDeathCases.add(listOfAnimals.get(i));
                animals.get(listOfAnimals.get(i).getPosition()).remove(listOfAnimals.get(i));
                if(animals.get(listOfAnimals.get(i).getPosition()).size()==0){
                        animals.remove(listOfAnimals.get(i).getPosition());
                }
                listOfAnimals.remove(i);
            }
            if(animals.size()==0){ //& animals.get(listOfDeathAnimals.size()-1).size()==0){
                System.out.println("Koniec symulacji");
            }
        }
        for(int i=0;i<newDeathCases.size();i++){
            newDeathCases.get(i).timeToDie(this.daysNumber);
            listOfDeathAnimals.add(newDeathCases.get(i));
        }
        this.daysNumber++;
    }
    public int getAnimalsNumber(){
        return listOfAnimals.size();
    }
    public int getGrassesNumber(){
        return 0;
    }
    public String stats(){
        String s="";
        for(int i=0;i<listOfAnimals.size();i++){
            s = "Zwierze o numerze "+listOfAnimals.get(i).getAnimalID()+"\nWiek "+listOfDeathAnimals.get(i).getAge()
                    +"\nPosiada genom"+listOfDeathAnimals.get(i).getGenom()+"\nMa dzieci w liczbie: "+listOfDeathAnimals.get(i).numberOfChildren()+"\n";
        }
        for(int i=0;i<listOfDeathAnimals.size();i++){
            s+="Zwierze o numerze "+listOfDeathAnimals.get(i).getAnimalID()+"\nZmarlo po "+listOfDeathAnimals.get(i).getAge()+"\nW dniu: "+listOfDeathAnimals.get(i).whenDay()
            +"\nPosiadajac genom"+listOfDeathAnimals.get(i).getGenom()+"\nMajac dzieci w liczbie: "+listOfDeathAnimals.get(i).numberOfChildren()+"\n";
        }
        return s;
    }
    public int getMeanLifeLength(){
        int counter=0;
        for(int i=0;i<listOfAnimals.size();i++){
            counter+=listOfAnimals.get(i).getAge();
        }
        for(int i=0;i<listOfDeathAnimals.size();i++){
            counter+=listOfDeathAnimals.get(i).getAge();
        }
        return counter/(listOfAnimals.size()+listOfDeathAnimals.size());
    }
    public int getMeanChildrenNumber(){
        int counter=0;
        for(int i=0;i<listOfAnimals.size();i++){
            counter+=listOfAnimals.get(i).numberOfChildren();
        }
        for(int i=0;i<listOfDeathAnimals.size();i++){
            counter+=listOfDeathAnimals.get(i).numberOfChildren();
        }
        return counter/(listOfAnimals.size()+listOfDeathAnimals.size());
    }
    public int getMeanEnergy(){
        int counter=0;
        for(int i=0;i<listOfAnimals.size();i++){
            counter+=listOfAnimals.get(i).getEnergy();
        }
        if (listOfAnimals.size()>1){
            return counter/(listOfAnimals.size());
        }
        return counter;
    }
    public int whatDayIsItToday(){
        return daysNumber;
    }
    public Map<Vector2d, Grass> getGrassesLocation(){return null;}
    public Map<Vector2d, List<Animal>> getAnimalsLocation(){ return animals; }

}
