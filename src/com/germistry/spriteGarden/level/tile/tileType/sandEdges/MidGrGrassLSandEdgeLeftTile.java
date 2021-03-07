package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassLSandEdgeLeftTile extends Tile {

	public MidGrGrassLSandEdgeLeftTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass LSand Edge Left";
		this.mapColour = Tile.col_midGrGrassLSandEdgeLeft;
		this.tileId = 42;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
