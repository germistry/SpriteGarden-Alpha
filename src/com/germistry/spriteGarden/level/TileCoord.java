package com.germistry.spriteGarden.level;

public class TileCoord {

	private int x, y;
	private final int TILE_SIZE = 16;
	
	public TileCoord(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int[] getTileCoords() {
		int[] result = new int[2];
		result[0] = x;
		result[1] = y;
		return result;
	}
}
