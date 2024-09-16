import processing.core.PApplet;

public class Face {
    Point[] facePoints;

    Vector3d normalVector;

    Point p1,p2,p3;

    public Face(Point ... _facePoints) {
        facePoints = _facePoints;
        p1 = facePoints[0];
        p2 = facePoints[1];
        p3 = facePoints[2];
        checkCoplanar();

        normalVector = calculateNormal(this);
    }

    private void checkCoplanar(){
        if (facePoints.length < 3) {//you need 3 points to make a face
            throw new IllegalArgumentException("At least 3 arguments are required.");
        }

        for (Point p : facePoints){
            float determinant = (p2.x - p1.x) * ((p3.y - p1.y) * (p.z - p1.z) - (p3.z - p1.z) * (p.y - p1.y))
                    - (p2.y - p1.y) * ((p3.x - p1.x) * (p.z - p1.z) - (p3.z - p1.z) * (p.x - p1.x))
                    + (p2.z - p1.z) * ((p3.x - p1.x) * (p.y - p1.y) - (p3.y - p1.y) * (p.x - p1.x));
            if (Math.abs(determinant) >= 1e-6) {
                throw new IllegalArgumentException("All points in the Face must be coplanar");
            }
        }

    }
    //cross product gives a vector perpendicular to the face
    public static Vector3d crossProduct(Vector3d v1, Vector3d v2) {
        return new Vector3d(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }
    //allows us to subtract two faces
    public static Vector3d subtract(Point p1, Point p2) {
        return new Vector3d(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z);
    }

    //allows us to calculate the normal vector of the plane
    public static Vector3d calculateNormal(Face face) {
        Vector3d v1 = subtract(face.facePoints[1], face.facePoints[0]);
        Vector3d v2 = subtract(face.facePoints[2], face.facePoints[0]);
        return crossProduct(v1, v2);
    }


    public static Face newFace(Point ... _facePoints){
        Face face = new Face(_facePoints);
        Main.app.faces = (Face[]) PApplet.append(Main.app.faces, face);
        return face;
    }





}

