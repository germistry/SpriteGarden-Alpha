package com.germistry.spriteGarden.entity.items;

import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.Sprite;

public class ItemRock extends Item {
	
	private int ya = 0;
	
	public ItemRock(int x, int y, int life) {
		super(x, y, life);
		sprite = Sprite.rockItem;
		this.life = life + (random.nextInt(20) - 10);
		
	}

	public void update() {
		time++;
		if (time >= 5000 - 1) time = 0;
		if (time > life) remove();
		if(time < 10) {
			if(ya < 0) {
				direction = Direction.UP;
				y++;
			}
			if(ya > 0) {
				direction = Direction.DOWN;
				y--;
			}
			move();
		}
	}
	
	public void move() {
		if (time < 5) {
			ya = 1;	
		} 
		else {
			ya = -1;
		}
		move(0, ya);
	}
	
	
	public void render(Screen screen) {
		screen.renderItem((int)x, (int)y, this);
	}
}
