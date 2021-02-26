package com.germistry.spriteGarden.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height; 
	
	public int[] pixels;
	
	protected SpriteSheet sheet;

	//spawn map sprites
	public static Sprite gardenGateTile = new Sprite(16, 0, 1, SpriteSheet.terrain);
	public static Sprite plainLightSand = new Sprite(16, 0, 1, SpriteSheet.terrain);
	public static Sprite detailLightSand = new Sprite(16, 13, 9, SpriteSheet.terrain);
	public static Sprite pittedLightSand = new Sprite(16, 9, 4, SpriteSheet.terrain);
	
	public static Sprite plainMidGreenGrass = new Sprite(16, 1, 1, SpriteSheet.terrain);
	public static Sprite detailMidGreenGrass = new Sprite(16, 2, 1, SpriteSheet.terrain);
	public static Sprite weedMidGreenGrass = new Sprite(16, 3, 1, SpriteSheet.terrain);
	
	public static Sprite midGrGrassLSandEdgeTop = new Sprite(16, 4, 6, SpriteSheet.terrain);
	public static Sprite midGrGrassLSandEdgeBase = new Sprite(16, 4, 8, SpriteSheet.terrain);
	public static Sprite midGrGrassLSandEdgeLeft = new Sprite(16, 3, 7, SpriteSheet.terrain);
	public static Sprite midGrGrassLSandEdgeRight = new Sprite(16, 5, 7, SpriteSheet.terrain);
	public static Sprite midGrGrassLSandCornerTL = new Sprite(16, 3, 6, SpriteSheet.terrain);
	public static Sprite midGrGrassLSandCornerBL = new Sprite(16, 3, 8, SpriteSheet.terrain);
	public static Sprite midGrGrassLSandCornerTR = new Sprite(16, 5, 6, SpriteSheet.terrain);
	public static Sprite midGrGrassLSandCornerBR = new Sprite(16, 5, 8, SpriteSheet.terrain);
	public static Sprite lgtSandMidGrGrassCornerTL = new Sprite(16, 12, 2, SpriteSheet.terrain);
	public static Sprite lgtSandMidGrGrassCornerBL = new Sprite(16, 12, 3, SpriteSheet.terrain);
	public static Sprite lgtSandMidGrGrassCornerTR = new Sprite(16, 13, 2, SpriteSheet.terrain);
	public static Sprite lgtSandMidGrGrassCornerBR = new Sprite(16, 13, 3, SpriteSheet.terrain);
		
	public static Sprite plainDarkGreenGrass = new Sprite(16, 1, 2, SpriteSheet.terrain);
	public static Sprite detailDarkGreenGrass = new Sprite(16, 2, 2, SpriteSheet.terrain);
	public static Sprite weedDarkGreenGrass = new Sprite(16, 3, 2, SpriteSheet.terrain);
	
	public static Sprite dirtPatch = new Sprite(16, 23, 1, SpriteSheet.terrain);
	public static Sprite detailDirtPatch = new Sprite(16, 19, 9, SpriteSheet.terrain);
	public static Sprite pittedDirtPatch = new Sprite(16, 23, 4, SpriteSheet.terrain);
	public static Sprite weedDirtPatch = new Sprite(16, 23, 7, SpriteSheet.terrain);
	public static Sprite fertileDirt = new Sprite(16, 21, 0, SpriteSheet.terrain);
	
	public static Sprite midGrGrassDirtEdgeTop = new Sprite(16, 23, 3, SpriteSheet.terrain);
	public static Sprite midGrGrassDirtEdgeBase = new Sprite(16, 23, 5, SpriteSheet.terrain);
	public static Sprite midGrGrassDirtEdgeLeft = new Sprite(16, 22, 4, SpriteSheet.terrain);
	public static Sprite midGrGrassDirtEdgeRight = new Sprite(16, 24, 4, SpriteSheet.terrain);
	public static Sprite midGrGrassFertDirtEdgeLeft = new Sprite(16, 21, 3, SpriteSheet.terrain);
	public static Sprite midGrGrassFertDirtEdgeRight = new Sprite(16, 21, 4, SpriteSheet.terrain);
	public static Sprite midGrGrassDirtCornerTL = new Sprite(16, 22, 3, SpriteSheet.terrain);
	public static Sprite midGrGrassDirtCornerBL = new Sprite(16, 22, 5, SpriteSheet.terrain);
	public static Sprite midGrGrassDirtCornerTR = new Sprite(16, 24, 3, SpriteSheet.terrain);
	public static Sprite midGrGrassDirtCornerBR = new Sprite(16, 24, 5, SpriteSheet.terrain);
	
	public static Sprite pinkTulipMidGrGrass = new Sprite(16, 7, 1, SpriteSheet.terrain);
	public static Sprite orangeTulipMidGrGrass = new Sprite(16, 3, 13, SpriteSheet.terrain);
	public static Sprite purpleTulipMidGrGrass = new Sprite(16, 12, 7, SpriteSheet.terrain);
	public static Sprite whiteDaisyMidGrGrass = new Sprite(16, 9, 13, SpriteSheet.terrain);
	public static Sprite rockMidGrGrass = new Sprite(16, 5, 15, SpriteSheet.terrain);
	public static Sprite smallPlant1MidGrGrass = new Sprite(16, 15, 7, SpriteSheet.terrain);
	public static Sprite smallPlant2MidGrGrass = new Sprite(16, 11, 15, SpriteSheet.terrain);
	
	public static Sprite wallBlock1 = new Sprite(16, 3, 16, SpriteSheet.terrain);
	public static Sprite wallBlock2 = new Sprite(16, 2, 16, SpriteSheet.terrain);
	public static Sprite wallBlock3 = new Sprite(16, 1, 16, SpriteSheet.terrain);
	public static Sprite wallBlock4 = new Sprite(16, 0, 16, SpriteSheet.terrain);
	
	//void sprite
	public static Sprite voidSprite = new Sprite(16, 0);

	//projectile sprites
	public static Sprite sparklesProjectile = new Sprite(16, 0, 31, SpriteSheet.staticEntities);
	public static Sprite arrowProjectile = new Sprite(16, 0, 30, SpriteSheet.staticEntities);
	//particles 
	public static Sprite sparklesParticle = new Sprite(3, 0x71FF2D);
	
	//for animated sprites in their own sheets
	protected Sprite(int width, int height, SpriteSheet sheet) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	//for loading a selected set of sprites
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		super();
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.width = size;
		this.height = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	//for sheets that aren't square
	public Sprite(int width, int height, int colour) {
		this.width = width;
		this.height = height;
		SIZE = -1;
		pixels = new int[width * height];
		setColour(colour);
	}
	//for square sheets
	public Sprite(int size, int colour) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}
	
	//for getting sprite pixels to add into pixels array in spritesheet
	public Sprite(int[] pixels, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}
	//angle in radians remember!
	public static Sprite rotate(Sprite sprite, double angle) {
		return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
	}
	
	//for rotating a projectile's pixels using the rotX and rotY methods
	private static int[] rotate(int[] pixels, int width, int height, double angle) {
		int[] result = new int[width * height];
		double nxX = rotX(-angle, 1.0, 0.0);
		double nxY = rotY(-angle, 1.0, 0.0);
		double nyX = rotX(-angle, 0.0, 1.0);
		double nyY = rotY(-angle, 0.0, 1.0);
		
		double xInitial = rotX(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
		double yInitial = rotY(-angle, -width / 2.0, -height / 2.0) + height / 2.0;
		
		for(int y = 0; y < height; y++) {
			double xNext = xInitial;
			double yNext = yInitial;
			for(int x = 0; x < width; x++) {
				int xx = (int) xNext;
				int yy = (int) yNext;
				int col = 0;
				if(xx < 0 || xx >= width || yy < 0 || yy >= height) col = 0xffff00ff;
				else col = pixels[xx + yy * width];
				result[x + y * width] = col;
				xNext += nxX;
				yNext += nxY;
			}
			xInitial += nyX;
			yInitial += nyY;
		}
		return result;
	}
	
	private static double rotX(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * cos + y * -sin;
	}
	private static double rotY(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * sin + y * cos;
	}
	
	//splitting font sheets into individual character sprites
	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getSheetWidth() * sheet.getSheetHeight()) / (sheet.WIDTH * sheet.HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.WIDTH * sheet.HEIGHT];
		
		for(int ypos = 0; ypos < sheet.getSheetHeight() / sheet.HEIGHT; ypos++) {
			for(int xpos = 0; xpos < sheet.getSheetWidth() / sheet.WIDTH; xpos++) {
				
				for(int y = 0; y < sheet.HEIGHT; y++) {
					for(int x = 0; x < sheet.WIDTH; x++) {
						int offsetX = x + xpos * sheet.WIDTH;
						int offsetY = y + ypos * sheet.HEIGHT;
						pixels[x + y * sheet.WIDTH] = sheet.getPixels() [offsetX + offsetY * sheet.getSheetWidth()];
					}
				}
				//putting [++current] would increment the index before doing the operation, so [current++] does it after! 
				sprites[current++] = new Sprite(pixels, sheet.WIDTH, sheet.HEIGHT);
			}
		}	
		return sprites;
	}
	//setter for pixels array
	private void setColour(int colour) {
		for(int i = 0; i < width * height; i++) {
			pixels[i] = colour;
		}
	}
	//basic load method
	private void load() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
}
