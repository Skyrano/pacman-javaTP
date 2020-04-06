package logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A class to generate Dijkstra maps in order to manage ghosts paths.
 *
 * @author Alistair Rameau
 */
public class Dijkstra {

    /**
     * The level where the mappings are going to be generated
     */
    private final int[][] level;

    /**
     * The number of zeros in the level, i.e. the length of the pathway
     */
    private final int nb_zeros;

    /**
     * The last mapping generated from a given point
     */
    private int[][] mapping;

    /**
     * The constructor takes the raw level created in the Game class and generate a integer map of
     * the level, and computes the number of zeros (where there is no wall)
     *
     * @param grille the raw level
     * @param wall the char used in grille to symbolize the walls
     */
    public Dijkstra(char[][] grille,char wall) {
        int zeros = 0;
        int[][] matrix = new int[grille.length][grille[0].length];
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
        this.level = this.clone2D(matrix);
        nb_zeros = zeros;
    }

    /**
     * This function creates a copy of the 2D matrix given with the clone() built-in function
     *
     * @param matrix the array to create the new matrix from
     * @return a matrix with the same values as the given matrix but with a different reference
     */
    private int[][] clone2D(int[][] matrix) {
        int[][] new_matrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
                new_matrix[i] = matrix[i].clone();
        }
        return new_matrix;
    }

    /**
     * Gives the list of all the adjacent cases in a cross where there is no wall
     *
     * @param point the points around which we look for the cases
     * @return the array containing all the valid points around the given point
     */
    private ArrayList<Point> adjacentList(Point point) {
        int x =  point.x;
        int y = point.y;
        ArrayList<Point> cross = new ArrayList<>(Arrays.asList(new Point(x-1,y),new Point(x+1,y),new Point(x,y-1),new Point(x,y+1)));;
        ArrayList<Point> liste = new ArrayList<>();
        for (Point p : cross) {
            if (level[p.x][p.y] != -1) {
                liste.add(p);
            }
        }
        return liste;
    }

    /**
     * Gives the list of all the points from the adjacentList() with the desired value
     *
     * @param point the points around which we look for the cases
     * @param value the value of the cases we are looking for
     * @return the array containing all the valid points with the given value around the given point
     */
    private ArrayList<Point> adjacentFilteredList(Point point, int value) {
        ArrayList<Point> cross = this.adjacentList(point);
        ArrayList<Point> liste = new ArrayList<>();
        for (Point p : cross) {
            if (mapping[p.x][p.y] == value) {
                liste.add(p);
            }
        }
        return liste;
    }

    /**
     * Generates the Dijkstra mapping from the given point
     *
     * @param origin the origin point of the mapping
     */
    public void generateMapping(Point origin) {
        int zeros = nb_zeros;
        int counter = 1;
        this.mapping = this.clone2D(this.level);
        this.mapping[origin.x][origin.y] = counter;
        zeros-=1;
        ArrayList<Point> liste = new ArrayList<>();
        liste.add(origin);
        ArrayList<Point> liste_next = new ArrayList<>();
        while (zeros>0) {
            for (Point point : liste) {
                ArrayList<Point> adjacentList = this.adjacentFilteredList(point,0);
                for (Point adjPoint : adjacentList) {
                    this.mapping[adjPoint.x][adjPoint.y] = counter+1;
                    zeros-=1;
                    liste_next.add(adjPoint);
                }
            }
            counter+=1;
            liste = new ArrayList<>(liste_next);
            liste_next.clear();
        }
    }

    /**
     * Gives the closest point to the pacman from the given position
     *
     * @param point the position of the object to compute from
     * @return the closest point to the pacman adjacent to the given point
     */
    public Point closerPoint(Point point) {
        int x = point.x;
        int y = point.y;
        int minimum = mapping[x][y];
        Point nextPoint = point;
        ArrayList<Point> adjacentList = this.adjacentList(point);
        for (Point adjPoint : adjacentList) {
            int i = adjPoint.x;
            int j = adjPoint.y;
            if (mapping[i][j] != -1 && mapping[i][j] <= minimum) {
                minimum = mapping[i][j];
                nextPoint = new Point(i,j);
            }
        }
        return nextPoint;
    }

    /**
     * Gives the furthest point to the pacman from the given position
     *
     * @param point the position of the object to compute from
     * @return the furthest point to the pacman adjacent to the given point
     */
    public Point furtherPoint(Point point) {
        int x = point.x;
        int y = point.y;
        int maximum = mapping[x][y];
        Point nextPoint = point;
        ArrayList<Point> adjacentList = this.adjacentList(point);
        for (Point adjPoint : adjacentList) {
            int i = adjPoint.x;
            int j = adjPoint.y;
            if (mapping[i][j] != -1 && mapping[i][j] >= maximum) {
                maximum = mapping[i][j];
                nextPoint = new Point(i,j);
            }
        }
        return nextPoint;
    }

    /**
     * Gives a pseudo random points around the given point
     * The point can be totally random or closer
     *
     * @param point the position of the object to compute from
     * @param coefficient the probability that the pacman move randomly
     * @return a pseudo random point around the given point
     */
    public Point pseudoRandomCloserPoint(Point point, double coefficient) {
        Random rng = new Random();
        if (rng.nextFloat() > coefficient) {
            return this.closerPoint(point);
        }
        else {
            ArrayList<Point> liste = this.adjacentList(point);
            return liste.get(rng.nextInt(liste.size()));
        }
    }

    /**
     * Gives a pseudo random points around the given point
     * The point can be totally random or further
     *
     * @param point the position of the object to compute from
     * @param coefficient the probability that the pacman move randomly
     * @return a pseudo random point around the given point
     */
    public Point pseudoRandomFurtherPoint(Point point, double coefficient) {
        Random rng = new Random();
        if (rng.nextFloat() > coefficient) {
            return this.furtherPoint(point);
        }
        else {
            ArrayList<Point> liste = this.adjacentList(point);
            return liste.get(rng.nextInt(liste.size()));
        }
    }

}
