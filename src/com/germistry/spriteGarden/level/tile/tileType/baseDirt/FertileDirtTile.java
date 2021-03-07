package com.germistry.spriteGarden.level.tile.tileType.baseDirt;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class FertileDirtTile extends Tile {

	public FertileDirtTile(Sprite sprite) {
		super(sprite);
		this.name = "Fertile Soil";
		this.mapColour = Tile.col_fertileDirt;
		this.tileId = 4;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean growable() {
		return true;
	}
}
