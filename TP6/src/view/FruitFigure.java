package view;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

public class FruitFigure extends Figure{

    private String name;

    FruitFigure(int x, int y, String name) {
        super(giveColor(name), giveShape(x,y,name));
        this.name = name;
    }


    private static Color giveColor (String name) {
        switch (name) {
            case "Gomme":
                return Color.white;
            case "Cerise":
                return Color.red;
            case "Fraise":
                return Color.red;
            case "Orange":
                return Color.orange;
            case "Pomme":
                return Color.yellow;
            case "Melon":
                return Color.green;
            case "Galboss":
                return Color.darkGray;
            case "Cloche":
                return Color.yellow;
            case "Clé":
                return Color.gray;
            case "Super":
                return Color.white;
        }
        return null;
    }

    private static Shape giveShape (int x, int y, String name) {
        switch (name) {
            case "Gomme":
                return new Ellipse2D.Double(x, y, 10, 10);
            case "Cerise":
                return new Ellipse2D.Double(x, y, 10, 10);
            case "Fraise":
                return new Polygon(new int[] {x+0,x+5,x+10}, new int[] {y+0,y+10,y+0},3);
            case "Orange":
                return new Ellipse2D.Double(x, y, 10, 10);
            case "Pomme":
                return new Ellipse2D.Double(x, y, 10, 10);
            case "Melon":
                return new Ellipse2D.Double(x, y, 10, 10);
            case "Galboss":
                return new Polygon(new int[] {x+0,x+10,x+20},new int[] {y+0,y+10,y+0},3);
            case "Cloche":
                return new Polygon(new int[] {x+0,x+5,x+10},new int[] {y+0,y+20,y+0},3);
            case "Clé":
                return new Polygon(new int[] {x+0,x+3,x+3,x+10,x+10,x+0},new int[] {y+0,y+0,y+10,y+10,y+13,y+13},6);
            case "Super":
                return new Ellipse2D.Double(x, y, 20, 20);
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
