package object;

import java.awt.Graphics;

// Position Vector / Coordinates

public class Position extends Vector
{
	private double xCoord; // x coordinate in pixel on screen
	private double yCoord; // y coordinate in pixel on screen
	private static double mpp = 1; // meters per pixel
	private static Position origin = new Position(); // origin (0, 0)
	
	// **********Constructor**********
	
	// Constructor
	public Position ()
	{
		;
	}
	
	// Copy Constructor
	public Position (Position other)
	{
		super(other);
		xCoord = other.xCoord;
		yCoord = other.yCoord;
	}
	
	// Standard Constructor
	public Position (double x, double y)
	{
		super(x, y);
		integrate();
	}
	
	// **********Mutator**********
	
	// Mutating mpp
	public static void setMPP (double mpp)
	{
		Position.mpp = mpp;
	}
	
	// Multiplying mpp
	public static void zoom (double times)
	{
		Position.mpp *= times;
	}

	// Setting origin
	public static void setOrigin (double xCoord, double yCoord)
	{
		origin.xCoord = xCoord;
		origin.yCoord = yCoord;
	}
	
	// Moving origin
	public static void moveOrigin (Vector v)
	{
		origin.xCoord += v.getX();
		origin.yCoord += v.getY();
	}
	
	// **********Accessor**********
	
	// Accessing XCoord
	public int getXCoord ()
	{
		return (int)Math.round(xCoord);
	}
	
	// Accessing YCoord
	public int getYCoord ()
	{
		return (int)Math.round(yCoord);
	}
	
	// Accessing YCoord
	public double getMPP ()
	{
		return mpp;
	}
	
	// Accessing Origin
	public static Position getOrigin ()
	{
		return origin;
	}
	
	// **********Information**********
	
	// Overriding toString
	public String toString ()
	{
		return String.format("(%10.5f, %10.5f) @ {%10.5f, %10.5f}", 
				this.getX(), this.getY(), xCoord, yCoord);
	}
	
	// From meters to pixels
	public static double toPixel (double meter)
	{
		return meter / mpp;
	}
	
	// From pixels to meters
	public static double toMeters (double pixel)
	{
		return pixel * mpp;
	}
	
	// Difference Vector
	public Vector difference (Position tail)
	{
		return this.multiple(-1).sum(tail);
	}
	
	// Distance
	public double distance (Position other)
	{
		return this.difference(other).magnitude();
	}
	
	// **********Position**********
	public Position displacement (Vector displacement)
	{
		Position result = new Position (this);
		result.displace(displacement);
		return result;
	}
	
	// **********Changing values**********
	
	// Setting new Position
	public void teleportCoord (double xCoord, double yCoord)
	{
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.reverseIntegration();
	}
	
	// Updating xCoord, yCoord
	public void integrate ()
	{
		xCoord = origin.xCoord + Position.toPixel(this.getX());
		yCoord = origin.yCoord - Position.toPixel(this.getY());
	}
	
	// Reverse Integration
	public void reverseIntegration ()
	{
		final double X = Position.toMeters(xCoord - origin.xCoord);
		final double Y = Position.toMeters(-(yCoord - origin.yCoord));
		this.setComponents(X, Y);
	}
	
	// Displacement
	public void displace (Vector displacement)
	{
		this.add(displacement);
		integrate();
	}
	
	// **********Graphics**********
	
	// Graphics with default radius
	public void draw (Graphics g)
	{
		draw(g,10);
	}
	
	// Standard draw
	public void draw (Graphics g, double radius)
	{
		//g.setColor(Color.BLACK);
		int r = (int)(Position.toPixel(radius));
		g.drawOval(getXCoord() - r, getYCoord() - r, r * 2 , r * 2);
	}
	
	// Standard draw
	public void drawFill (Graphics g, double radius)
	{
		//g.setColor(Color.BLACK);
		int r = (int)(Position.toPixel(radius));
		g.fillOval(getXCoord() - r, getYCoord() - r, r * 2 , r * 2);
	}

	// Drawing Axis
	public static void drawAxis (Graphics g, double interval)
	{
		//g.setColor(Color.BLACK);
		int xO = origin.getXCoord();
		int yO = origin.getYCoord();
		int maxCood = 2000;
		g.drawLine(xO, 0, xO, maxCood);
		g.drawLine(0, yO, maxCood, yO);
		int r = 4;
		for (int i = -100; i < 100; i++)
		{
			int x = (int)(xO + Position.toPixel(interval) * i);
			int y = (int)(yO + Position.toPixel(interval) * i);
			g.fillOval(x - r / 2, yO - r / 2, r, r);
			g.fillOval(xO - r / 2, y - r / 2, r, r);
		}
	}
	
	// Drawing line
	public static void drawLine (Graphics g, Position tail, Position head)
	{
		g.drawLine(tail.getXCoord(), tail.getYCoord(), head.getXCoord(), head.getYCoord());
	}
}
