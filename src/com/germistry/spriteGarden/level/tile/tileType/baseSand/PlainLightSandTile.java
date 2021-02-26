package com.germistry.spriteGarden.level.tile.tileType.baseSand;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class PlainLightSandTile extends Tile{

	public PlainLightSandTile(Sprite sprite) {
		super(sprite);
		this.name = "Plain Light Sand";
		this.mapColour = Tile.col_plainLightSand;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}
