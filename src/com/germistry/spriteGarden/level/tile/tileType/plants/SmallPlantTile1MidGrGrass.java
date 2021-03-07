package com.germistry.spriteGarden.level.tile.tileType.plants; 

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class SmallPlantTile1MidGrGrass extends Tile {

	public SmallPlantTile1MidGrGrass(Sprite sprite) {
		super(sprite);
		this.name = "Small Plant 1 MidGrGrass";
		this.mapColour = Tile.col_smallPlant1MidGrGrass;
		this.tileId = 30;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
