package com.germistry.spriteGarden.entity.projectile;

import java.util.Random;

import com.germistry.spriteGarden.entity.emitter.ParticleEmitter;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public class SparklesProjectile extends Projectile {

	protected final Random random = new Random();
	
	//higher the value the slower it goes 
	public static final int FIRE_RATE = 15;
	
	public SparklesProjectile(double x, double y, double direction) {
		super(x, y, direction);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.sparklesProjectile;
		
		newX = speed * Math.cos(angle);
		newY = speed * Math.sin(angle);
	}

	public void update() {
		if(level.tileCollision((int)(x + newX), (int)(y + newY), 8, 4, 4)) {
			level.add(new ParticleEmitter((int)x, (int)y, 10, 100, level));
			remove();
		}
		move();
		
	}
	protected void move() {
		x += newX;
		y += newY;
		if(distance() > range) remove();
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int)x, (int)y - 2, this);
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin - y)));
		
		return dist;
	}
}
