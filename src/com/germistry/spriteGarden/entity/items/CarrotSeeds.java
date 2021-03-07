package com.germistry.spriteGarden.entity.items;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public class CarrotSeeds extends Item {

	public CarrotSeeds(int x, int y, ItemType itemType, int life) {
		super(x, y, itemType, life); 
		sprite = Sprite.carrotSeedItem;
	}

	public void update() {
		
	}
	
	public void render(Screen screen) {
		screen.renderItem((int)x, (int)y, this);
	}
}
