package com.germistry.spriteGarden.level.tile.tileType.baseGrass;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class PlainMidGreenGrassTile extends Tile {

	public PlainMidGreenGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Plain Mid Green Grass";
		this.mapColour = Tile.col_plainMidGreenGrass;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
}
