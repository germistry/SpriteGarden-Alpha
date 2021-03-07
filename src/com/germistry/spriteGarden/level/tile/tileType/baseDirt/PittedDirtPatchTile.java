package com.germistry.spriteGarden.level.tile.tileType.baseDirt;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class PittedDirtPatchTile extends Tile {
	
	public PittedDirtPatchTile(Sprite sprite) {
		super(sprite);
		this.name = "Pitted Dirt";
		this.mapColour = Tile.col_pittedDirtPatch;
		this.tileId = 5;
	}

	public void render(int x, int y, Screen screen) {		
		screen.renderTile(x << 4, y << 4, this);
	}
}
