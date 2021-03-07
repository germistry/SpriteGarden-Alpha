package com.germistry.spriteGarden.graphics.gui.hud.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.germistry.spriteGarden.graphics.gui.hud.HUDComponent;
import com.germistry.spriteGarden.utils.Vector2i;

public class Label extends HUDComponent {

	public String text;
	public Font font;
	public boolean shadow = false;
	public int shadowOffset = 1;
	
	public Label(Vector2i position, String text) {
		super(position);
		font = new Font("Courier New", Font.BOLD, 32);
		this.text = text;
		colour = new Color(0xff27511C);
	}

	public void render(Graphics graphics) {
		if(shadow) {
			graphics.setColor(Color.GREEN);
			graphics.setFont(new Font(font.getName(), font.getStyle(), font.getSize()));
			graphics.drawString(text, position.x + offset.x + shadowOffset, position.y + offset.y + shadowOffset);
		}
		graphics.setFont(font);
		graphics.setColor(colour);
		graphics.drawString(text, position.x + offset.x, position.y + offset.y);
	}
	
	public Label setFont(Font font) {
		this.font = font;
		return this;
	}
	public Label setText(String text) {
		this.text = text;
		return this;
	}
	
}
