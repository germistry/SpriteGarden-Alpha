package com.germistry.spriteGarden.entity.staticEntities;

import com.germistry.spriteGarden.graphics.AnimatedSprite;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.SpriteSheet;

public class GardenGate extends StaticEntity {

	private boolean open = false; 
	private AnimatedSprite gardenGate_shut = new AnimatedSprite(SpriteSheet.gardenGate_shut, 64, 64, 3);
	private AnimatedSprite gardenGate_open = new AnimatedSprite(SpriteSheet.gardenGate_open, 64, 64, 3);
		
	private AnimatedSprite animatedSprite = gardenGate_shut;
	
	public GardenGate(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		animatedSprite = gardenGate_shut;
	}	
	public void update() {
		//something to trigger the change of open shut state - perhaps once player has x number of coins
		//clicks on door it updates to open
		
		animatedSprite.update();
		animatedSprite.setFrame(0);
		if(open) animatedSprite = gardenGate_open;
		else animatedSprite = gardenGate_shut;
		
	}
	
	public void render(Screen screen) {
		sprite = animatedSprite.getSprite();
		screen.renderStaticEntity((int)x, (int)y, this);
	}
}
