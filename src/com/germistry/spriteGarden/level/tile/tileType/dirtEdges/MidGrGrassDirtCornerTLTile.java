package com.germistry.spriteGarden.level.tile.tileType.dirtEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassDirtCornerTLTile extends Tile {
	
	public MidGrGrassDirtCornerTLTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass Dirt Corner TL Tile";
		this.mapColour = Tile.col_midGrGrassDirtCornerTL;
		this.tileId = 18;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
