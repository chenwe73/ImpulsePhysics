package control;

import frame.Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import object.Position;
import object.Vector;

//The key listener class for game component
public class GameKey extends KeyAdapter
{
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		int scroll = 10;
		switch (keyCode) 
		{
		case KeyEvent.VK_UP:
			Position.moveOrigin(new Vector (0, scroll));
			break;
		case KeyEvent.VK_DOWN:
			Position.moveOrigin(new Vector (0, -scroll));
			break;
		case KeyEvent.VK_LEFT:
			Position.moveOrigin(new Vector (scroll, 0));
			break;
		case KeyEvent.VK_RIGHT:
			Position.moveOrigin(new Vector (-scroll, 0));
			break;
		}
		Main.game.repaint();
	}
}