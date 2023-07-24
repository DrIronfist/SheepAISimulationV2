
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
    private Vector2 circleStartPos = new Vector2(300, 300);
    private Vector2 circlePos = circleStartPos;
    private int frame = 0;
	
	MyPanelb()
	{
		time = new Timer(15, this); //sets delay to 15 millis and calls the actionPerformed of this class.
		setSize(800, 810);
		setVisible(true); //it's like calling the repaint method.
		time.start();
	
	}
	
	public void paintComponent(Graphics g)
	{
        frame++;
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 810);

        g.setColor(Color.black);
        double circleRadius = 50;

        circlePos = circlePos.add(new Vector2(1, 0));
        if (circlePos.x > 800)
            circlePos = circleStartPos;

        g.drawArc(
            (int) (circlePos.x - circleRadius ), 
            (int) (circlePos.y - circleRadius ), 
            (int) circleRadius*2, 
            (int) circleRadius*2, 
            0, 360);

        Vector2 rayStart = new Vector2(600, 500);
        double rayAngle = Math.PI / (-2) + Math.sin((double)frame/100);
        double rayDistance = 1000;

        g.setColor(Color.green);
        g.drawLine(
            (int) rayStart.x, 
            (int) rayStart.y, 
            (int) (rayStart.x + Math.cos(rayAngle) * rayDistance), 
            (int) (rayStart.y + Math.sin(rayAngle) * rayDistance)
            );

        Raycasting.RayResult ray = Raycasting.raycast(rayStart, rayAngle, rayDistance, circlePos, circleRadius);

        Color[] debugColors = new Color[] {Color.red, Color.blue, Color.ORANGE};

        if (ray != null)
        {
            for (int i = 0; i < ray.debugVectors.length; i++)
            {
                g.setColor(debugColors[i]);
                g.drawArc(
                    (int) (ray.debugVectors[i].x - 5 ), 
                    (int) (ray.debugVectors[i].y - 5 ), 
                    (int) 5*2, 
                    (int) 5*2, 
                    0, 360);
            }
        }
	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}

	
}
