package com.germistry.spriteGarden.entity;

import java.util.Random;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.level.Level;


public abstract class Entity {

	//x & y are the location coords of entity
	protected double x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	
	public void update() {
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}

