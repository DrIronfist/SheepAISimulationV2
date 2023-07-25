
public class Sheep {

   //constants
   public static double starveRate,  thirstRate = 0.01;

   public static double maxSpeed = 1;
   public static double maxTurnSpeed = .02;

   public static double ViewDistance = 100;
   public static double ViewAngle = Math.PI/4;

   public static int maxLifeSpan = 1000;
   public static int matingReq = 180;

   //sheep variables
   public double hunger = 1;
   public double thirst = 1;
   public int age = 0;
   public Vector2 pos;
   public double rot;

   public Sheep pregnant;

   public Sheep()
   {
      
   }

   public Sheep(Sheep p1, Sheep p2){
      pos = p1.pos.add(p2.pos).scale(0.5);
   }

   public void tickActions(){
      age++;
      hunger -= starveRate;
      // thirst -= thirstRate;
   }

   public void eat(){
      hunger+= 0.75;
      if(hunger > 1){
         hunger = 1;
      }
   }





}
