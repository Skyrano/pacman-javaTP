package view;

import java.awt.*;
import java.awt.geom.Ellipse2D;


/**
 * A class symbolizing a Fruit or an general Element derived from Figure
 *
 * @author Alistair Rameau
 */
public class FruitFigure extends Figure{

    /**
     * The name of the element or fruit
     */
    private String name;

    /**
     * The constructor
     * @param x the x position of the element in the canvas
     * @param y the y position of the element in the canvas
     * @param caseLength the length of one case in the canvas
     * @param name the name of the element
     */
    FruitFigure(int x, int y, int caseLength, String name) {
        super(giveColor(name), giveShape(x,y,caseLength,name));
        this.name = name;
    }

    /**
     * Give a certain color depending on the parameters
     * @param name the name of the element or fruit
     * @return the color corresponding to the given name
     */
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
                return Color.green;
            case "Melon":
                return Color.yellow;
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

    /**
     * Give a certain shape depending on the parameters
     * @param x the x position of the element
     * @param y the y position of the element
     * @param caseLength the length of one case in the canvas
     * @param name the name of the element
     * @return the shape corresponding to the given parameters
     */
    private static Shape giveShape (int x, int y, int caseLength, String name) {
        switch (name) {
            case "Gomme":
                return new Ellipse2D.Double(x+caseLength*0.35, y+caseLength*0.35, caseLength*0.3, caseLength*0.3);
            case "Cerise":
                return new Ellipse2D.Double(x+caseLength*0.35, y+caseLength*0.35, caseLength*0.3, caseLength*0.3);
            case "Fraise":
                return new Polygon(new int[] {(int) (x + (caseLength*0.35)), (int) (x+5+caseLength*0.35), (int) (x+10+caseLength*0.35)}, new int[] {(int) (y+caseLength*0.35), (int) (y+10+caseLength*0.35), (int) (y+caseLength*0.35)},3);
            case "Orange":
                return new Ellipse2D.Double(x+caseLength*0.35, y+caseLength*0.35, caseLength*0.3, caseLength*0.3);
            case "Pomme":
                return new Ellipse2D.Double(x+caseLength*0.35, y+caseLength*0.35, caseLength*0.3, caseLength*0.3);
            case "Melon":
                return new Ellipse2D.Double(x+caseLength*0.35, y+caseLength*0.35, caseLength*0.3, caseLength*0.3);
            case "Galboss":
                return new Polygon(new int[] {(int) (x+caseLength*0.25), (int) (x+10+caseLength*0.25), (int) (x+20+caseLength*0.25)},new int[] {(int) (y+caseLength*0.35), (int) (y+10+caseLength*0.35), (int) (y+caseLength*0.35)},3);
            case "Cloche":
                return new Polygon(new int[] {(int) (x+caseLength*0.35), (int) (x+5+caseLength*0.35), (int) (x+10+caseLength*0.35)},new int[] {(int) (y+20+caseLength*0.20), (int) (y+caseLength*0.20), (int) (y+20+caseLength*0.20)},3);
            case "Clé":
                return new Polygon(new int[] {(int) (x+caseLength*0.40), (int) (x+3+caseLength*0.40), (int) (x+3+caseLength*0.40), (int) (x+10+caseLength*0.40), (int) (x+10+caseLength*0.40), (int) (x+caseLength*0.40)}, new int[] {(int) (y+caseLength*0.25), (int) (y+caseLength*0.25), (int) (y+15+caseLength*0.25), (int) (y+15+caseLength*0.25), (int) (y+18+caseLength*0.25), (int) (y+18+caseLength*0.25)},6);
            case "Super":
                return new Ellipse2D.Double(x+caseLength*0.25, y+caseLength*0.25, caseLength*0.5, caseLength*0.5);
        }
        return null;
    }

    /**
     * The getter of the element's name
     * @return the String name
     */
    public String getName() {
        return name;
    }
}
