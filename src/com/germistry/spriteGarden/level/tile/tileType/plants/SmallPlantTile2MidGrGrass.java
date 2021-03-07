package com.germistry.spriteGarden.level.tile.tileType.plants;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class SmallPlantTile2MidGrGrass extends Tile {

	public SmallPlantTile2MidGrGrass(Sprite sprite) {
		super(sprite);
		this.name = "Small Plant 2 MidGrGrass";
		this.mapColour = Tile.col_smallPlant2MidGrGrass;
		this.tileId = 31;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
