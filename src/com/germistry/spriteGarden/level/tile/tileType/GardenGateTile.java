package com.germistry.spriteGarden.level.tile.tileType;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class GardenGateTile extends Tile{

	//TODO will get rid of these tiles once collision detection between mobs is implemented
	public GardenGateTile(Sprite sprite) {
		super(sprite);
		this.name = "Garden Gate";
		this.mapColour = Tile.col_gardenGateTile;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
}
