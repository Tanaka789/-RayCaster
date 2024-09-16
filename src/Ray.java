import processing.core.PApplet;

public class Ray {//this is without any directional values for simplicity
    Point p1;
    Point targetPoint;
    Vector3d vector;
    float magnitude;

    public Ray(Point _p1, Vector3d _vector, float _magnitude) {
        p1 = _p1;
        vector = _vector;
        magnitude = _magnitude;
        for (Face f : Main.app.faces){
            System.out.println(linePlaneIntersection(this,f));
        }
    }

    public static Ray newLine(Point _p1, Vector3d _vector, float _magnitude){
        Ray ray = new Ray(_p1,_vector, _magnitude);
        Main.app.rays = (Ray[]) PApplet.append(Main.app.rays, ray);
        return ray;
    }

    public static float dotProductV_P(Vector3d v1, Point v2) {//point to vector
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
    public static float dotProduct(Vector3d v1, Vector3d v2) {//vector to vector
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Point linePlaneIntersection(Ray ray, Face face) {//calculate if a ray hits a plane
        Vector3d normal = Face.calculateNormal(face);
        float d = -dotProductV_P(normal, face.facePoints[0]);

        Vector3d lineVector = ray.vector;
        float dot = dotProduct(normal, lineVector);

        if (Math.abs(dot) < 1e-6) {
            return null; // Ray is parallel to the plane
        }

        float t = -(dotProductV_P(normal, ray.p1) + d) / dot;

        if (t < 0 || t > 1) {
            return null; // No intersection within the segment
        }

        return new Point(
                ray.p1.x + t * lineVector.x,
                ray.p1.y + t * lineVector.y,
                ray.p1.z + t * lineVector.z

        );
    }

}
