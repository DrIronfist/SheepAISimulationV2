public class SimulationRunner {

    private static int TotalFrames = 10000;

    public static void main(String...args)
    {
        Simulation sim = new Simulation();

        TickStats[] stats = new TickStats[TotalFrames];

        System.out.println("Simulation Started");

        long startTime = System.nanoTime();

        for (int i = 0; i < TotalFrames; i++)
        {
            stats[i] = sim.Tick();
        }

        System.out.println("Simulation Ended");

        double duration = (double)((System.nanoTime() - startTime) / 1000000)/1000;
        System.out.println("Time Elapsed: "+ duration );

        TickStats.toCSV(stats, "output");
    }
}
