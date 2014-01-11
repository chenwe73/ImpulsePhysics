package object;

import java.awt.Graphics;

// Fundamental particle that reacts to forces

public class Atom 
{
	private Position position; // position (m)
	private double mass; // (kg)
	private Vector velocityI; // initial velocity(m/s)
	private Vector velocityF; // final velocity(m/s)
	private static Vector g = new Vector (0, -9.8); // gravity (N / kg)
	private static double fps = 60; // frames per seconds (/s)
	
	// **********Constructor**********
	
	// Constructor
	public Atom ()
	{
		this(new Position(0, 0), 1);
	}
	
	// Copy Constructor
	public Atom (Atom other)
	{
		this (new Position(other.position), other.mass, new Vector(other.velocityF));
	}
	
	// Standard Constructor
	public Atom (Position position, double mass)
	{
		this.position = position;
		this.mass = mass;
		this.velocityI = new Vector(0, 0);
		this.velocityF = new Vector(0, 0);
	}
	
	// Constructor with initial velocity
	public Atom (Position position, double mass, Vector velocity)
	{
		this.position = position;
		this.mass = mass;
		this.velocityI = new Vector(velocity);
		this.velocityF = new Vector(velocity);
	}
	
	// **********Mutator**********
	
	// Setting g
	public static void setG (Vector g)
	{
		Atom.g = g;
	}
	
	// Setting fps
	public static void setFPS (double fps)
	{
		Atom.fps = fps;
	}
	
	// **********Accessor**********
	/*
	// Accessing g
	public static Vector getG ()
	{
		return g;
	}
	
	// Accessing fps
	public static double getFPS ()
	{
		return fps;
	}
	*/
	// Accessing mass
	public double getMass ()
	{
		return mass;
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
		return position.toString() + "  vi: " + velocityI.toString() + "(m/s)  vf: "
				+ velocityF.toString() + "(m/s)  m: " + mass + "(kg)";
	}
	
	// Getting net final Velocity
	public double netVelocity ()
	{
		return velocityF.magnitude();
	}
	
	// From frame to second
	public static double toSecond (double frame)
	{
		return frame / fps;
	}
	
	// From second to frame
	public static double toFrame (double second)
	{
		return second * fps;
	}
	
	// Distance
	public double distance (Position other)
	{
		return position.distance(other);
	}
	
	// Distance
	public double distance (Atom other)
	{
		return position.distance(other.position);
	}
	
	// Difference Vector
	public Vector difference (Position tail)
	{
		return position.difference(tail);
	}
	
	// Difference Vector
	public Vector difference (Atom tail)
	{
		return position.difference(tail.position);
	}
	
	// Velocity direction unit Vector
	public Vector direction ()
	{
		return velocityF.unit();
	}
	
	// Relative Velocity
	public Vector relativeV (Atom other)
	{
		return this.velocityF.sum(other.velocityF.multiple(-1));
	}
	
	// Normal Vector (the difference Vector)
	public Vector normal (Atom other)
	{
		return this.position.difference(other.position);
	}
	
	// Relative Normal Speed (towards = +)
	public double relativeNormalSpeed (Atom other)
	{
		return this.relativeV(other).component(other.normal(this));
	}
	
	// Relative Normal Velocity
	public Vector relativeNormalVelcocity (Atom other)
	{
		return this.relativeV(other).projection(other.normal(this));
	}
		
	
	// Normal Distance (past = -)
	public double normalDistance (Plane plane)
	{
		return (plane.getPosition().difference(position)).component(plane.getNormal());
	}
	
	// Relative Normal Speed (towards = +)
	public double relativeNormalSpeed (Plane plane)
	{
		return velocityF.component(plane.getNormal());
	}
	
	// **********Boolean**********
	
	// At rest
	public boolean isStationary ()
	{
		return velocityI.isZero() && velocityF.isZero();
	}
	
	// **********Changing values**********
	
	// Applying impulse
	public void applyImpulse (Vector impulse)
	{
		velocityF.add(impulse);
	}
	
	// Applying gravity force
	public void applyGravity ()
	{
		// Fg = mg
		react(g.multiple(mass));
	}
	
	// From velocity to force
	public Vector toForce (Vector velocity)
	{
		Vector acceleration = velocity.multiple(1 / Atom.toSecond(1));
		return acceleration.multiple(mass);
	}
	
	// From force to delta velocity
	public Vector toVelocity (Vector force)
	{
		// f = m * a
		Vector acceleration = force.multiple(1 / mass);
		// a = (v2 - v1) / t
		// (m/s/s) * (1 / ( / s)) = (m/s)
		return acceleration.multiple(Atom.toSecond(1));
	}
	
	// Stopping force
	public Vector stopForce ()
	{
		return this.toForce(velocityF.multiple(-1));
	}
	
	// Calculating input force
	public void react (Vector force)
	{
		velocityF.add(this.toVelocity(force));
	}
	
	// Integrating position (call after all forces are applied, once per frame)
	public void integrate ()
	{
		// d = 1/2 * (v1 + v2) * t
		// (m/s) * 1/(/s) = m
		position.displace(velocityI.sum(velocityF).multiple(Atom.toSecond(1)).multiple(0.5));
		velocityI = new Vector(velocityF);
	}
	
	// **********Graphics**********
	
	// Standard draw
	public void draw (Graphics g, double radius)
	{
		position.draw(g, radius);
	}
	
	// Standard drawFill
	public void drawFill (Graphics g, double radius)
	{
		position.drawFill(g, radius);
	}
	
	// Drawing velocity
	public void drawVelocity (Graphics g)
	{
		velocityF.draw(g, position);
	}
}
