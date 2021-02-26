package com.germistry.spriteGarden.level.tile.tileType;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
		this.name = "Void";
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
}
