package me.hii488.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import me.hii488.interfaces.IInputUser;

public class InputHandler implements MouseListener, KeyListener{

	public static InputHandler instance = new InputHandler();
	public static ArrayList<IInputUser> inputUsers = new ArrayList<IInputUser>();
	
	public static void addInputUser(IInputUser i){
		inputUsers.add(i);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		ContainerHandler.getLoadedContainer().keyPressed(arg0);
		for(IInputUser i : inputUsers) i.keyPressed(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		ContainerHandler.getLoadedContainer().keyReleased(arg0);
		for(IInputUser i : inputUsers) i.keyReleased(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		ContainerHandler.getLoadedContainer().keyTyped(arg0);
		for(IInputUser i : inputUsers) i.keyTyped(arg0);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mouseClicked(arg0);
		for(IInputUser i : inputUsers) i.mouseClicked(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mouseEntered(arg0);
		for(IInputUser i : inputUsers) i.mouseEntered(arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mouseExited(arg0);
		for(IInputUser i : inputUsers) i.mouseExited(arg0);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mousePressed(arg0);
		for(IInputUser i : inputUsers) i.mousePressed(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		ContainerHandler.getLoadedContainer().mouseReleased(arg0);
		for(IInputUser i : inputUsers) i.mouseReleased(arg0);
	}

}
