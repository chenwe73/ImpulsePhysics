package thread;

import frame.Main;
import object.*;

public class Engine extends Thread
{
	static long timeLast; // time of end of last loop (milliseconds)
	static long timeNow; // time of end of current loop (milliseconds)
	static long sleepTime; // amount of sleep (milliseconds)
	public static double fpsCap = 60; // frame per second capacity (Hz)
	public static double fps = fpsCap; // frame per second (Hz)
	
	public static Sphere ball1 = new Sphere(new Position(-1, 2), 1, new Vector(0, 0), 0.2, 0.6);
	public static Sphere ball2 = new Sphere(new Position(1, 2), 1, new Vector(0, 0), 0.2, 0.6);
	public static Plane plane1 = new Plane(new Vector (0, 1), new Position(0, -00));
	public static Plane plane2 = new Plane(new Vector (0, -1), new Position(0, 10));
	public static Plane plane3 = new Plane(new Vector (1, 0), new Position(-10, 0));
	public static Plane plane4 = new Plane(new Vector (-1, 0), new Position(10, 0));
	public long time = 0;
	
	public void run()
	{
		try
		{
			time = System.currentTimeMillis();
			while (true)
			{
				ball1.applyGravity();
				ball2.applyGravity();
				ball1.applyRepulsion(Main.game.mouse.mouse, 10, 1);
				ball1.applyFriction(0);
				ball1.collide(ball2);
				
				ball1.reflect(plane1);
				ball2.reflect(plane1);
				ball1.reflect(plane2);
				ball2.reflect(plane2);
				ball1.reflect(plane3);
				ball2.reflect(plane3);
				ball1.reflect(plane4);
				ball2.reflect(plane4);
				
				ball1.integrate();
				ball2.integrate();
				plane1.integrate();
				plane2.integrate();
				plane3.integrate();
				plane4.integrate();
				System.out.println("ball1: " + ball1);
				//System.out.println("time: " + (System.currentTimeMillis() - time));
				
				Main.game.repaint();
				sleepTime = Math.round(1000.0 / fpsCap) - System.currentTimeMillis() + timeNow;
				if (sleepTime > 0) // have spare time
					Thread.sleep(sleepTime + 0); // + a value for artificial slow motion
				else; // running behind
				updatefps();
			}
		}
		catch (InterruptedException e)
		{
		};
	}
	
	public static void updatefps ()
	{
		timeLast = timeNow;
		timeNow = System.currentTimeMillis();
		fps = 1000.0 / (timeNow - timeLast);
	}
	
	
}