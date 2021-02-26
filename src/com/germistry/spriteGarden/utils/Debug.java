package com.germistry.spriteGarden.utils;

import com.germistry.spriteGarden.graphics.Screen;

public class Debug {

	private Debug() {	
	}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, int colour, boolean fixed) {
		screen.drawRect(x, y, width, height, colour, fixed);
	}
	public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed) {
		drawRect(screen, x, y, width, height, 0xff00ff99, fixed);
	}
}
