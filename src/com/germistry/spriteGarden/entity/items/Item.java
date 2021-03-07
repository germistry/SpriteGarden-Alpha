package com.germistry.spriteGarden.entity.items;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public abstract class Item extends Entity{

	protected Sprite sprite;
	protected int life; //length of life after item dropped
	protected int time = 0;
	protected int buyPrice, sellPrice;
	protected int x, y;
	
	public enum ItemType {
		MOB, CROP, TILE;
	}
	private ItemType itemType;
	
	public Item(int x, int y, ItemType itemType, int life) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.setItemType(itemType);
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
}
