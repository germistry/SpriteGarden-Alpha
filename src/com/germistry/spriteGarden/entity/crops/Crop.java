package com.germistry.spriteGarden.entity.crops;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public abstract class Crop extends Entity {

	protected Sprite sprite;
	protected int growthState = 0;  //stage of growth 
	protected int resilience = 1;   //something like 1 for poor resilience, 10 for best resilience  
	
	public Crop(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
}
