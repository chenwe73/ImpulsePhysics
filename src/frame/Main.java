package frame;

import java.awt.Color;
import javax.swing.JFrame;

import object.Position;

@SuppressWarnings("serial")
//The only frame class that contains all components
public class Main extends JFrame
{
	public static final int FRAMEWIDTH = 1000;
	public static final int FRAMEHEIGHT = 800;
	
	public static Game game;
	
	// The constructor for the frame
	public Main()
	{
		super("Fall Of The Titans");
		Position.setOrigin(500, 600);
		Position.setMPP(0.02);
		
		this.setLayout(null);
		add(game = new Game());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setSize(FRAMEWIDTH+6,FRAMEHEIGHT+26);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}