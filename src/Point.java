import processing.core.PApplet;

public class Point {

    float x,y,z;
    public Point(float _x, float _y, float _z){
        x = _x;
        y = _y;
        z = _z;
    }

    public static Point newPoint(float _x, float _y, float _z){
        Point point =  new Point(_x,_y,_z);
        Main.app.points = (Point[]) PApplet.append(Main.app.points, point);
        return point;
    }



}
