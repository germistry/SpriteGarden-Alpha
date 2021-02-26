package com.germistry.spriteGarden.level.tile.tileType.flowers;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class PinkTulipMidGrGrassTile extends Tile {

	public PinkTulipMidGrGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Pink Tulip MidGrGrass";
		this.mapColour = Tile.col_pinkTulipMidGrGrass;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
