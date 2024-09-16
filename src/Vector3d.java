import processing.core.PApplet;

public class Vector3d {
    float x,y,z;

    public Vector3d(float _x,float _y,float _z){
        x = _x;
        y= _y;
        z = _z;
    }

    public static Vector3d newVector3d(float _x, float _y, float _z){
        Vector3d vector3d = new Vector3d(_x,_y,_z);
        Main.app.vectors = (Vector3d[]) PApplet.append(Main.app.vectors, vector3d);
        return vector3d;
    }



}
