package frame;

import control.GameKey;
import control.GameMouse;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import thread.Engine;
import object.*;

@SuppressWarnings("serial")
// The game component class that displays the game play
public class Game extends JComponent
{
	public GameMouse mouse = new GameMouse();
	public GameKey key = new GameKey();
	public Engine engine = new Engine();
	
	static long timeLast; // time of end of last loop (millisecond)
	static long timeNow; // time of end of current loop (millisecond)
	static double fpsscr; // screen FPS (Hz)
	
	// The constructor for the game component
	public Game()
	{
		setSize(Main.FRAMEWIDTH, Main.FRAMEHEIGHT);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(key);
		addMouseWheelListener(mouse);
		setFocusable(true);
		//setVisible(false);
		
		engine.start();
	}
	
	// The overridden method for using keyboard
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		requestFocus();
	}
	
	// To display the game graphics
	public void paint(Graphics g)
	{
		g.drawString(mouse.mouse.toString(), 500, 20);
		
		g.setColor(Color.black);
		Position.drawAxis(g, 1);
		g.drawString("FPS: "+thread.Engine.fps,10,20);
		g.drawString("FPSSCR: "+fpsscr,10,40);
		
		Engine.ball1.drawFill(g);
		//Engine.ball1.drawVelocity(g);
		Engine.ball2.draw(g);
		//Engine.ball2.drawVelocity(g);
		Engine.plane1.draw(g, 1000);
		Engine.plane2.draw(g, 1000);
		Engine.plane3.draw(g, 1000);
		Engine.plane4.draw(g, 1000);
		
		mouse.mouse.draw(g, 1);
		
		updatefps();
	}
	
	public static void updatefps ()
	{
		timeLast = timeNow;
		timeNow = System.currentTimeMillis();
		fpsscr = 1000.0 / (timeNow - timeLast);
	}
}