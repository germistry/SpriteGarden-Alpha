package com.germistry.spriteGarden.level.tile.tileType.baseGrass;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class DetailDarkGreenGrassTile extends Tile {

	
	public DetailDarkGreenGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Detail Dark Green Grass";
		this.mapColour = Tile.col_detailDarkGreenGrass;
		this.tileId = 7;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
