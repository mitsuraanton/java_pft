package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        Point p1 = new Point();
        p1.x = 1;
        p1.y = 1;
        Point p2 = new Point();
        p2.x = 4;
        p2.y = 5;
        System.out.println(distance(p1,p2));

        Point p3 = new Point(100,100);
        Point p4 = new Point(97,96);
        System.out.println(p3.distance(p4));
        System.out.println(p4.distance(p3));

    }

    public static double distance(Point p1, Point p2){
        int lengthX = p2.x - p1.x;
        if (lengthX<0) lengthX = lengthX * (-1);
        int lengthY = p2.y - p1.y;
        if (lengthY<0) lengthY = lengthY * (-1);
        return Math.sqrt(lengthX*lengthX + lengthY*lengthY);
    }
}