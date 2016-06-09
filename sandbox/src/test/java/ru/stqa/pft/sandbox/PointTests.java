package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import static ru.stqa.pft.sandbox.MyFirstProgram.distance;

/**
 * Created by Антон on 16.04.2016.
 */
public class PointTests {

    @Test
    public void testPointDistance1(){
        Point p1 = new Point();
        p1.x = 1;
        p1.y = 1;
        Point p2 = new Point();
        p2.x = 4;
        p2.y = 5;
        Assert.assertEquals(distance(p1,p2),4.0);
    }
    @Test
    public void testPointDistance2(){
        Point p3 = new Point(100,100);
        Point p4 = new Point(97,96);
        Assert.assertEquals(p3.distance(p4),5.0);
    }
    @Test
    public void testPointDistance3(){
        Point p3 = new Point(100,100);
        Point p4 = new Point(97,96);
        Assert.assertEquals(p3.distance(p4),5.0);
    }
}
