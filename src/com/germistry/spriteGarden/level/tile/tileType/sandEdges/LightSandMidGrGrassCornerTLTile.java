package com.germistry.spriteGarden.level.tile.tileType.sandEdges;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class LightSandMidGrGrassCornerTLTile extends Tile{

	public LightSandMidGrGrassCornerTLTile(Sprite sprite) {
		super(sprite);
		this.name = "LightSand MidGreen Grass Corner TL";
		this.mapColour = Tile.col_lgtSandMidGrGrassCornerTL;
		this.tileId = 35;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
