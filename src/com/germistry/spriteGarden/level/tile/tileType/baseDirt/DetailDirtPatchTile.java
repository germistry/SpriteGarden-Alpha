package com.germistry.spriteGarden.level.tile.tileType.baseDirt;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class DetailDirtPatchTile extends Tile {
	
	public DetailDirtPatchTile(Sprite sprite) {
		super(sprite);
		this.name = "Detail Dirt";
		this.mapColour = Tile.col_detailDirtPatch;
	}

	public void render(int x, int y, Screen screen) {		
		screen.renderTile(x << 4, y << 4, this);
	}
}
