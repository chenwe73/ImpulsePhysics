package object;

import java.awt.Graphics;

// Atoms with size

public class Sphere extends Atom
{
	private double radius; // radius of circle (pixel)
	private double cor; // coefficient of restitution
	//private double invMass; // 1 / mass (1/kg)
	//private static double cof = 0; // coefficient of kinetic friction (for top view)
	//private static double density = 0; // density of surrounding fluid (kg/m^3)
	
	// **********Constructor**********
	
	// Constructor
	public Sphere ()
	{
		super();
	}
	
	// Standard Constructor
	public Sphere (Position position, double mass, double radius)
	{
		super(position, mass);
		this.radius = radius;
		//this.invMass = 1 / mass;
		this.cor = 1;
	}
	
	// Full Constructor
	public Sphere (Position position, double mass, Vector velocity, double radius, double cor)
	{
		super(position, mass, velocity);
		this.radius = radius;
		//this.invMass = 1 / mass;
		this.cor = cor;
	}
	
	// **********Mutator**********
	/*
	// Mutating cof
	public static void setCof (double cof)
	{
		Sphere.cof = cof;
	}
	
	// Mutating density
	public static void setDensity (double density)
	{
		Sphere.density = density;
	}
	*/
	// **********Force Interaction**********
	
	// Input repulsive field force
	// f is the force applied at 1 pixel distance
	public void applyRepulsion (Position source, double f, double radiusF)
	{
		// Fg = G * m * M / distance^2
		Vector force= new Vector (0, 0);
		double safeR = radiusF / 4;
		if (radiusF == 0)
			;
		else if (this.distance(source) <= safeR)
			force = this.difference(source).multiple(-1 * f / Math.pow(safeR, 2));
		else if (this.distance(source) - radius <= radiusF)
			force = this.difference(source).multiple
					(-1 * f / Math.pow(this.distance(source), 2));
		else
			;
		react(force);
	}
	
	//Input friction force
	public void applyFriction (double friction)
	{
		Vector force = this.direction().multiple(-friction);
		if (this.isStationary())
			force = new Vector(0, 0);
		else if (this.toVelocity(force).magnitude() >= this.netVelocity())
			force = this.stopForce();
		else
			;
		this.react(force);
	}
	
	// **********Sphere Interaction**********
	
	// Is Sphere going to collide
	public boolean isCollide (Sphere other)
	{
		final boolean isPenetrate = this.distance(other) < this.radius + other.radius;
		final boolean isRNV = this.relativeNormalSpeed(other) <= 0;
		return isPenetrate && isRNV;
	}
	
	// Collision
	public void collide (Sphere other)
	{
		if (this.isCollide(other))
		{
			// I = (1+e)*N*(Vr.N) / (1/Ma + 1/Mb)
			final double THISINVMASS = 1 / this.getMass();
			final double OTHERINVMASS = 1 / other.getMass();
			final Vector I = this.relativeNormalVelcocity(other).multiple
					(((1 + (this.cor + other.cor) / 2))/ (THISINVMASS + OTHERINVMASS));
			//Va += I * 1/Ma 
			this.applyImpulse(I.multiple(-THISINVMASS));
			//Vb += I * 1/Mb
			other.applyImpulse(I.multiple(OTHERINVMASS));
		}
		else
			;
	}
	
	// **********Plane Interaction**********
	
	// Penetration Detection
	public boolean isReflect (Plane plane)
	{
		// normal distance = x * p.x + y * p.y + p.magnitude
		final boolean isND = this.normalDistance(plane) - radius<= 0;
		// relative normal velocity = V[i] dot-product N[i]
		final boolean isRNV = this.relativeNormalSpeed(plane) < 0;
		//System.out.println(this.rnv(plane));
		return isND && isRNV;
	}
	
	// Reflect Atom off plane
	public void reflect (Plane plane)
	{
		if (isReflect(plane))
		{
			// R = V-(1+e)*N*(V•N)
			final Vector IMPULSE = plane.getNormal().unit().multiple(-this.relativeNormalSpeed(plane) * (1 + cor));
			this.applyImpulse(IMPULSE);
		}
	}
	
	// **********Graphics**********
	
	// Standard draw
	public void draw (Graphics g)
	{
		this.draw(g, radius);
	}
	
	// Standard draw
	public void drawFill (Graphics g)
	{
		this.drawFill(g, radius);
	}
}
