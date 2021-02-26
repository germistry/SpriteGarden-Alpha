package com.germistry.spriteGarden.level.tile.tileType.baseGrass;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class WeedMidGreenGrassTile extends Tile {
	
	public WeedMidGreenGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Weed Mid Green Grass";
		this.mapColour = Tile.col_weedMidGreenGrass;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
