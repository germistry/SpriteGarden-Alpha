package com.germistry.spriteGarden.level.tile.tileType.baseDirt;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class DirtPatchTile extends Tile {

	public DirtPatchTile(Sprite sprite) {
		super(sprite);
		this.name = "Dirt";
		this.mapColour = Tile.col_dirtPatch;
		this.tileId = 3;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
