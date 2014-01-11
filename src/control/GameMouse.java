package control;

import frame.Main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

import object.Position;
import thread.Engine;

// The mouse listener class for game component
public class GameMouse extends MouseAdapter
{
	public Position mouse = new Position(10000,10000); 
	// be carefull of where you initialize the position before mouse enter window
	
	public void mouseMoved (MouseEvent e)
	{
		mouse.teleportCoord(e.getX(), e.getY());
	}
	
	public void mouseDragged (MouseEvent e)
	{
		mouse.teleportCoord(e.getX(), e.getY());
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	public void mouseWheelMoved (MouseWheelEvent e)
	{
		double zoom = 0.9;
		if (e.getWheelRotation() > 0)
			Position.zoom(1/zoom);
		if (e.getWheelRotation() < 0)
			Position.zoom(zoom);
		Main.game.repaint();
	}
}