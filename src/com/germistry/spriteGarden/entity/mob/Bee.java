package com.germistry.spriteGarden.entity.mob;

import java.util.List;

import com.germistry.spriteGarden.graphics.AnimatedSprite;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.SpriteSheet;
import com.germistry.spriteGarden.level.TileNode;
//import com.germistry.spriteGarden.utils.Debug;
import com.germistry.spriteGarden.utils.Vector2i;

public class Bee extends Mob {

	private int animate = 0;
	
	private AnimatedSprite bee_down = new AnimatedSprite(SpriteSheet.bee_down, 16, 16, 3);
	private AnimatedSprite bee_up = new AnimatedSprite(SpriteSheet.bee_up, 16, 16, 3);
	private AnimatedSprite bee_left = new AnimatedSprite(SpriteSheet.bee_left, 16, 16, 3);
	private AnimatedSprite bee_right = new AnimatedSprite(SpriteSheet.bee_right, 16, 16, 3);
	
	private AnimatedSprite animatedSprite = bee_down;
	
	private double xa = 0, ya = 0;
	private double speed = 1;
	private int time = 0;
	
	private List<TileNode> path = null;
	
	public Bee(int x, int y) {
		this.x = x << 4;    //multiplied
		this.y = y << 4;
		animatedSprite = bee_down;
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
			animatedSprite = bee_up;
		}
		if(ya > 0) {
			direction = Direction.DOWN;
			animatedSprite = bee_down;
		}
		if(xa < 0) {
			direction = Direction.LEFT;
			animatedSprite = bee_left;
		}
		if(xa > 0) {
			direction = Direction.RIGHT;
			animatedSprite = bee_right;
		}
	}

	public void render(Screen screen) {
		//Debug.drawRect(screen, 40*16, 40*16, 100, 40, 0xff00ff99, true);
		sprite = animatedSprite.getSprite();
		screen.renderMob16((int)(x - 8), (int)(y - 8), this);
	}
	private void move() {
		List<Player> players = level.getPlayers(this, 10);
		if(players.size() > 0) {
			xa = 0;
			ya = 0;
			//this is in pixel precision -> needs to be converted to tile precision to use the A* Search Algorithm
			// hence all the >> 4, yet moving actually requires it to be converted by to pixel precision hence << 4
			int playerX = (int) level.getPlayerAt(0).getX();	
			int playerY = (int) level.getPlayerAt(0).getY();
			Vector2i start = new Vector2i(((int)this.x) >> 4, ((int)this.y) >> 4);
			Vector2i finish = new Vector2i(playerX >> 4, playerY >> 4);
			if(time % 20 == 0) path = level.findPath(start, finish);
			if (path != null) {
				if (path.size() > 0) {
					Vector2i vector = path.get(path.size() - 1).tile;
					if(x < vector.getX() << 4) xa+=speed;
					if(x > vector.getX() << 4) xa-=speed;
					if(y < vector.getY() << 4) ya+=speed;
					if(y > vector.getY() << 4) ya-=speed;
					if (Math.floor(x) == Math.floor(vector.getX() << 4)) xa = 0;
					if (Math.floor(y) == Math.floor(vector.getY() << 4)) ya = 0;
				} 
			}
		}
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = 0;
			ya = 0;
			int randomX = random.nextInt(3) - 1;
			int randomY = random.nextInt(3) - 1;
			xa += randomX * speed;
			ya += randomY * speed;
		}	
		if (random.nextInt(30) == 0) {
			xa = 0;
			ya = 0;
		}		
		if (xa != 0 || ya != 0) { 
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
}
