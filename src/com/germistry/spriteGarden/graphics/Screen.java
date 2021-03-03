package com.germistry.spriteGarden.graphics;

import java.awt.Color;
import java.util.Random;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import com.germistry.spriteGarden.entity.crops.Crop;
import com.germistry.spriteGarden.entity.mob.Mob;
import com.germistry.spriteGarden.entity.projectile.Projectile;
import com.germistry.spriteGarden.entity.staticEntities.StaticEntity;
import com.germistry.spriteGarden.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 8;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int xOffset, yOffset;
	private Graphics graphics;

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private final int ALPHA_COL = 0xffff00ff;
	
	private Random random = new Random();
		
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for(int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void graphics(Graphics graphics) {
		this.graphics = graphics;
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	//render sprite methods
	public void renderSprite(int xpos, int ypos, Sprite sprite, boolean fixed) {
		if (fixed) {
			xpos -= xOffset;
			ypos -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yabs = y + ypos;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xabs = x + xpos;
				if(xabs < 0 || xabs >= width || yabs < 0 || yabs >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL) pixels[xabs + yabs * width] = col;
			}
		}
	}
	//for testing, set boolean to false in game instance
	public void renderSpriteSheet(int xpos, int ypos, SpriteSheet spriteSheet, boolean fixed) {
		if (fixed) {
			xpos -= xOffset;
			ypos -= yOffset;
		}
		for (int y = 0; y < spriteSheet.HEIGHT; y++) {
			int yabs = y + ypos;
			for (int x = 0; x < spriteSheet.WIDTH; x++) {
				int xabs = x + xpos;
				if(xabs < 0 || xabs > width || yabs < 0 || yabs > height) continue;
				pixels[xabs + yabs * width] = spriteSheet.pixels[x + y * spriteSheet.WIDTH];
			}
		}
	}
	public void renderTile(int xpixel, int ypixel, Tile tile) {
		xpixel -= xOffset;
		ypixel -= yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++) {
			int yabs = y + ypixel;
			for(int x = 0; x < tile.sprite.SIZE; x++) {
				int xabs = x + xpixel;
				if(xabs < -tile.sprite.SIZE || xabs >= width || yabs < 0 || yabs >= height) break;
				if(xabs < 0) xabs = 0;
				pixels[xabs + yabs * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	public void renderProjectile(int xpixel, int ypixel, Projectile projectile) {
		xpixel -= xOffset;
		ypixel -= yOffset;
		for(int y = 0; y < projectile.getSpriteSize(); y++) {
			int yabs = y + ypixel;
			for(int x = 0; x < projectile.getSpriteSize(); x++) {
				int xabs = x + xpixel;
				if(xabs < -projectile.getSpriteSize() || xabs >= width || yabs < 0 || yabs >= height) break;
				if(xabs < 0) xabs = 0;
				int col = projectile.getSprite().pixels[x + y * projectile.getSprite().SIZE];
				if (col != ALPHA_COL) pixels[xabs + yabs * width] = col;
			}
		}
	}
	public void renderMob(int xpixel, int ypixel, Mob mob) {
		xpixel -= xOffset;
		ypixel -= yOffset;
		for(int y = 0; y < mob.getSpriteHeight(); y++) {
			int yabs = y + ypixel;
			int ysprite = y;
			for(int x = 0; x < mob.getSpriteWidth(); x++) {
				int xabs = x + xpixel;
				int xsprite = x;
				if(xabs < -mob.getSpriteWidth() || xabs >= width || yabs < 0 || yabs >= height) break;
				if(xabs < 0) xabs = 0;
				int col = mob.getSprite().pixels[xsprite + ysprite * mob.getSpriteWidth()];
				if (col != ALPHA_COL) pixels[xabs + yabs * width] = col;
			}
		}
	}

	//render crop
	public void renderCrop(int xpixel, int ypixel, Crop crop) {
		xpixel -= xOffset;
		ypixel -= yOffset;
		for(int y = 0; y < crop.getSpriteHeight(); y++) {
			int yabs = y + ypixel;
			int ysprite = y;
			for(int x = 0; x < crop.getSpriteWidth(); x++) {
				int xabs = x + xpixel;
				int xsprite = x;
				if(xabs < -crop.getSpriteWidth() || xabs >= width || yabs < 0 || yabs >= height) break;
				if(xabs < 0) xabs = 0;
				int col = crop.getSprite().pixels[xsprite + ysprite * crop.getSpriteWidth()];
				if (col != ALPHA_COL) pixels[xabs + yabs * width] = col;
			}
		}
	}
	//render static entities (that player can walk in front of)
		public void renderStaticEntity(int xpixel, int ypixel, StaticEntity entity) {
			xpixel -= xOffset;
			ypixel -= yOffset;
			for(int y = 0; y < entity.getSpriteHeight(); y++) {
				int yabs = y + ypixel;
				int ysprite = y;
				for(int x = 0; x < entity.getSpriteWidth(); x++) {
					int xabs = x + xpixel;
					int xsprite = x;
					if(xabs < -entity.getSpriteWidth() || xabs >= width || yabs < 0 || yabs >= height) break;
					if(xabs < 0) xabs = 0;
					int col = entity.getSprite().pixels[xsprite + ysprite * entity.getSpriteWidth()];
					if (col != ALPHA_COL) pixels[xabs + yabs * width] = col;
				}
			}
		}
	
	//render text characters from a font sheet, not used currently
	public void renderTextCharacters(int xpos, int ypos, Sprite sprite, boolean fixed, int colour) {
		if (fixed) {
			xpos -= xOffset;
			ypos -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yabs = y + ypos;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xabs = x + xpos;
				if(xabs < 0 || xabs >= width || yabs < 0 || yabs >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL) pixels[xabs + yabs * width] = colour;
			}
		}
	}
	
	//render a rectangular outline
	public void drawRect(int xpos, int ypos, int width, int height, int colour, boolean fixed) {
		if (fixed) {
			xpos -= xOffset;
			ypos -= yOffset;
		}
		for (int x = xpos; x < xpos + width; x++) {
			if (ypos >= 0 && ypos < this.height && x < this.width && x >= 0)
				pixels[x + (ypos * this.width)] = colour;
			if ((ypos + height) >= 0 && (ypos + height) < this.height && x < this.width && x >= 0)
				pixels[x + ((ypos + height) * this.width)] = colour;
		}
		for (int y = ypos; y <= ypos + height; y++) {
			if (xpos >= 0 && xpos < this.width && y < this.height && y >= 0)
				pixels[xpos + (y * this.width)] = colour;
			if ((xpos + width) >= 0 && (xpos + width) < this.width && y < this.height && y >= 0)
				pixels[(xpos + width) + (y * this.width)] = colour;
		}
	}
	//render a solid rectangle 
	public void fillRect(int xpos, int ypos, int width, int height, int colour, boolean fixed) {
		if (fixed) {
			xpos -= xOffset;
			ypos -= yOffset;
		}
		for(int y = 0; y < height; y++) {
			int ya = ypos + y;
			if(ya < 0 || ya >= this.height) continue;
			for(int x = 0; x < width; x++) {
				int xa = xpos + x;
				if(xa < 0 || xa >= this.width) continue;
				pixels[xa + ya * this.width] = colour;
			}
		}
	}
	
	//render graphics objects such as text or solid rectangle
	public void renderText(String text, int x, int y, int size, int style, int colour) {
		int r = (colour & 0xff0000) >> 16;
		int g = (colour & 0xff00) >> 8;
		int b = (colour & 0xff);
		Color c = new Color(r, g, b);
		Font f = new Font("Courier New", style, size);
		this.graphics.setColor(c);
		this.graphics.setFont(f);
		this.graphics.drawString(text, x, y);
	}

	public void drawRect(int x, int y, int width, int height, int colour) {
		int r = (colour & 0xff0000) >> 16;
		int g = (colour & 0xff00) >> 8;
		int b = (colour & 0xff);
		Color c = new Color(r, g, b); 
		this.graphics.setColor(c);
		this.graphics.fillRect(x, y, width, height);
	}
	//render an image as menu background
	
	public void renderImage(Image image, int xOffset, int yOffset, int width, int height) {
		this.graphics.drawImage(image, xOffset, yOffset, width, height, null);
	}
	
}
