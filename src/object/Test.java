package object;

public class Test 
{
	public static void main (String[] args)
	{
		Position p1 = new Position(10, 0);
		Position p2 = new Position(0, 10);
		System.out.println(p1.distance(p2));
		Atom atom = new Atom(p2, 1);
		double fps = 4;
		Plane plane1 = new Plane(new Vector (100, 100), new Position (0, 0));
		System.out.println(atom.normalDistance(plane1));
		
		/*Atom.setFPS(fps);
		Position.setMPP(2);
		System.out.println(atom);
		for (int i = 0; i < fps; i++)
		{
			atom.applyGravity();
			System.out.println(atom);
			atom.integrate();
		}
		System.out.println(atom);*/
	}
}
