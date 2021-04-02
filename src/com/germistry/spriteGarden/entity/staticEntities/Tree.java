package com.germistry.spriteGarden.entity.staticEntities;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public class Tree extends StaticEntity {

	public Tree(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.tree;
	}	
	
	
	@Override
	public void update() {

	}

	@Override
	public void render(Screen screen) {
		screen.renderStaticEntity((int)x, (int)y, this);
	}

}
