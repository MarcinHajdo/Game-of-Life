package agh.cs.lab7;

enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTH_EAST,
    NORTH_WEST,
    SOUTH_EAST,
    SOUTH_WEST;

    public String toString(){
        switch(this) {
            case NORTH: return "Polnoc";
            case SOUTH: return "Poludnie";
            case EAST:  return "Wschod";
            case WEST:  return "Zachod";
            case NORTH_EAST: return "Polnocny Wschod";
            case NORTH_WEST: return "Polnocny Zachód";
            case SOUTH_EAST: return "Poludniowy Wschod";
            case SOUTH_WEST: return "Poludniowy Zachód";
            default:    return "Error";
        }
    }
    public MapDirection next(){
        switch(this) {
            case NORTH: return NORTH_EAST;
            case SOUTH: return SOUTH_WEST;
            case EAST:  return SOUTH_EAST;
            case WEST:  return NORTH_WEST;
            case NORTH_EAST: return EAST;
            case NORTH_WEST: return NORTH;
            case SOUTH_EAST: return SOUTH;
            case SOUTH_WEST: return WEST;
            default:    return null;
        }
    }
    public MapDirection previous(){
        switch(this) {
            case NORTH: return NORTH_WEST;
            case SOUTH: return SOUTH_EAST;
            case WEST:  return SOUTH_WEST;
            case EAST:  return NORTH_EAST;
            case NORTH_EAST: return NORTH;
            case NORTH_WEST: return WEST;
            case SOUTH_EAST: return EAST;
            case SOUTH_WEST: return SOUTH;
            default:    return null;
        }

    }
    public Vector2d toUnitVector(){
        switch(this) {
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            case EAST: return new Vector2d(1,0);
            case NORTH_EAST: return new Vector2d(1,1);
            case NORTH_WEST: return new Vector2d(-1,1);
            case SOUTH_EAST: return new Vector2d(1,-1);
            case SOUTH_WEST: return new Vector2d(-1,-1);
        }
        return new Vector2d(0,0);
    }
    public String toUnitedVector(){
        switch(this) {
            case NORTH: return new Vector2d(0,1).toString();
            case SOUTH: return new Vector2d(0,-1).toString();
            case WEST: return new Vector2d(-1,0).toString();
            case EAST: return new Vector2d(1,0).toString();
            case NORTH_EAST: return new Vector2d(1,1).toString();
            case NORTH_WEST: return new Vector2d(-1,1).toString();
            case SOUTH_EAST: return new Vector2d(1,-1).toString();
            case SOUTH_WEST: return new Vector2d(-1,-1).toString();
        }
        return new Vector2d(0,0).toString();
    }
}