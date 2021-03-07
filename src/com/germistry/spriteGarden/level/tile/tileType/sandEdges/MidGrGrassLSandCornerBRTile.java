package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class MidGrGrassLSandCornerBRTile extends Tile {

	public MidGrGrassLSandCornerBRTile(Sprite sprite) {
		super(sprite);
		this.name = "MidGreen Grass LightSand Corner BR";
		this.mapColour = Tile.col_midGrGrassLSandCornerBR;
		this.tileId = 38;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
