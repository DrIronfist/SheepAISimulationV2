
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class Visualizer
{
	public static void main(String...args)
	{
		JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
		MyPanelb m = new MyPanelb();
		j.setSize(m.getSize());
		j.add(m); //adds the panel to the frame so that the picture will be drawn
			      //use setContentPane() sometimes works better then just add b/c of greater efficiency.

		// Input

		j.setVisible(true); //allows the frame to be shown.

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
	}

}

class MyPanelb extends JPanel implements ActionListener
{
	
	private Timer time;
    private int frame = 0;

    private int spheres = 1000;

    private Vector2[] spherePos = new Vector2[spheres];
    private double[] sphereRadius = new double[spheres];
	
	MyPanelb()
	{
		time = new Timer(15, this); //sets delay to 15 millis and calls the actionPerformed of this class.
		setSize(800, 810);
		setVisible(true); //it's like calling the repaint method.
		time.start();
        
        for (int i = 0; i < spheres; i++)
        {
            double sphereAngle = Math.random()*2*Math.PI;
            double sphereDistance = 150 + Math.random()*300;
            spherePos[i] = new Vector2(Math.cos(sphereAngle)*sphereDistance + 400, Math.sin(sphereAngle)*sphereDistance + 400);
            sphereRadius[i] = Math.random()*40+10;
        }
	}
	
	public void paintComponent(Graphics g)
	{
        frame++;
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 810);

        g.setColor(Color.black);
        for (int i = 0; i < spheres; i++)
        {
            g.drawArc(
                (int) ( spherePos[i].x - sphereRadius[i] ), 
                (int) ( spherePos[i].y - sphereRadius[i] ), 
                (int) sphereRadius[i]*2, 
                (int) sphereRadius[i]*2, 
                0, 360);
        }

        Vector2 rayStart = new Vector2(400, 400);
        double rayAngle = Math.atan2(Math.sin((double)frame/100), Math.cos((double)frame/100));
        double rayDistance = 10000;

        // g.setColor(Color.green);

        g.setColor(Color.red);
        long startTime = System.nanoTime();
        
        for (int i = 0; i < 100; i++)
        {
            double rangle = rayAngle+(Math.PI*2/100 * i);
            rangle = Math.atan2(Math.sin(rangle), Math.cos(rangle));

            g.drawLine(
                (int) rayStart.x, 
                (int) rayStart.y, 
                (int) (rayStart.x + Math.cos(rangle) * rayDistance), 
                (int) (rayStart.y + Math.sin(rangle) * rayDistance)
                );

            Raycasting.RayResult ray = Raycasting.raycast(rayStart, rangle, rayDistance, spherePos, sphereRadius);

            if (ray != null)
            {
                Vector2 pos = rayStart.add(Vector2.polar(ray.distance, rangle));
                g.drawArc(
                    (int) (pos.x - 5 ), 
                    (int) (pos.y - 5 ), 
                    (int) 5*2, 
                    (int) 5*2, 
                    0, 360);
            }
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println(duration/1000000 );
	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}

	
}
