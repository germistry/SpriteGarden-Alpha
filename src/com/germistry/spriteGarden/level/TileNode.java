package com.germistry.spriteGarden.level;

import com.germistry.spriteGarden.utils.Vector2i;

public class TileNode {

	public Vector2i tile;
	public TileNode parent;
	public double fCost, gCost, hCost;
	
	public TileNode(Vector2i tile, TileNode parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
	}
}
