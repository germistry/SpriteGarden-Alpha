package com.germistry.spriteGarden.level.tile.tileType.flowers;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class PurpleTulipMidGrGrassTile extends Tile {

	public PurpleTulipMidGrGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Purple Tulip MidGrGrass";
		this.mapColour = Tile.col_purpleTulipMidGrGrass;
		this.tileId = 28;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
