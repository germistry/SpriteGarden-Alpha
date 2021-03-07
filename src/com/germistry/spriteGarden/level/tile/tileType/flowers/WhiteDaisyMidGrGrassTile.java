package com.germistry.spriteGarden.level.tile.tileType.flowers;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class WhiteDaisyMidGrGrassTile extends Tile {

	public WhiteDaisyMidGrGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "White Daisy MidGrGrass";
		this.mapColour = Tile.col_whiteDaisyMidGrGrass;
		this.tileId = 29;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
