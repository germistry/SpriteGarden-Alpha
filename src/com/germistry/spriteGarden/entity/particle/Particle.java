package com.germistry.spriteGarden.entity.particle;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public class Particle extends Entity{

	private Sprite sprite;
	private int life;
	private int time = 0;
	protected double xActual, yActual, zActual;
	protected double xAmount, yAmount, zAmount;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xActual = x;
		this.yActual = y;
		this.zActual = random.nextFloat() + 2.0;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.sparklesParticle;
		
		this.xAmount = random.nextGaussian();
		this.yAmount = random.nextGaussian();
	}

	public void update() {
		time++;
		if (time >= 5000 - 1) time = 0;
		if (time > life) remove();
		//Animating the z direction
		zAmount -= 0.1;
		if(zActual < 0) {
			zActual = 0;
			zAmount *= -0.5;
			xAmount *= 0.8;
			yAmount *= 0.5;
		}
		move((xActual + xAmount), (yActual + yAmount) + (zActual + zAmount));
	}
	
	private void move(double x, double y) {
		if(collision(x, y)) {
			this.xAmount *= -0.5;
			this.yAmount *= -0.5;
			this.zAmount *= -0.5;
		}
		this.xActual += xAmount;
		this.yActual += yAmount;
		this.zActual += zAmount;
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for(int corner = 0; corner < 4; corner++) {
			double xt = (x - corner % 2 * 16) / 16;
			double yt = (y - corner / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(corner % 2 == 0) ix =(int) Math.floor(xt);
			if(corner / 2 == 0) iy =(int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		
		return solid;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) xActual - 1, (int) yActual - (int) zActual - 1, sprite, true);
	}
}
