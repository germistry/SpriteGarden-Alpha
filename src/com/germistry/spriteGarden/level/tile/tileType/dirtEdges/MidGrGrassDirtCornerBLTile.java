package com.germistry.spriteGarden.level.tile.tileType.dirtEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassDirtCornerBLTile extends Tile {

	public MidGrGrassDirtCornerBLTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass Dirt Corner BL Tile";
		this.mapColour = Tile.col_midGrGrassDirtCornerBL;
		this.tileId = 16;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
