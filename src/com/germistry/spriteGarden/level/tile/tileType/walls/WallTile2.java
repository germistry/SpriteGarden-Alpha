package com.germistry.spriteGarden.level.tile.tileType.walls;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class WallTile2 extends Tile {

	public WallTile2(Sprite sprite) {
		super(sprite);
		this.name = "Wall 2";
		this.mapColour = Tile.col_wallTile2;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
}
