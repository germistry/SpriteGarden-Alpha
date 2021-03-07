package com.germistry.spriteGarden.level.tile.tileType.walls;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class WallTile4 extends Tile{

	public WallTile4(Sprite sprite) {
		super(sprite);
		this.name = "Wall 4";
		this.mapColour = Tile.col_wallTile4;
		this.tileId = 48;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
}
