package logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Pathfinding {

    private static int[][] matrix;

    public static void Dijkstra(char[][] grille, Point origin, char wall) {
        int counter = 1;
        int zeros = 0;
        matrix = new int[grille.length][grille[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(grille[i][j] == wall) {
                    matrix[i][j] = -1;
                }
                else {
                    matrix[i][j] = 0;
                    zeros+=1;
                }
            }
        }
        matrix[origin.x][origin.y] = 1;
        zeros-=1;
        ArrayList<Point> liste = new ArrayList<>();
        liste.add(origin);
        ArrayList<Point> liste_next = new ArrayList<>();
        while (zeros>0) {
            for (Point point : liste) {
                ArrayList<Point> adjacentList = AdjacentFilteredList(point,0);
                for (Point adjPoint : adjacentList) {
                    matrix[adjPoint.x][adjPoint.y] = counter+1;
                    zeros-=1;
                    liste_next.add(adjPoint);
                }
            }
            counter+=1;
            liste = new ArrayList<>(liste_next);
            liste_next.clear();
        }
    }


    public static Point closerPoint(Point point) {
        int x = point.x;
        int y = point.y;
        int minimum = matrix[x][y];
        Point nextPoint = point;
        ArrayList<Point> adjacentList = AdjacentList(point);
        for (Point adjPoint : adjacentList) {
            int i = adjPoint.x;
            int j = adjPoint.y;
            if (matrix[i][j] != -1 && matrix[i][j] <= minimum) {
                minimum = matrix[i][j];
                nextPoint = new Point(i,j);
            }
        }
        System.out.println("On retourne le point suivant");
        return nextPoint;
    }

    private static ArrayList<Point> AdjacentList(Point point) {
        int x =  point.x;
        int y = point.y;
        return new ArrayList<>(Arrays.asList(new Point(x-1,y),new Point(x+1,y),new Point(x,y-1),new Point(x,y+1)));
    }

    private static ArrayList<Point> AdjacentFilteredList(Point point, int value) {
        ArrayList<Point> cross = AdjacentList(point);
        ArrayList<Point> liste = new ArrayList<>();
        for (Point p : cross) {
            if (matrix[p.x][p.y] == value) {
                liste.add(p);
            }
        }
        return liste;
    }

}
