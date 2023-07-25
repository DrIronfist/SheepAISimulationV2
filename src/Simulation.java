import java.util.ArrayList;

public class Simulation {
    public ArrayList<Sheep> allSheep;
    public ArrayList<Vector2> food;

    double mapSize = 800;

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

        spawnRandomSheep();
        spawnRandomSheep();
        spawnRandomSheep();
        spawnRandomSheep();
        spawnRandomSheep();
        spawnRandomSheep();
        spawnRandomSheep();
        spawnRandomSheep();


        food = new ArrayList<Vector2>();
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
                if (dist < nearestFoodD && Math.abs(angleDif) > Math.PI/4)
                {
                    nearestFoodD = dist;
                    nearestFoodAngle = angleDif;
                    nearestFood = i;
                }
            }

            // Sheep AI (Temp)
            double MoveSpeed;
            double TurnSpeed;

            // Epic AI
            MoveSpeed = 1;
            TurnSpeed = Math.signum(nearestFoodAngle);

            // Eating
            double EatingRange = 5; // Temp Var
            if (nearestFoodD < EatingRange )
            {
                
                food.remove(nearestFood);
            }

            // Movement
            sheep.rot = sheep.rot + TurnSpeed*Sheep.maxTurnSpeed;
            sheep.pos = sheep.pos.add(Vector2.polar(MoveSpeed * Sheep.maxSpeed, sheep.rot));
        }

        // Adding Food
        if (food.size() < 100)
            food.add(new Vector2(Math.random()*mapSize, Math.random()*mapSize));
    }
}
