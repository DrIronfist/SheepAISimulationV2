import java.util.ArrayList;

public class Simulation {
    public ArrayList<Sheep> allSheep;
    public ArrayList<Vector2> food;

    double mapSize = 800;
    int StartingSheep = 20;

    public void spawnRandomSheep()
    {
        Sheep sheep = new Sheep();
        sheep.pos = new Vector2(mapSize*Math.random(), mapSize*Math.random());
        sheep.rot = Math.random()*Math.PI*2;

        allSheep.add(sheep);
    }

    public Simulation()
    {
        allSheep = new ArrayList<Sheep>();

        for (int i = 0; i < StartingSheep; i++)
        {
            spawnRandomSheep();
        }


        food = new ArrayList<Vector2>();
    }

    private double Clamp(double val, double min, double max)
    {
        return Math.max(Math.min(val, max), min);
    }

    public void Tick()
    {
        // Vision
        for (Sheep sheep : allSheep) {
            int nearestFood = -1;
            double nearestFoodD = Sheep.ViewDistance; // Vision
            double nearestFoodAngle = 0;

            // Food Vision
            for (int i = 0; i < food.size(); i++)
            {
                Vector2 fpos = food.get(i);
                double dist = fpos.sub(sheep.pos).magnitude();
                double angle = Math.atan2(fpos.y-sheep.pos.y, fpos.x-sheep.pos.x);
                double angleDif = Raycasting.compareAngles(sheep.rot, angle);
                if (dist < nearestFoodD && Math.abs(angleDif) < +Sheep.ViewAngle)
                {
                    nearestFoodD = dist;
                    nearestFoodAngle = angleDif;
                    nearestFood = i;
                }
            }

            sheep.age = nearestFood;

            // Sheep AI (Temp)
            double MoveSpeed;
            double TurnSpeed;

            // Epic AI
            MoveSpeed = 1;
            TurnSpeed =  nearestFoodAngle > 0? 1:-1;

            // Eating
            double EatingRange = 10; // Temp Var
            if (nearestFoodD < EatingRange )
            {
                food.remove(nearestFood);
            }

            // Movement
            sheep.rot = sheep.rot + TurnSpeed*Sheep.maxTurnSpeed;
            Vector2 newpos = sheep.pos.add(Vector2.polar(MoveSpeed * Sheep.maxSpeed, sheep.rot)); 
            sheep.pos = new Vector2(Clamp(newpos.x, 0, mapSize), Clamp(newpos.y, 0, mapSize));
        }

        // Adding Food
        if (food.size() < 200)
            food.add(new Vector2(Math.random()*mapSize, Math.random()*mapSize));
    }
}
