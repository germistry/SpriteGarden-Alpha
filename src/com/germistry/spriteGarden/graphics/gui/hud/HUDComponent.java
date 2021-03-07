package com.germistry.spriteGarden.graphics.gui.hud;

import java.awt.Color;
import java.awt.Graphics;

import com.germistry.spriteGarden.utils.Vector2i;

public class HUDComponent {

	public Vector2i position, size; //total size of component
	protected Vector2i offset;
	public Color colour;
	public boolean active = true;
	protected HUDPanel panel;
	
	public HUDComponent(Vector2i position) {
		this.position = position;
		offset = new Vector2i();
	}
	public HUDComponent(Vector2i position, Vector2i size) {
		this.position = position;
		this.size = size;
		offset = new Vector2i();
	}
	
	void setOffset(Vector2i offset) {
		this.offset = offset;
	}
	//getting the absolute position of the component for actions etc. 
	public Vector2i getAbsolutePosition() {
		return new Vector2i(position).add(offset);
	}
	
	public HUDComponent setColour(int colour) {
		this.colour = new Color(colour);
		return this;
	}
	
	public void init(HUDPanel panel) {
		this.panel = panel;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics graphics) {
		
	}
	
}
