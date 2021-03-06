package com.germistry.spriteGarden.level.tile.tileType.baseSand;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class PittedLightSandTile extends Tile{

	public PittedLightSandTile(Sprite sprite) {
		super(sprite);
		this.name = "Pitted Light Sand";
		this.mapColour = Tile.col_pittedLightSand;
		this.tileId = 14;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
