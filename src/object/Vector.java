package object;

import java.awt.Graphics;

// 2D Vector

public class Vector 
{
	private double x; // x components in meters
	private double y; // y components in meters
	
	// **********Constructor**********
	
	// Constructor
	public Vector ()
	{
		this(0, 0);
	}
	
	// Standard Constructor
	public Vector (double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	// Copy Constructor
	public Vector (Vector other)
	{
		this.x = other.x;
		this.y = other.y;
	}	
	
	// Angle Constructor unit Vector
	public Vector (double angle)
	{
		angle %= 2 * Math.PI;
		x = Math.cos(angle);
		y = Math.sin(angle);
	}
	
	// **********Accessor**********
	
	// Accessing x
	protected double getX ()
	{
		return x;
	}

	// Accessing y
	protected double getY ()
	{
		return y;
	}
	
	// **********Information**********
	
	// Overriding toString
	public String toString ()
	{
		return String.format("[%10.4f, %10.4f]", x, y);
	}
	
	// Getting angle
	public double angle ()
	{
		double angle = 0;
		if (x < 0)
			if (y >= 0)
				angle = Math.atan(y / x) + Math.PI;
			else
				angle = Math.atan(y / x) - Math.PI;
		else
			angle = Math.atan(y / x);
		return angle;
	}
	
	// Getting angle between two vectors
	public double angle (Vector other)
	{
		double angle = Math.abs(this.angle() - other.angle());
		if (angle > Math.PI)
			return 2 * Math.PI - angle;
		else
			return angle;
	}
	
	// Getting magnitude
	public double magnitude ()
	{
		return Math.sqrt(x * x + y * y);
	}
	
	// Cross product (z component)
	public double cross (Vector other)
	{
		return x * other.y - y * other.x;
	}

	// Dot product
	public double dot (Vector other)
	{
		return x * other.x + y * other.y;
	}
	
	// **********Boolean**********
	
	// Zero
	public boolean isZero ()
	{
		return magnitude() == 0;
	}
	
	// Equality
	public boolean equals (Vector other)
	{
		return x == other.x && y == other.y;
	}
	
	// Parallel
	public boolean isParallel (Vector other)
	{
		return x / other.x == y / other.y;
	}
	
	// Perpendicular
	public boolean isPerpendicular (Vector other)
	{
		return this.dot(other) == 0;
	}
	
	// **********Changing values**********
	
	// Setting new x, y
	protected void setComponents (double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	// Scalar multiple
	public void multiply (double num)
	{
		x *= num;
		y *= num;
	}
	
	// Adding
	public void add (Vector other)
	{
		x += other.x;
		y += other.y;
	}
	
	// **********Vectors**********
	
	// Adding
	public Vector sum (Vector other)
	{
		Vector sum = new Vector(this);
		sum.add(other);
		return sum;
	}
	
	// Getting multiple Vector
	public Vector multiple (double num)
	{
		Vector multiple = new Vector(this);
		multiple.multiply(num);
		return multiple;
	}
	
	// Getting unit Vector
	public Vector unit ()
	{
		return this.multiple(1 / this.magnitude());
	}
	
	// Component on a Vector Direction
	public double component (Vector screen)
	{
		return this.dot(screen) / screen.magnitude();
	}
	
	// Projection
	public Vector projection (Vector screen)
	{
		return screen.multiple(this.dot(screen) / screen.dot(screen));
	}
	
	// Perpendicular
	public Vector Perpendicular ()
	{
		return new Vector(-this.y, this.x);
	}
	
	// **********Graphics**********
	
	//Graphics with respect to the origin
	public void draw(Graphics g)
	{
		draw(g,new Position());
	}
	
	// Standard draw
	public void draw(Graphics g, Position tail)
	{
		//g.setColor(Color.BLACK);
		Position.drawLine(g, tail, tail.displacement(this));
	}
}
