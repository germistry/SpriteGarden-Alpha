package com.germistry.spriteGarden.level.tile.tileType.dirtEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassDirtEdgeLeftTile extends Tile {

	public MidGrGrassDirtEdgeLeftTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass Dirt Edge Left";
		this.mapColour = Tile.col_midGrGrassDirtEdgeLeft;
		this.tileId = 21;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
