package com.germistry.spriteGarden.graphics.gui.hud;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class HUDManager {

	private List<HUDPanel> panels = new ArrayList<HUDPanel>();
	
	public HUDManager() {
	}

	public void addPanel(HUDPanel panel) {
		panels.add(panel);
	}
	
	
	public void update() {
		for (HUDPanel panel : panels) {
			panel.update();
		}
	}

	public void render(Graphics graphics) {
		for (HUDPanel panel : panels) {
			panel.render(graphics);
		}
	}

}
