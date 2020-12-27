package agh.cs.lab7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionParser {
    MapDirection[] parse(String[] a){

        int flag=0;
        MapDirection[] moves = new MapDirection[a.length];
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case "0", "north":
                    moves[i-flag]= MapDirection.NORTH;
                    break;
                case "1", "north east":
                    moves[i-flag]= MapDirection.NORTH_EAST;
                    break;
                case "2", "east" :
                    moves[i-flag]= MapDirection.EAST;
                    break;
                case "3", "south east" :
                    moves[i-flag]= MapDirection.SOUTH_EAST;
                    break;
                case "4", "south":
                    moves[i-flag]= MapDirection.SOUTH;
                    break;
                case "5", "south west":
                    moves[i-flag]= MapDirection.SOUTH_WEST;
                    break;
                case "6", "west" :
                    moves[i-flag]= MapDirection.WEST;
                    break;
                case "7", "north west" :
                    moves[i-flag]= MapDirection.NORTH_WEST;
                    break;
                default :
                    flag++;
                    throw new IllegalArgumentException(a[i] + " is not legal move specification");
            }

        }
        return Arrays.copyOf(moves,a.length-flag);
    }

    List<MapDirection> parseList(String args){
        String[] a=args.split(" ");
        List<MapDirection> moves  = new ArrayList<MapDirection>();
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case "0", "north" -> moves.add(MapDirection.NORTH);
                case "1", "north east" -> moves.add(MapDirection.NORTH_EAST);
                case "2", "east" -> moves.add(MapDirection.EAST);
                case "3", "south east" -> moves.add(MapDirection.SOUTH_EAST);
                case "4", "south" -> moves.add(MapDirection.SOUTH);
                case "5", "south west" -> moves.add(MapDirection.SOUTH_WEST);
                case "6", "west" -> moves.add(MapDirection.WEST);
                case "7", "north west" -> moves.add(MapDirection.NORTH_WEST);
                default ->{}
            }
        }
        return moves;
    }
}
