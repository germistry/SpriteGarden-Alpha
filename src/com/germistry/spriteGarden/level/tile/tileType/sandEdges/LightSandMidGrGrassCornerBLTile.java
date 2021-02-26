package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class LightSandMidGrGrassCornerBLTile extends Tile{

	public LightSandMidGrGrassCornerBLTile(Sprite sprite) {
		super(sprite);
		this.name = "LightSand MidGreen Grass Corner BL";
		this.mapColour = Tile.col_lgtSandMidGrGrassCornerBL;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
