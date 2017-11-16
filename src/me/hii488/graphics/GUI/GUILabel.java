package me.hii488.graphics.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class GUILabel extends GUIElement {
	
	public boolean fill = false;
	public int horizontalJustification = 0; // -1 = left, 0 = centred, 1 = right
	public int verticalJustificaton = 0;    // -1 = down, 0 = centred, 1 = up
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
		
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		
		String[] s = text.replace("\t", "    ").split("\n");
		int x,y;
		
		for(int i = 0; i < s.length; i++){
			if(horizontalJustification == 0)       x = position.getX() + (dimensions.getX() - metrics.stringWidth(s[i])) / 2;
			else if(horizontalJustification == -1) x = position.getX();
			else                                   x = position.getX() + dimensions.getX() - metrics.stringWidth(s[i]);
			
			if(verticalJustificaton == 0)       y = position.getY() + ((dimensions.getY() - metrics.getHeight()) / 2) + ((i-s.length/2) * metrics.getHeight()) + metrics.getAscent(); // TODO: Make this center better
			else if(verticalJustificaton == -1) y = position.getY() + dimensions.getY() + metrics.getHeight() * (i-s.length+1); 
			else                                y = position.getY() - metrics.getHeight() * (i-s.length+1); 
			g.drawString(s[i], x, y);
		}
		
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

	public int getHorizontalJustification() {
		return horizontalJustification;
	}

	public GUILabel setHorizontalJustification(int justification) {
		this.horizontalJustification = justification;
		return this;
	}

	public int getVerticalJustificaton() {
		return verticalJustificaton;
	}

	public GUILabel setVerticalJustificaton(int verticalJustificaton) {
		this.verticalJustificaton = verticalJustificaton;
		return this;
	}

}
