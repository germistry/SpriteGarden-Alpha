package com.germistry.spriteGarden.entity.mob;

import java.util.List;

import com.germistry.spriteGarden.graphics.AnimatedSprite;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.SpriteSheet;
import com.germistry.spriteGarden.level.TileNode;
import com.germistry.spriteGarden.utils.Vector2i;

public class Kitty extends Mob{

	private int animate = 0;
	
	private AnimatedSprite kitty_down = new AnimatedSprite(SpriteSheet.kitty_down, 32, 32, 3);
	private AnimatedSprite kitty_up = new AnimatedSprite(SpriteSheet.kitty_up, 32, 32, 3);
	private AnimatedSprite kitty_left = new AnimatedSprite(SpriteSheet.kitty_left, 32, 32, 3);
	private AnimatedSprite kitty_right = new AnimatedSprite(SpriteSheet.kitty_right, 32, 32, 3);
	
	private AnimatedSprite animatedSprite = kitty_down;
	
	private double xa = 0, ya = 0;
	private double speed = 0.8;
	private int time = 0;
	
	private List<TileNode> path = null;
	
	public Kitty(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		animatedSprite = kitty_down;
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
			animatedSprite = kitty_up;
		}
		if(ya > 0) {
			direction = Direction.DOWN;
			animatedSprite = kitty_down;
		}
		if(xa < 0) {
			direction = Direction.LEFT;
			animatedSprite = kitty_left;
		}
		if(xa > 0) {
			direction = Direction.RIGHT;
			animatedSprite = kitty_right;
		}
	}

	public void render(Screen screen) {
		sprite = animatedSprite.getSprite();
		screen.renderMob((int)(x), (int)(y), this);
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
			moveKitty(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
	private void moveKitty(double xpixel, double ypixel) {
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
				if (!collision(abs(xpixel), ypixel, this.getSpriteSize())) {
					this.x += abs(xpixel);
				}
				xpixel -= abs(xpixel);
			} else {
				if (!collision(abs(xpixel), ypixel, this.getSpriteSize())) {
					this.x += xpixel;
				}
				xpixel = 0;
			}
		}
		
		while (ypixel != 0) {
			if (Math.abs(ypixel) > 1) {
				if (!collision(xpixel, abs(ypixel), this.getSpriteSize())) {
					this.y += abs(ypixel);
				}
				ypixel -= abs(ypixel);
			} else {
				if (!collision(xpixel, abs(ypixel), this.getSpriteSize())) {
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
	//TODO Implement better collision detection for tiles & entities - use a bounding box 
	private boolean collision(double xpixel, double ypixel, int size) {
		boolean solid = false;
		for(int corner = 0; corner < 4; corner++) {
			double xt = ((x + xpixel) - corner % 2 * 8 + 4)/16;
			double yt = ((y + ypixel) - corner / 2 * 8 + 4)/16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(corner % 2 == 0) ix =(int) Math.floor(xt);
			if(corner / 2 == 0) iy =(int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
}

