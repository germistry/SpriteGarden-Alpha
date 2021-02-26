package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassLSandCornerTLTile extends Tile {
	
	public MidGrGrassLSandCornerTLTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGreen Grass LightSand Corner TL";
		this.mapColour = Tile.col_midGrGrassLSandCornerTL;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
