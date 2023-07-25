import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class SimulationDisplay
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

    private Simulation sim;
	
	MyPanelb()
	{
		time = new Timer(15, this); //sets delay to 15 millis and calls the actionPerformed of this class.
		setSize(800, 810);
		setVisible(true); //it's like calling the repaint method.
		time.start();
        
        sim = new Simulation();
	}

    public void drawCircle(Graphics g, Vector2 pos, double radius)
    {
        g.fillArc(
            (int)(pos.x - radius), 
            (int)(pos.y - radius), 
            (int)(radius*2), 
            (int)(radius*2), 
            0, 360);
    }
	
	public void paintComponent(Graphics g)
	{
        frame++;
        sim.Tick();

        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);

        g.setColor(Color.black);
        for (Sheep sheep : sim.allSheep) {
            // Sheep
            drawCircle(g, sheep.pos, 10);

            double actualRot = Math.atan2(Math.sin(sheep.rot), Math.cos(sheep.rot));
            int arcStart = ((int)(-actualRot*180/Math.PI+360)%360);

            // g.drawLine(
            //     (int)(sheep.pos.x), 
            //     (int)(sheep.pos.y),
            //     (int)(sheep.pos.x +Math.cos(sheep.rot) * Sheep.ViewDistance), 
            //     (int)(sheep.pos.y +Math.sin(sheep.rot) * Sheep.ViewDistance)
            //     );

            g.drawLine(
                (int)(sheep.pos.x), 
                (int)(sheep.pos.y),
                (int)(sheep.pos.x +Math.cos(sheep.rot-Sheep.ViewAngle) * Sheep.ViewDistance), 
                (int)(sheep.pos.y +Math.sin(sheep.rot-Sheep.ViewAngle) * Sheep.ViewDistance)
                );

            g.drawLine(
                (int)(sheep.pos.x), 
                (int)(sheep.pos.y),
                (int)(sheep.pos.x +Math.cos(sheep.rot+Sheep.ViewAngle) * Sheep.ViewDistance), 
                (int)(sheep.pos.y +Math.sin(sheep.rot+Sheep.ViewAngle) * Sheep.ViewDistance)
                );

            g.drawArc(
                (int)(sheep.pos.x-Sheep.ViewDistance), 
                (int)(sheep.pos.y-Sheep.ViewDistance), 
                (int)(Sheep.ViewDistance*2), 
                (int)(Sheep.ViewDistance*2), 
                arcStart-45, 
                90
                );
        }

        for (Vector2 food : sim.food) {
            g.setColor(Color.green);
            
            drawCircle(g, food, 3);
        }
	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}

	
}
