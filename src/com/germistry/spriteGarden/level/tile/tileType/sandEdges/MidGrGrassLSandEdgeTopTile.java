package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassLSandEdgeTopTile extends Tile {

	public MidGrGrassLSandEdgeTopTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass LSand Edge Right";
		this.mapColour = Tile.col_midGrGrassLSandEdgeTop;
	}

	public void render(int x, int y, Screen screen) {		
		screen.renderTile(x << 4, y << 4, this);
	}
}
