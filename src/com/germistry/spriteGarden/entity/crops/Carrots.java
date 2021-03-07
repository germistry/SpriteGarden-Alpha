package com.germistry.spriteGarden.entity.crops;

import com.germistry.spriteGarden.graphics.AnimatedSprite;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.SpriteSheet;

public class Carrots extends Crop {

	//TODO Make some sprites - 1st sprite is the freshly planted one that can be used for multiple crops
	private AnimatedSprite gardenGate_shut = new AnimatedSprite(SpriteSheet.gardenGate_shut, 16, 16, 5);
			
	private AnimatedSprite animatedSprite = gardenGate_shut;
	
	public Carrots(int x, int y) {
		super(x, y);
		animatedSprite = gardenGate_shut;
		growthState = 0;
		resilience = 1;
		
	}
	
	public void update() {
		animatedSprite.update();
		animatedSprite.setFrame(0);
	}
	
	public void render(Screen screen) {
		sprite = animatedSprite.getSprite(); 
		screen.renderCrop((int)x, (int)y, this);
	}
}
