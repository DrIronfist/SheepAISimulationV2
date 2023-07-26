import java.util.ArrayList;

class TickStats
{
    public int GrassEaten = 0;
    public int Births = 0;
    public int Population = 0;
    public int TotalGrass = 0;

    public static String[] columns = new String[] {"GrassEaten", "Births", "Population", "TotalGrass"};

    public static void toCSV(TickStats[] stats, String file_name)
    {
        String[][] dfArray = new String[stats.length][4];
        for (int i = 0; i < dfArray.length; i++)
        {
            dfArray[i][0] = ""+stats[i].GrassEaten;
            dfArray[i][1] = ""+stats[i].Births;
            dfArray[i][2] = ""+stats[i].Population;
            dfArray[i][3] = ""+stats[i].TotalGrass;
        }

        DataFrame<String> df = new DataFrame<String>(dfArray, columns);
        df.toCSV(file_name);
    }
}

public class Simulation {
    public ArrayList<Sheep> allSheep;
    public ArrayList<Sheep> newSheep;
    public ArrayList<Vector2> food;

    double mapSize = 800;
    int StartingSheep = 30;
    double foodSpawnRate = 20;
    int MaxFood = 2500;

    double foodSpawn = 0;

    public void spawnRandomSheep()
    {
        Sheep sheep = new Sheep();
        sheep.pos = new Vector2(mapSize*Math.random(), mapSize*Math.random());
        sheep.rot = Math.random()*Math.PI*2;
        sheep.gender = (int) Math.floor(Math.random()*2);

        allSheep.add(sheep);
    }

    public Simulation()
    {
        allSheep = new ArrayList<Sheep>();
        newSheep = new ArrayList<Sheep>();

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

    private void Birth(Sheep p1, Sheep p2)
    {
        Sheep child = new Sheep();
        child.pos = p1.pos;
        child.rot = Math.random()*Math.PI*2;;
        child.gender = (int) Math.floor(Math.random()*2);
        child.hunger = .5;

        newSheep.add(child);
    }

    private boolean HandleFemaleSheep(Sheep sheep)
    {
        if (sheep.age < Sheep.eggMinAge) {return false;}

        if (!sheep.hasEggs) 
        {
            if (Math.floor(Math.random()*Sheep.eggChance) == 0)
                sheep.hasEggs = true;
        } 
        else if (sheep.pregnant == null)
        {
            // Looking for a mate
            Sheep closestPartner = null;
            double distance = Sheep.MaxMateDistance;
            for (Sheep sheep2 : allSheep)
            {
                double dist = sheep.pos.sub(sheep2.pos).magnitude();
                if (sheep2.gender == 0 && dist < distance )
                {
                    distance = dist;
                    closestPartner = sheep2;
                }
            }

            if (closestPartner != null)
            {
                sheep.pregnant = closestPartner;
                sheep.pregnantPeriod = 0;
            }
        } else {
            sheep.pregnantPeriod++;
            if (sheep.pregnantPeriod >= Sheep.maxPregnantPeriod && sheep.hunger > .5)
            {
                Birth(sheep, sheep.pregnant);
                sheep.pregnant = null;
                sheep.hasEggs = false;
                return true;
            }
        }
        return false;
    }

    public TickStats Tick()
    {
        TickStats stats = new TickStats();

        // Vision
        for (int j = allSheep.size()-1; j > -1; j--)
        {       
            Sheep sheep = allSheep.get(j);
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
                // System.out.println("Dist: "+dist +" angle: "+angleDif );
                if (dist < nearestFoodD && Math.abs(angleDif) < Sheep.ViewAngle)
                {
                    nearestFoodD = dist;
                    nearestFoodAngle = angleDif;
                    nearestFood = i;
                }
            }

            sheep.age++;
            if (sheep.age >= Sheep.LifeSpan)
            {
                allSheep.remove(j);
            }

            // Sheep AI (Temp)
            double MoveSpeed;
            double TurnSpeed;

            // Epic AI
            if (nearestFood == -1)
            {
                MoveSpeed = .4;
                TurnSpeed = .5;
            } else {
                TurnSpeed =  Math.signum(nearestFoodAngle);
                MoveSpeed = 1;
            }

            // Eating
            double EatingRange = 10; // Temp Var
            if (nearestFoodD < EatingRange )
            {
                sheep.hunger = 1;
                stats.GrassEaten++;
                food.remove(nearestFood);
            }

            // Female
            if (sheep.gender == 1)
            {
                if (HandleFemaleSheep(sheep))
                    stats.Births++;
            }

            sheep.hunger -= Sheep.starveRate;

            if (sheep.hunger <= 0)
            {
                allSheep.remove(j);
            }

            // sheep.age++;

            // Movement
            double newRot = sheep.rot + TurnSpeed*Sheep.maxTurnSpeed;
            sheep.rot = Math.atan2(Math.sin(newRot), Math.cos(newRot));

            Vector2 newpos = sheep.pos.add(Vector2.polar(MoveSpeed * Sheep.maxSpeed, sheep.rot)); 
            sheep.pos = new Vector2(Clamp(newpos.x, 0, mapSize), Clamp(newpos.y, 0, mapSize));
        }

        // Adding Food
        if (food.size() < MaxFood)
        {
            foodSpawn += foodSpawnRate;
            while (foodSpawn >= 1)
            {
                food.add(new Vector2(Math.random()*mapSize, Math.random()*mapSize));
                foodSpawn--;
            }
        }

        for (int j = newSheep.size()-1; j > -1; j--)
        {
            allSheep.add(newSheep.get(j));
            newSheep.remove(j);
        }

        stats.Population = allSheep.size();
        stats.TotalGrass = food.size();

        return stats;
    }
}
