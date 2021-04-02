package com.germistry.spriteGarden.level.tile.tileType.rocks;


import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;
import com.germistry.spriteGarden.level.tile.Tile;

public class RockMidGrGrassTile extends Tile {

	public RockMidGrGrassTile(Sprite sprite) {
		super(sprite);
		this.name = "Rock MidGrGrass";
		this.mapColour = Tile.col_rockMidGrGrass;
		this.tileId = 32;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
	public boolean breakable() {
		return true;
	}
	
	public Tile getReplacementTile() {
		return Tile.plainMidGreenGrass;
	}
}
