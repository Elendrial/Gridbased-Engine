package me.hii488.graphics.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class GUILabel extends GUIElement {
	
	public boolean fill = false;
	public Color outlineColor, textColor = Color.BLACK;
	public Font f;
	
	@Override
	public void onClick(MouseEvent e) {}

	@Override
	public void render(Graphics g) {
		if(hidden) return;
		
		if(outlineColor != null){
			g.setColor(outlineColor);
			if(fill) g.fillRect(position.getX(), position.getY(), dimensions.getX(), dimensions.getY());
			else g.drawRect(position.getX(), position.getY(), dimensions.getX(), dimensions.getY());
		}
		
		g.setColor(textColor);
		Font f2 = g.getFont();
		if(f != null)g.setFont(f);
		
		FontMetrics metrics = g.getFontMetrics(g.getFont()); // Grabbed from stack overflow
	    // Determine the X coordinate for the text
	    int x = position.getX() + (dimensions.getX() - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = position.getY() + ((dimensions.getY() - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Draw the String
	    g.drawString(text, x, y);
	    g.setFont(f2);
	}

	public boolean isFill() {
		return fill;
	}

	public GUILabel setFill(boolean fill) {
		this.fill = fill;
		return this;
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public GUILabel setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
		return this;
	}

	public Color getTextColor() {
		return textColor;
	}

	public GUILabel setTextColor(Color textColor) {
		this.textColor = textColor;
		return this;
	}

	public Font getFont() {
		return f;
	}

	public GUILabel setFont(Font f) {
		this.f = f;
		return this;
	}
	
	

}
