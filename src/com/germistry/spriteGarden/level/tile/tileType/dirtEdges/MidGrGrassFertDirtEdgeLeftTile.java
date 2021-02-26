package com.germistry.spriteGarden.level.tile.tileType.dirtEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassFertDirtEdgeLeftTile extends Tile{
	
	public MidGrGrassFertDirtEdgeLeftTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass Fert Dirt Edge Left";
		this.mapColour = Tile.col_midGrGrassFertDirtEdgeLeft;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
