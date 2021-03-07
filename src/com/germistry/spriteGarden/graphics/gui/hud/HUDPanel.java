package com.germistry.spriteGarden.graphics.gui.hud;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.germistry.spriteGarden.utils.Vector2i;

public class HUDPanel {
	
	private List<HUDComponent> comps = new ArrayList<HUDComponent>();
	private Vector2i position, size;
	private Color colour;
	
	public HUDPanel(Vector2i position, Vector2i size) {
		this.position = position;
		this.size = size;
		colour = new Color(0xff78ff57);
	}
	
	public void addComponent(HUDComponent comp) {
		comp.init(this);
		comps.add(comp);
	}
	
	public void update() {
		for (HUDComponent comp : comps) {
			comp.setOffset(position);
			comp.update();
		}
	}
	
	public void render(Graphics graphics) {
		graphics.setColor(colour);
		graphics.fillRect(position.x, position.y, size.x, size.y);
		for (HUDComponent comp : comps) {
			comp.render(graphics);
		}
	}
	public HUDPanel setColour(int colour) {
		this.colour = new Color(colour);
		return this;
	}
}
