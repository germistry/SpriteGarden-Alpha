package com.germistry.spriteGarden.entity.mob;

import com.germistry.spriteGarden.graphics.AnimatedSprite;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.SpriteSheet;

public class LadyBeetle extends Mob {

	private int animate = 0;
	
	private AnimatedSprite ladyBeetle_down = new AnimatedSprite(SpriteSheet.ladyBeetle_down, 16, 16, 3);
	private AnimatedSprite ladyBeetle_up = new AnimatedSprite(SpriteSheet.ladyBeetle_up, 16, 16, 3);
	private AnimatedSprite ladyBeetle_left = new AnimatedSprite(SpriteSheet.ladyBeetle_left, 16, 16, 3);
	private AnimatedSprite ladyBeetle_right = new AnimatedSprite(SpriteSheet.ladyBeetle_right, 16, 16, 3);
	
	private AnimatedSprite animatedSprite = ladyBeetle_down;
	
	private int xa = 0, ya = 0;
	private int time = 0;
	
	public LadyBeetle(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		animatedSprite = ladyBeetle_down;
	}
	
	public void update() {
		time++;
		move();
		if(walking) animatedSprite.update();
		else animatedSprite.setFrame(0);
		
		if (animate < 5000) animate++;
		else animate = 0;
		if(ya < 0) {
			direction = Direction.UP;
			animatedSprite = ladyBeetle_up;
		}
		if(ya > 0) {
			direction = Direction.DOWN;
			animatedSprite = ladyBeetle_down;
		}
		if(xa < 0) {
			direction = Direction.LEFT;
			animatedSprite = ladyBeetle_left;
		}
		if(xa > 0) {
			direction = Direction.RIGHT;
			animatedSprite = ladyBeetle_right;
		}
		
	}

	public void render(Screen screen) {
		sprite = animatedSprite.getSprite();
		screen.renderMob((int)(x), (int)(y), this);
	}
	private void move() {
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;	
		}
		if (random.nextInt(50) == 0) {
			xa = 0;
			ya = 0;
		}
		if (xa != 0 || ya != 0)  {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

}
