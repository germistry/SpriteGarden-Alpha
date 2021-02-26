package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassLSandCornerBLTile extends Tile {

	public MidGrGrassLSandCornerBLTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGreen Grass LightSand Corner BL";
		this.mapColour = Tile.col_midGrGrassLSandCornerBL;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
