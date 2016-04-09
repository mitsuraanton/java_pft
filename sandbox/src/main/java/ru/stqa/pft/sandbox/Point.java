package ru.stqa.pft.sandbox;

/**
 * Created by Антон on 09.04.2016.
 */
public class Point {
    int x, y;

    public Point(){

    }
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2){
        int lengthX = p2.x - this.x;
        if (lengthX<0) lengthX = lengthX * (-1);
        int lengthY = p2.y - this.y;
        if (lengthY<0) lengthY = lengthY * (-1);
        return Math.sqrt(lengthX*lengthX + lengthY*lengthY);
    }
}
