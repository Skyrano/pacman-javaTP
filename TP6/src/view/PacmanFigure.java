package view;

import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.Arc2D;

public class PacmanFigure extends Figure{

    private static boolean mouthState = true;
    private static int direction = 0;

    PacmanFigure(int x, int y, int caseLength) {
        super(Color.yellow, giveShape(x,y,caseLength));
        changeMouthState();
    }

    public static void changeDirection(int dx, int dy) {
        if (dx == 1 && dy == 0) {
            direction = 0;
        }
        else if (dx == 0 && dy == -1) {
            direction = 1;
        }
        else if (dx == -1 && dy == 0) {
            direction = 2;
        }
        else if (dx == 0 && dy == 1) {
            direction = 3;
        }
    }

    private static Shape giveShape (int x, int y, int caseLength) {
        int extent;
        int type;
        int start = 0;
        if (mouthState) {
            extent = 300;
            type = Arc2D.PIE;
        }
        else {
            extent = 360;
            type = Arc2D.OPEN;
        }

        switch (direction) {
            case 0:
                start = 30;
                return new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,start, extent,type);
            case 1:
                start = 120;
                return new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,start, extent,type);
            case 2:
                start = 210;
                return new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,start, extent,type);
            case 3:
                start = 300;
                return new Arc2D.Double(x+caseLength*0.1,y+caseLength*0.05,caseLength*0.85,caseLength*0.85,start, extent,type);
        }
        return null;
    }

    private static void changeMouthState() {
        mouthState = !mouthState;
    }

}
