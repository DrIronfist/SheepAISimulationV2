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
        // System.out.println(""+sourceAngle+" "+otherAngle+" "+(otherAngle - sourceAngle));
        double difference = otherAngle - sourceAngle;

        if(difference < -Math.PI)
            difference += Math.PI*2;
        if(difference > Math.PI)
            difference -= Math.PI*2;

        return difference;
    }

    public static RayResult raycast(Vector2 pos, double angle, double maxDistance, Vector2[] spherePos, double[] sphereRadius)
    {
        double[] abc = convertToABC(pos.x, pos.y, angle);
        double a, b, c;
        a = abc[0];
        b = abc[1];
        c = abc[2];

        int closestIndex = -1;
        double closestDistance = Double.MAX_VALUE;

        for (int i = 0; i < spherePos.length; i++)
        {
            Vector2 sp = spherePos[i];
            double sr = sphereRadius[i];
            double dif = compareAngles(angle, Math.atan2(sp.y-pos.y, sp.x-pos.x));

            if (Math.abs(dif) >= Math.PI/2 || sp.sub(pos).magnitude() > maxDistance + sr )
                continue;

            double dist = (Math.abs(a * sp.x + b * sp.y + c)) / Math.sqrt(a * a + b * b);

            if (sr < dist)
                continue;
            
            Vector2 linePoint;
            // Is touching

            if (dif < 0)
                linePoint = sp.add(Vector2.polar(dist, angle+Math.PI/2));
            else
                linePoint = sp.add(Vector2.polar(dist, angle-Math.PI/2));

            Vector2 sphereIn = Vector2.polar(Math.sqrt(sr*sr - dist*dist), angle);

            Vector2 p1 = linePoint.add(sphereIn);
            Vector2 p2 = linePoint.sub(sphereIn);

            double dis = Math.min(p1.sub(pos).magnitude(), p2.sub(pos).magnitude());

            if (dis < closestDistance)
            {
                closestIndex = i;
                closestDistance = dis;
            }
            
        }

        return new Raycasting.RayResult(closestIndex, closestDistance);
    }

    public double hits(double a, double b, double c, double x, double y, double r)
    {
        // double h = r*r - ();
        return 0;
    }
}
