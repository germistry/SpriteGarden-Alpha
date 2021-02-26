package com.germistry.spriteGarden.entity.mob;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.entity.projectile.ArrowProjectile;
import com.germistry.spriteGarden.entity.projectile.Projectile;
//import com.germistry.spriteGarden.entity.projectile.SparklesProjectile;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected boolean moving = false;
	protected boolean walking = false;
		
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	protected Direction direction;
	
	//move & collision method for 16 x 16 mobs only. create separate move / collision methods for larger/smaller 
	//entities in their own class to tweak the collision detection.
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
		
		while (xpixel != 0) {
			if (Math.abs(xpixel) > 1) {
				if (!collision(abs(xpixel), ypixel)) {
					this.x += abs(xpixel);
				}
				xpixel -= abs(xpixel);
			} else {
				if (!collision(abs(xpixel), ypixel)) {
					this.x += xpixel;
				}
				xpixel = 0;
			}
		}
		
		while (ypixel != 0) {
			if (Math.abs(ypixel) > 1) {
				if (!collision(xpixel, abs(ypixel))) {
					this.y += abs(ypixel);
				}
				ypixel -= abs(ypixel);
			} else {
				if (!collision(xpixel, abs(ypixel))) {
					this.y += ypixel;
				}
				ypixel = 0;
			}
		}
	}
	private int abs(double value) {
		if(value < 0) return -1;
		else return 1;
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	protected void fireProjectile(double xplayer, double yplayer, double direction) {
		//direction *= 180 / Math.PI;
		Projectile p = new ArrowProjectile(x, y, direction);
		level.add(p);
	}
	
	private boolean collision(double xpixel, double ypixel) {
		boolean solid = false;
		for(int corner = 0; corner < 4; corner++) {
			double xt = ((x + xpixel) - corner % 2 * 8 - 4)/16;
			double yt = ((y + ypixel) - corner / 2 * 8)/16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(corner % 2 == 0) ix =(int) Math.floor(xt);
			if(corner / 2 == 0) iy =(int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
		
	}
	public Sprite getSprite( ) {
		return sprite;
	}
	
}
