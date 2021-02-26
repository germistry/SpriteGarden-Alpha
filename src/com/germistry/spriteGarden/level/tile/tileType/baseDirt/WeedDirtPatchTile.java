package com.germistry.spriteGarden.level.tile.tileType.baseDirt;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class WeedDirtPatchTile extends Tile {

	public WeedDirtPatchTile(Sprite sprite) {
		super(sprite);
		this.name = "Weed Dirt";
		this.mapColour = Tile.col_weedDirtPatch;
	}

	public void render(int x, int y, Screen screen) {		
		screen.renderTile(x << 4, y << 4, this);
	}
}
