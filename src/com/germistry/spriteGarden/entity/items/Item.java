package com.germistry.spriteGarden.entity.items;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;


public abstract class Item extends Entity {

	protected Sprite sprite;
	protected int life; //length of life after item dropped
	protected int time = 0;
	protected int buyPrice, sellPrice;
	protected int x, y;
	
	protected boolean moving = false;
			
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	protected Direction direction;
	
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
	
	public Item(int x, int y, int life) {
		this.x = x << 4;
		this.y = y << 4;
		this.life = life;
	}
	
	public Item(Sprite sprite) {
		this.sprite = sprite;
	}
		
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void move(double xpixel, double ypixel) {
		if (xpixel != 0 && ypixel != 0) {
			move(xpixel, 0);
			move(0, ypixel);
			return;
		}
		
		if (xpixel > 0 ) direction = Direction.RIGHT; 
		if (xpixel < 0 ) direction = Direction.LEFT; 
		if (ypixel > 0 ) direction = Direction.DOWN;
		if (ypixel < 0 ) direction = Direction.UP;
		
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
