package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassLSandEdgeBaseTile extends Tile{

	public MidGrGrassLSandEdgeBaseTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass LSand Edge Base";
		this.mapColour = Tile.col_midGrGrassLSandEdgeBase;
		this.tileId = 41;
	}

	public void render(int x, int y, Screen screen) {		
		screen.renderTile(x << 4, y << 4, this);
	}
}
