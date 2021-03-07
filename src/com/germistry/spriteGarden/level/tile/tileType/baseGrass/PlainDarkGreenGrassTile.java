package com.germistry.spriteGarden.level.tile.tileType.baseGrass;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class PlainDarkGreenGrassTile extends Tile {
	
	public PlainDarkGreenGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Plain Dark Green Grass";
		this.mapColour = Tile.col_plainDarkGreenGrass;
		this.tileId = 9;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
