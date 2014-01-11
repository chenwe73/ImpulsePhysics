package object;

import java.awt.Graphics;

// 2D Plane that reflects Sphere

public class Plane 
{
	private Vector normal;
	private Position position;
	
	// **********Constructor**********
	
	// Empty Constructor
	public Plane ()
	{
		this.normal = new Vector (0, 1);
		this.position = new Position ();
	}
	
	// Standard Constructor
	public Plane (Vector normal, Position center)
	{
		this.normal = normal;
		this.position = center;
	}
	
	// Copy Constructor
	public Plane (Plane other)
	{
		this.normal = other.normal;
		this.position = other.position;
	}
	
	// **********Accessor**********
	
	// Accessing normal
	public Vector getNormal ()
	{
		return normal;
	}
	
	// Accessing position
	public Position getPosition ()
	{
		return position;
	}
	
	// **********Information**********
	
	// Overriding toString
	public String toString ()
	{
		return String.format("Normal: " + normal + " Center: " + position);
	}
	
	// **********Changing values**********
	
	// Integration
	public void integrate ()
	{
		position.integrate();
	}
	
	// **********Graphics**********
	
	// Standard draw
	public void draw (Graphics g, double length)
	{
		Position end1 = position.displacement(normal.Perpendicular().unit().multiple(length / 2));
		Position end2 = position.displacement(normal.Perpendicular().unit().multiple(length / -2));
		Position.drawLine(g, position, end1);
		Position.drawLine(g, position, end2);
	}
	
	// Drawing normal Vector
	public void drawNormal (Graphics g, double length)
	{
		normal.draw(g, position);
	}
}
