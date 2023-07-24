public class Vector2
{              
    // Members
    public double x;
    public double y;
       
    // Constructors
    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }
       
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 add(Vector2 a, Vector2 b)
    {
        return new Vector2(a.x+b.x, a.y+b.y);
    }

    public static Vector2 sub(Vector2 a, Vector2 b)
    {
        return new Vector2(a.x-b.x, a.y-b.y);
    }

    public static Vector2 scale(Vector2 a, double b)
    {
        return new Vector2(a.x*b, a.y*b);
    }

    public static double distance(Vector2 a, Vector2 b)
    {
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    public static double magnitude(Vector2 a)
    {
        return Math.sqrt(a.x*a.x + a.y*a.y);
    }

    public static Vector2 normalize(Vector2 a)
    {
        if (a.magnitude() == 0)
            return Vector2.zero;
        return new Vector2(a.x / a.magnitude(), a. y/ a.magnitude());
    }

    public Vector2 add(Vector2 b)
    {return Vector2.add(this, b);}

    public Vector2 sub(Vector2 b)
    {return Vector2.sub(this, b);}

    public Vector2 scale(double b)
    {return Vector2.scale(this, b);}

    public double distance(Vector2 b)
    {return Vector2.distance(this, b);}

    public double magnitude()
    {return Vector2.magnitude(this);}

    public Vector2 normalize()
    {return Vector2.normalize(this);}

    public static Vector2 polar(double radius, double theta)
    {
        return new Vector2(Math.cos(theta)*radius, Math.sin(theta)*radius);
    }
       
    // Compare two vectors
    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    // Starting Vectors
    public static Vector2 zero = new Vector2();
    public static Vector2 up = new Vector2(0, 1);
    public static Vector2 down = new Vector2(0, -1);
    public static Vector2 left = new Vector2(-1, 0);
    public static Vector2 right = new Vector2(1, 0);

    public String toString()
    {
        return ""+x+" "+y;
    }
}
