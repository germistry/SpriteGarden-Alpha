package com.germistry.spriteGarden.graphics;

public class Font {

	private static SpriteSheet consolas14Font = new SpriteSheet("/fonts/Consolas14.png", 16);
	private static Sprite[] consolas14Chars = Sprite.split(consolas14Font);
	
	private static String charIndex = //
			" !\"#$%&'()*+,-./" + //
			"0123456789:;<=>?" + //
			"@ABCDEFGHIJKLMNO" + //
			"PQRSTUVWXYZ[\\]^_" + //
			"`abcdefghijklmno" + //
			"pqrstuvwxyz{|}~ ";
	
	public Font() {	
	}
	
	public void renderConsolas14(Screen screen, String text, int colour, int x, int y) {
		renderConsolas14(screen, text, x, y, 0, colour);
	}
	public void renderConsolas14(Screen screen, String text, int x, int y, int spacing, int colour) {
		int xOffset = 0;
		int line = 0;
		for (int i = 0; i < text.length(); i++) {
			xOffset += 8 + spacing; 
			char currentChar = text.charAt(i);
			if(currentChar == '\n') {
				xOffset = 0;
				line++;
			}
			int index = charIndex.indexOf(currentChar);
			if(index == -1) continue;
			screen.renderTextCharacters(x + xOffset, y + line * 18, consolas14Chars[index], false, colour);
		}
	}
	
}
