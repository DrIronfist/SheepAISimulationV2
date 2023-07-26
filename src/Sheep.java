
public class Sheep {

   //constants
   public static double starveRate = 0.005,  thirstRate = 0.01;

   public static double maxSpeed = 1;
   public static double maxTurnSpeed = .05;

   public static double ViewDistance = 100;
   public static double ViewAngle = Math.PI/4;

   public static int LifeSpan = 3000;

   public static double pregnantHungerCost = .5;
   public static int eggMinAge = 200;
   public static int eggChance = 100;
   public static double MaxMateDistance = 100;
   public static int maxPregnantPeriod = 100;

   //sheep variables
   public double hunger = 1;
   public double thirst = 1;
   public int age = 0;
   public Vector2 pos;
   public double rot;

   // O male, 1 Female
   public int gender;

   public boolean hasEggs;
   public Sheep pregnant;
   public int pregnantPeriod; 

   public Sheep()
   {
      
   }

   public Sheep(Sheep p1, Sheep p2){
      pos = p1.pos.add(p2.pos).scale(0.5);
   }
}
