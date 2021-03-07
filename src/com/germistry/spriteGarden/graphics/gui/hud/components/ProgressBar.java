package com.germistry.spriteGarden.graphics.gui.hud.components;

import java.awt.Color;
import java.awt.Graphics;

import org.w3c.dom.ranges.RangeException;

import com.germistry.spriteGarden.graphics.gui.hud.HUDComponent;
import com.germistry.spriteGarden.utils.Vector2i;

public class ProgressBar extends HUDComponent {

	private double progress; // 0.0 - 1.0
	private Color foregroundColour;
	
	public ProgressBar(Vector2i position, Vector2i size) {
		super(position);
		this.size = size;
		foregroundColour = new Color(0xffff00ff);
	}

	public double getProgress() {
		return progress;
	}
	
	public void setProgress(double progress) {
		if(progress < 0.0 || progress > 1.0)
			throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Progress must be between 0.0 and 1.0.");
		this.progress = progress;
	}
	public void setForegroundColour(int colour) {
		this.foregroundColour = new Color(colour);
	}
	
	public void update() {
	}

	public void render(Graphics graphics) {
		graphics.setColor(colour);
		graphics.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);
		graphics.setColor(foregroundColour);
		graphics.fillRect(position.x + offset.x, position.y + offset.y, (int)(size.x * progress), size.y);
	}
}
