package com.germistry.spriteGarden.level.tile.tileType.dirtEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassFertDirtEdgeRightTile extends Tile {
	
	public MidGrGrassFertDirtEdgeRightTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass Fert Dirt Edge Right";
		this.mapColour = Tile.col_midGrGrassFertDirtEdgeRight;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
