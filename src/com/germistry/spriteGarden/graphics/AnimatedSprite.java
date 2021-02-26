package com.germistry.spriteGarden.graphics;

public class AnimatedSprite extends Sprite {
	
	private int frame = 0, time = 0;
	private Sprite sprite;
	private int rate = 8;
	private int length = -1;

	private int[] cycle = new int[] {0,1,0,2};
	private int frameIndex = 0;
	
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(width, height, sheet);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("Length of animation is too long");
	}

	public void update() {
		time++;
		 if(time % rate == 0){
		   if(frame >= length - 1) frame = 0;
		   else frame++;
		   frameIndex++;
		   if(frameIndex > 3) frameIndex = 0;
		   sprite = sheet.getSprites()[cycle[frameIndex]];
		}
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrameRate(int frames) {
		rate = frames;
	}
	public void setFrame(int i) {
		if(i > sheet.getSprites().length - 1) {
			System.err.println("Index(i) out of bounds in " + this);
			return; 
		}
		sprite = sheet.getSprites()[i];
	}
	
}
