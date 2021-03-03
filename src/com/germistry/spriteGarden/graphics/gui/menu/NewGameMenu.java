package com.germistry.spriteGarden.graphics.gui.menu;

import com.germistry.spriteGarden.Main;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.input.Keyboard;
import com.germistry.spriteGarden.input.Mouse;

public class NewGameMenu extends Menu {

	String[] options = { 
			"Play", 
			"Return to Main",
			"How to Play", 
			"About" };
	private int clickRate = 0;
	
	public NewGameMenu(Keyboard input) {
		super(input);
		clickRate = Mouse.CLICK_RATE;
	}

	public void update() {
		int x = Mouse.getX();
		int y = Mouse.getY();
		if(clickRate > 0) clickRate--;	
		//330, 318, 390, 46 newgame (play in essence at the moment)
		//330, 378, 390, 46 loadgame 
		//330, 438, 390, 46 how to
		//330, 498, 390, 46 about
		
		if(x >= 330 && x <= 720) {
			if(y >= 318 && y <= 364 && Mouse.getMouseButton() == 1 && clickRate <= 0) {
				Main.State = Main.STATE.PLAY;
				clickRate = Mouse.CLICK_RATE;
			}
		}
		if(x >= 330 && x <= 720) {
			if(y >= 378 && y <= 424 && Mouse.getMouseButton() == 1 && clickRate <= 0) {
				Main.State = Main.STATE.MAINMENU;
				clickRate = Mouse.CLICK_RATE;
			}
		}
	}
	public void render(Screen screen) {
		screen.renderText("Sprite Garden", 195 + 4, 140 + 4, 72, 1, 0x27511C);
		screen.renderText("Sprite Garden", 195, 140, 72, 1, 0x7DFF59);
//		screen.renderText("A demo game by germistry.", 260 + 2, 188 + 2, 30, 1, 0x27511C);
//		screen.renderText("A demo game by germistry.", 260, 188, 30, 1, 0x7DFF59);
		for(int i = 0; i < options.length; i++) {
			screen.drawRect(330, 318 + i * 60, 390, 46, 0xffffff);
		}
		for (int i = 0; i < options.length; i++) {
			screen.renderText(options[i], 350 + 3, 350 + i * 60 + 3, 36, 1, 0x27511C);
			screen.renderText(options[i], 350, 350 + i * 60, 36, 1, 0x7DFF59);
		}
		
	}
	
}
