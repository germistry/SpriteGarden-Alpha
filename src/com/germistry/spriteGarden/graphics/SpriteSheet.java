package com.germistry.spriteGarden.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT; // width and height of number of sprites in sheet
	private int sheetWidth, sheetHeight;
	public int[] pixels;
	private Sprite[] sprites;
		
	//create new instances of sprite sheets
	public static SpriteSheet terrain = new SpriteSheet("/sprites/terrain.png", 512);
	public static SpriteSheet staticEntities = new SpriteSheet("/sprites/staticEntities.png", 512);
	
	//new instances of sprite sheets for animation
	public static SpriteSheet mobs = new SpriteSheet("/sprites/mobs.png", 512);
	public static SpriteSheet ladyBeetle_down = new SpriteSheet(mobs, 0, 0, 1, 3, 16);
	public static SpriteSheet ladyBeetle_up = new SpriteSheet(mobs, 2, 0, 1, 3, 16);
	public static SpriteSheet ladyBeetle_left = new SpriteSheet(mobs, 3, 0, 1, 3, 16);
	public static SpriteSheet ladyBeetle_right = new SpriteSheet(mobs, 1, 0, 1, 3, 16);
	public static SpriteSheet gardenGate_shut = new SpriteSheet(mobs, 1, 0, 1, 3, 64);
	public static SpriteSheet gardenGate_open = new SpriteSheet(mobs, 2, 0, 1, 3, 64);
	public static SpriteSheet kitty_down = new SpriteSheet(mobs, 6, 0, 1, 3, 32);
	public static SpriteSheet kitty_up = new SpriteSheet(mobs, 8, 0, 1, 3, 32);
	public static SpriteSheet kitty_left = new SpriteSheet(mobs, 9, 0, 1, 3, 32);
	public static SpriteSheet kitty_right = new SpriteSheet(mobs, 7, 0, 1, 3, 32);
	public static SpriteSheet bee_down = new SpriteSheet(mobs, 0, 3, 1, 3, 16);
	public static SpriteSheet bee_up = new SpriteSheet(mobs, 2, 3, 1, 3, 16);
	public static SpriteSheet bee_left = new SpriteSheet(mobs, 3, 3, 1, 3, 16);
	public static SpriteSheet bee_right = new SpriteSheet(mobs, 1, 3, 1, 3, 16);
	
	public static SpriteSheet players = new SpriteSheet("/sprites/players.png", 192, 144);
	public static SpriteSheet defaultGirlPlayer_down = new SpriteSheet(players, 0, 0, 1, 3, 48);
	public static SpriteSheet defaultGirlPlayer_up = new SpriteSheet(players, 2, 0, 1, 3, 48);
	public static SpriteSheet defaultGirlPlayer_left = new SpriteSheet(players, 3, 0, 1, 3, 48);
	public static SpriteSheet defaultGirlPlayer_right = new SpriteSheet(players, 1, 0, 1, 3, 48);
		
	//for extracting a sprite from a sheet - width and height refer to area of sprite(s) on sheet.
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xOffset = x * spriteSize;
		int yOffset = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int ya = 0; ya < h; ya++) {
			int ypos = yOffset + ya;
			for (int xa = 0; xa < w; xa++) {
				int xpos = xOffset + xa;
				pixels[xa + ya * w] = sheet.pixels[xpos + ypos * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width*height];
		for (int yb = 0; yb < height; yb++) {
			for (int xb = 0; xb < width; xb++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int ya = 0; ya < spriteSize; ya++) {
					for (int xa = 0; xa < spriteSize; xa++) {
						spritePixels[xa + ya * spriteSize] = 
								pixels[(xa + xb * spriteSize) + (ya + yb * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}
	//constructor for 2^n sized sheet
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	//constructor for sheet of different width & height
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public int getSheetWidth() {
		return sheetWidth;
	}

	public int getSheetHeight() {
		return sheetHeight;
	}

	//The base load method for a spritesheet
	private void load() {
		try {
			System.out.print("Trying to load: " + path + " ...");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println("succeeded! "+ image.getType());
			sheetWidth = image.getWidth();
			sheetHeight = image.getHeight();
			pixels = new int[sheetWidth * sheetHeight];
			image.getRGB(0, 0, sheetWidth, sheetHeight, pixels, 0, sheetWidth);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("failed!");
		}
		
	}
}
