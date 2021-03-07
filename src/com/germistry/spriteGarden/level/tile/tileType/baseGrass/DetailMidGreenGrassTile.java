package com.germistry.spriteGarden.level.tile.tileType.baseGrass;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class DetailMidGreenGrassTile extends Tile {
	
	public DetailMidGreenGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Detail Mid Green Grass";
		this.mapColour = Tile.col_detailMidGreenGrass;
		this.tileId = 8;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
