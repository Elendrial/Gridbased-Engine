package me.hii488.interfaces;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface IInputUser extends MouseListener, KeyListener{
	
	// The default keyword is used to avoid bloating classes which do not need the methods.
	public default void keyPressed(KeyEvent arg0) {}
	public default void keyReleased(KeyEvent arg0) {}
	public default void keyTyped(KeyEvent arg0) {}
	public default void mouseClicked(MouseEvent arg0) {}
	public default void mouseEntered(MouseEvent arg0) {}
	public default void mouseExited(MouseEvent arg0) {}
	public default void mousePressed(MouseEvent arg0) {}
	public default void mouseReleased(MouseEvent arg0) {}
	
}
