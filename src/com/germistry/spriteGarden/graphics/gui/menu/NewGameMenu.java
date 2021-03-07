package com.germistry.spriteGarden.graphics.gui.menu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.germistry.spriteGarden.Main;
import com.germistry.spriteGarden.entity.mob.Player;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.input.KeyBuffer;
import com.germistry.spriteGarden.input.Keyboard;
import com.germistry.spriteGarden.input.Mouse;

public class NewGameMenu extends Menu {

	String[] options = { 
			"Play", 
			"Return to Main",
			"Options" 
			};
	private int clickRate = 0;
	private int pressedRate = 0;
	private BufferedImage image = null;
	private BufferedImage imageButton= null;
	private String playerName;
	private KeyBuffer buffer = new KeyBuffer();
	
	public NewGameMenu(Keyboard input) {
		super(input);
		clickRate = Mouse.CLICK_RATE;
		pressedRate = Keyboard.PRESSED_RATE;
		playerName = Player.getName();
		try {
			//IMPORTANT!!!! These images need to be type 6 - not 13 like the mob and players sheets, make sure 
			//in paint.net that these are saved as 32 bit depth!!!!! not a random type! 
			image = ImageIO.read(getClass().getResource("/images/BlankMenuBackground.png"));
			imageButton = ImageIO.read(getClass().getResource("/images/button.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public void update() {
		int x = Mouse.getX();
		int y = Mouse.getY();
		if(clickRate > 0) clickRate--;	
		if(pressedRate > 0) pressedRate--; 
		
		if(input.charA && pressedRate <= 0) {	buffer.add("a"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charB && pressedRate <= 0) {	buffer.add("b"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charC && pressedRate <= 0) {	buffer.add("c"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charD && pressedRate <= 0) {	buffer.add("d"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charE && pressedRate <= 0) {	buffer.add("e"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charF && pressedRate <= 0) {	buffer.add("f"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charG && pressedRate <= 0) {	buffer.add("g"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charH && pressedRate <= 0) {	buffer.add("h"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charI && pressedRate <= 0) {	buffer.add("i"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charJ && pressedRate <= 0) {	buffer.add("j"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charK && pressedRate <= 0) {	buffer.add("k"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charL && pressedRate <= 0) {	buffer.add("l"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charM && pressedRate <= 0) {	buffer.add("m"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charN && pressedRate <= 0) {	buffer.add("n"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charO && pressedRate <= 0) {	buffer.add("o"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charP && pressedRate <= 0) {	buffer.add("p"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charQ && pressedRate <= 0) {	buffer.add("q"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charR && pressedRate <= 0) {	buffer.add("r"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charS && pressedRate <= 0) {	buffer.add("s"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charT && pressedRate <= 0) {	buffer.add("t"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charU && pressedRate <= 0) {	buffer.add("u"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charV && pressedRate <= 0) {	buffer.add("v"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charW && pressedRate <= 0) {	buffer.add("w"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charX && pressedRate <= 0) {	buffer.add("x"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charY && pressedRate <= 0) {	buffer.add("y"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charZ && pressedRate <= 0) {	buffer.add("z"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char0 && pressedRate <= 0) {	buffer.add("0"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char1 && pressedRate <= 0) {	buffer.add("1"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char2 && pressedRate <= 0) {	buffer.add("2"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char3 && pressedRate <= 0) {	buffer.add("3"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char4 && pressedRate <= 0) {	buffer.add("4"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char5 && pressedRate <= 0) {	buffer.add("5"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char6 && pressedRate <= 0) {	buffer.add("6"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char7 && pressedRate <= 0) {	buffer.add("7"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char8 && pressedRate <= 0) {	buffer.add("8"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.char9 && pressedRate <= 0) {	buffer.add("9"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charSpace && pressedRate <= 0) {	buffer.add(" "); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charApost && pressedRate <= 0) {	buffer.add("\'"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.charHypen && pressedRate <= 0) {	buffer.add("-"); pressedRate = Keyboard.PRESSED_RATE;	}
		if(input.backSpace && pressedRate <= 0) {	buffer.add("backspace"); pressedRate = Keyboard.PRESSED_RATE;	}
		
		playerName = buffer.toString();
		
		if(x >= 310 && x <= 690) {
			if(y >= 378 && y <= 424 && Mouse.getMouseButton() == 1 && clickRate <= 0 && !playerName.isEmpty()) {
				Player.setName(playerName.toUpperCase());
				Main.State = Main.STATE.PLAY;
				clickRate = Mouse.CLICK_RATE;
			}
		}
		if(x >= 310 && x <= 690) {
			if(y >= 438 && y <= 484 && Mouse.getMouseButton() == 1 && clickRate <= 0) {
				Main.State = Main.STATE.MAINMENU;
				clickRate = Mouse.CLICK_RATE;
			}
		}	
		if(x >= 310 && x <= 690) {
			if(y >= 498 && y <= 544 && Mouse.getMouseButton() == 1 && clickRate <= 0) {
				Main.State = Main.STATE.SAVEGAME;
				clickRate = Mouse.CLICK_RATE;
			}
		}
	}
	public void render(Screen screen) {
		screen.renderImage(image, 0, 0, image.getWidth(), image.getHeight());	
		screen.renderText("Version Alpha 1.0!", 710, 30, 28, 1, 0xffffff);
		screen.renderText("New Game", 410, 140, 40, 1, 0xDDFFDB);
		screen.renderImage(imageButton, 310, 218, imageButton.getWidth(), imageButton.getHeight());
		if(playerName != null) 
			screen.renderText(playerName.toUpperCase(), 330, 251, 36, 1, 0x000000);
		for(int i = 0; i < options.length; i++) {
			screen.renderImage(imageButton, 310, 378 + i * 60, imageButton.getWidth(), imageButton.getHeight());
		}
		for (int i = 0; i < options.length; i++) {
			screen.renderText(options[i], 330, 411 + i * 60, 36, 1, 0x000000);
		}
		
	}
	
	
	
}
