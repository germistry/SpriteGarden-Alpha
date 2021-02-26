package com.germistry.spriteGarden.entity.projectile;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.graphics.Sprite;

public abstract class Projectile extends Entity {

	
	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double newX, newY;
	protected double distance;
	protected double speed, damage, range;
	
	
	public Projectile(double x, double y, double direction) {
		xOrigin = x;
		yOrigin = y;
		angle = direction;
		this.x = x;
		this.y = y;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	protected void move() {
		
	}
	
}
