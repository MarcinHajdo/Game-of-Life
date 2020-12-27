package agh.cs.lab7;

import javax.swing.*;
import java.awt.*;

class MapPanel extends JPanel {
    private IWorldMap map;

    MapPanel(IWorldMap map){
        this.map = map;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                graphics.setColor(new Color(120,120,0));
                for (Vector2d position : map.getGrassesLocation().keySet()) {
                    if (i== position.getX() && j == position.getY()){
                        graphics.setColor(new Color(100,150,0));
                        if(j > (map.getHeight()/4) && j < (3*map.getHeight()/4) && i > (map.getWidth()/4) && i < (3*map.getWidth()/4) ) {
                            graphics.setColor(new Color(11,100,0));
                        }
                    }

                }
                for (Vector2d position: map.getAnimalsLocation().keySet()) {
                    if (i== position.getX() && j == position.getY()){
                        if(map.getAnimalsLocation().get(position) == null){
                            break;
                        }
                        if(map.getAnimalsLocation().get(position).get(0).getEnergy() < 10)
                            graphics.setColor(new Color(0,0,0));
                        else if(map.getAnimalsLocation().get(position).get(0).getEnergy() < 25)
                            graphics.setColor(new Color(110,0,0));
                        else if(map.getAnimalsLocation().get(position).get(0).getEnergy() < 30)
                            graphics.setColor(new Color(220,0,0));
                        else if(map.getAnimalsLocation().get(position).get(0).getEnergy() < 40)
                            graphics.setColor(new Color(220,150,0));
                        else
                            graphics.setColor(new Color(220,220,0));
                    }
                }

                graphics.fillRect(20*i, 20*j,20,20);
            }
        }
    }
}
