package com.germistry.spriteGarden.level.tile.tileType.dirtEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassDirtEdgeBaseTile extends Tile{

	public MidGrGrassDirtEdgeBaseTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGrGrass Dirt Edge Base";
		this.mapColour = Tile.col_midGrGrassDirtEdgeBase;
	}

	public void render(int x, int y, Screen screen) {		
		screen.renderTile(x << 4, y << 4, this);
	}
}
