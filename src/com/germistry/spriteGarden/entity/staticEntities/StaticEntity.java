package com.germistry.spriteGarden.entity.staticEntities;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public abstract class StaticEntity extends Entity {

	protected Sprite sprite;
	
	
	
	private int abs(double value) {
		if(value < 0) return -1;
		else return 1;
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	public Sprite getSprite( ) {
		return sprite;
	}
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	public int getSpriteWidth() {
		return sprite.getWidth();
	}
	public int getSpriteHeight() {
		return sprite.getHeight();
	}
}
