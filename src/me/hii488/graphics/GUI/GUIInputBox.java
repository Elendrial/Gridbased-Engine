package me.hii488.graphics.GUI;

import java.awt.event.KeyEvent;

public class GUIInputBox extends GUILabel{
	public void onKeyTyped(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && this.text.length() > 0) text = text.substring(0, text.length()-1);
		if(Character.isWhitespace(e.getKeyChar()) || Character.isAlphabetic(e.getKeyChar()) || Character.isDigit(e.getKeyChar())) text += e.getKeyChar();
	}
}
