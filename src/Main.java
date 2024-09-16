import processing.core.PApplet;

public class Main extends PApplet {
    public static Main app;

    public int screenWidth = 900;
    public int screenHeight = 700;

    public Ray camera;
    public Point camOrigin;
    public Vector3d camDir;
    public Face screen;

    public Point screenOrig;

    public Point screenP1;
    public Point screenP2;
    public Point screenP3;
    public Point screenP4;

    public float camDirX = 0;
    public float camDirY = 0;
    public float camDirZ = 1;
    public float camX = 0;
    public float camY = (float) screenHeight /2;
    public float camZ = 0;
    public float camDistance = 10;




    public Point[] points = {};
    public Ray[] rays = {};
    public Face[] faces = {};
    public Vector3d[] vectors = {};



    public void setup() {
        app = this;
        camOrigin = Point.newPoint(camX,camY,camZ);
        camDir = Vector3d.newVector3d(camDirX,camDirY,camDirZ);
        camera = Ray.newLine(camOrigin,camDir, camDistance);

        screenOrig  = Point.newPoint(
                camOrigin.x + (camDirX* camDistance),
                camOrigin.y +(camDirY* camDistance),
                camOrigin.z +(camDirZ* camDistance)
        );
        screenP1 = Point.newPoint(
                screenOrig.x - (float) screenWidth /2,
                screenOrig.y + (float) screenHeight /2,
                screenOrig.z
        );
        screenP2 = Point.newPoint(
                screenOrig.x - (float) screenWidth /2,
                screenOrig.y - (float) screenHeight /2,
                screenOrig.z
        );
        screenP3 = Point.newPoint(
                screenOrig.x + (float) screenWidth /2,
                screenOrig.y - (float) screenHeight /2,
                screenOrig.z
        );
        screenP4 = Point.newPoint(
                screenOrig.x + (float) screenWidth /2,
                screenOrig.y + (float) screenHeight /2,
                screenOrig.z
        );

        screen = Face.newFace(screenP1,screenP2,screenP3,screenP4);
        Graphics.castRays();
    }

    public void settings() {
        size(screenWidth, screenHeight);
    }



    public void draw(){
        background(0);

        if(mousePressed) {
            Main.app.fill(255);
            ellipse(mouseX, mouseY, 20, 20);
        }
        if (keyPressed) {
            if (key == 'w') {
                System.out.println("w");
            }
            keyPressed = false;

        }

        fill(255);
        textSize(20);

    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "Main" };
        PApplet.main(appletArgs);

    }
}
