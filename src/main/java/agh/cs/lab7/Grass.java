package agh.cs.lab7;

public class Grass {
    private Vector2d position;
    private IWorldMap map;
    private int width=10;
    private int height=5;
    private int energy;
    public Grass(Vector2d position, IWorldMap map, int energy){
        this.position=border(position);
        this.map=map;
        this.energy = energy;
    }
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
    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
