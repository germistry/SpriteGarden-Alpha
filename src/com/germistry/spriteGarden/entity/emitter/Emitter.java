package com.germistry.spriteGarden.entity.emitter;


import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.level.Level;

public class Emitter extends Entity {

	public enum Type {
		PARTICLE, MOB, ITEM;
	}
	private Type type;
	
	public Emitter(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.setType(type);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
