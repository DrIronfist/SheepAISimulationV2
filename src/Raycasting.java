public class Raycasting {
    public static class RayResult
    {
        public double distance;
        public int hitIndex;
        public Vector2[] debugVectors;

        public RayResult(int i, double d)
        {
            distance = d;
            hitIndex = i;
        }

        public RayResult(Vector2[] debug)
        {
            debugVectors = debug;
        }
    }

    public static double[] convertToABC(double x, double y, double a)
    {
        return new double[] {
            -Math.sin(a),                 // a
            Math.cos(a),
            Math.sin(a)*x -Math. cos(a)*y
        };
    }

    public static boolean withinAngle(double targetAngle, double targetAngleWidth, double angle)
    {
        double anglediff = (angle - targetAngle + Math.PI*3) % (Math.PI*2) - Math.PI;

        return (anglediff <= targetAngleWidth && anglediff>=-targetAngleWidth);
    }

    public static double compareAngles(double sourceAngle, double otherAngle)
    {
        // sourceAngle and otherAngle should be in the range -180 to 180
        System.out.println(""+sourceAngle+" "+otherAngle+" "+(otherAngle - sourceAngle));
        double difference = otherAngle - sourceAngle;

        if(difference < -Math.PI)
            difference += Math.PI*2;
        if(difference > Math.PI)
            difference -= Math.PI*2;

        return difference;
    }

    public static RayResult raycast(Vector2 pos, double angle, double maxDistance, Vector2 spherePos, double sphereRadius)
    {
        double[] abc = convertToABC(pos.x, pos.y, angle);
        double a, b, c;
        a = abc[0];
        b = abc[1];
        c = abc[2];

        int closestIndex = -1;

        double dist = (Math.abs(a * spherePos.x + b * spherePos.y + c)) / Math.sqrt(a * a + b * b);

        if (sphereRadius >= dist)
        {
            double sphereAngle = Math.atan2(spherePos.y-pos.y, spherePos.x-pos.x);

            Vector2 linePoint;

            // Vector2 linePoint = spherePos.add(Vector2.polar(dist, angle+Math.PI/2));
            // Vector2 linePoint2 = spherePos.add(Vector2.polar(dist, angle-Math.PI/2));

            // Is touching
            double dif = compareAngles(angle, sphereAngle);

            if (Math.abs(dif) >= Math.PI/2)
                return null;

            if (dif < 0)
                linePoint = spherePos.add(Vector2.polar(dist, angle+Math.PI/2));
            else
                linePoint = spherePos.add(Vector2.polar(dist, angle-Math.PI/2));

            double sphereIn = Math.sqrt(sphereRadius*sphereRadius - dist*dist);

            Vector2 p1 = linePoint.add(Vector2.polar(sphereIn, angle));
            Vector2 p2 = linePoint.sub(Vector2.polar(sphereIn, angle));

            double p1d = p1.sub(pos).magnitude();
            double p2d = p2.sub(pos).magnitude();

            Vector2[] debugArray;

            if (p1d > p2d)
                debugArray = new Vector2[] {linePoint, p2};
            else 
                debugArray = new Vector2[] {linePoint, p1};

            return new Raycasting.RayResult(debugArray);
        }


        // for (int i = 0; i < spherePos.length; i++)
        // {
        //     double dist = (Math.abs(a * spherePos[i].x + b * spherePos[i].y + c)) / Math.sqrt(a * a + b * b);

        //     if (sphereRadius[i] >= dist)
        //     {
        //         // Is touching
        //         Vector2 linePoint = spherePos[i].add(Vector2.polar(dist, angle+Math.PI/2));
        //         return new Raycasting.RayResult(linePoint);
        //     }
        // }

        return null;
    }

    public double hits(double a, double b, double c, double x, double y, double r)
    {
        // double h = r*r - ();
        return 0;
    }
}
