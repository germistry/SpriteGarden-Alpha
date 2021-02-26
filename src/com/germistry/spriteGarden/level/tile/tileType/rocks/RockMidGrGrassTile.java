package com.germistry.spriteGarden.level.tile.tileType.rocks;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class RockMidGrGrassTile extends Tile {

	public RockMidGrGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Rock MidGrGrass";
		this.mapColour = Tile.col_rockMidGrGrass;
		
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
}
