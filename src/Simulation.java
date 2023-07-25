import java.util.ArrayList;

public class Simulation {
    public ArrayList<Sheep> allSheep;

    public Simulation()
    {
        allSheep = new ArrayList<Sheep>();
        allSheep.add(new Sheep());
    }

    public void Tick()
    {
        // Vision
        for (Sheep sheep : allSheep) {
            
        }
    }
}
