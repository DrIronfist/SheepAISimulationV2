public class Raycasting {
    public class RayResult
    {
        public double distance;
        public int hitIndex;

        public RayResult(int i, double d)
        {
            distance = d;
            hitIndex = i;
        }
    }

    public double[] convertToABC(double x, double y, double a)
    {
        return new double[] {
            -Math.sin(a),                 // a
            Math.cos(a),
            Math.sin(a)*x -Math. cos(a)*y
        };
    }

    public RayResult raycast(Vector2 pos, double angle, Vector2[] spherePos, double[] sphereRadius)
    {
        double[] abc = convertToABC(pos.x, pos.y, angle);
        double a, b, c;
        a = abc[0];
        b = abc[1];
        c = abc[2];

        int closestIndex = -1;


        for (int i = 0; i < spherePos.length; i++)
        {
            double dist = (Math.abs(a * spherePos[i].x + b * spherePos[i].y + c)) / Math.sqrt(a * a + b * b);

            if (sphereRadius[i] >= dist)
            {

            }
        }

        return null;
    }

    public double hits(double a, double b, double c, double x, double y, double r)
    {
        // double h = r*r - ();
        return 0;
    }
}
