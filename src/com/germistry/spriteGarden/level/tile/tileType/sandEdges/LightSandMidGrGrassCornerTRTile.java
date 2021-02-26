package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class LightSandMidGrGrassCornerTRTile extends Tile{

	public LightSandMidGrGrassCornerTRTile(Sprite sprite) {
		super(sprite);
		this.name = "LightSand MidGreen Grass Corner TR";
		this.mapColour = Tile.col_lgtSandMidGrGrassCornerTR;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
