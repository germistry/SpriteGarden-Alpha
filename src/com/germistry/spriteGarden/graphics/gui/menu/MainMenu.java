package com.germistry.spriteGarden.graphics.gui.menu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.germistry.spriteGarden.Main;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.input.Keyboard;
import com.germistry.spriteGarden.input.Mouse;
import com.germistry.spriteGarden.level.Level;
import com.germistry.spriteGarden.utils.MapUtils;

public class MainMenu extends Menu {
	
	String[] options = { 
			"New Game", 
			"Load Saved Game",
			"How to Play", 
			"About" };
	private int clickRate = 0;
	private BufferedImage image = null;
	private BufferedImage imageButton= null;
		
	public MainMenu(Keyboard input) {
		super(input);
		clickRate = Mouse.CLICK_RATE;
		try {
			//IMPORTANT!!!! These images need to be type 6 - not 13 like the mob and players sheets, make sure 
			//in paint.net that these are saved as 32 bit depth!!!!! not a random type!
			image = ImageIO.read(getClass().getResource("/images/MainMenuBackground.png"));
			imageButton = ImageIO.read(getClass().getResource("/images/button.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void update() {
		int x = Mouse.getX();
		int y = Mouse.getY();
		if(clickRate > 0) clickRate--;		
		
		if(x >= 310 && x <= 690) {
			if(y >= 318 && y <= 364 && Mouse.getMouseButton() == 1 && clickRate <= 0) {
				Main.State = Main.STATE.NEWGAMEMENU;
				clickRate = Mouse.CLICK_RATE;
			}
		}
		if(x >= 310 && x <= 690) {
			if(y >= 378 && y <= 424 && Mouse.getMouseButton() == 1 && clickRate <= 0) {
				Main.State = Main.STATE.LOADGAME;
				clickRate = Mouse.CLICK_RATE;
			}
		}
		if(x >= 310 && x <= 690) {
			if(y >= 438 && y <= 484 && Mouse.getMouseButton() == 1 && clickRate <= 0) {
				Main.State = Main.STATE.HOWTOPLAY;
				clickRate = Mouse.CLICK_RATE;
			}
		}
		if(x >= 310 && x <= 690) {
			if(y >= 498 && y <= 544 && Mouse.getMouseButton() == 1 && clickRate <= 0) {
				Main.State = Main.STATE.ABOUT;
				clickRate = Mouse.CLICK_RATE;
			}
		}
	}
	public void render(Screen screen) {
		screen.renderImage(image, 0, 0, image.getWidth(), image.getHeight());	
		screen.renderText("Version Alpha 1.0!", 710, 30, 28, 1, 0xffffff);
		screen.renderText("Germistry's", 374, 70, 40, 1, 0xDDFFDB);
		for(int i = 0; i < options.length; i++) {
			screen.renderImage(imageButton, 310, 318 + i * 60, imageButton.getWidth(), imageButton.getHeight());
		}
		for (int i = 0; i < options.length; i++) {
			screen.renderText(options[i], 330, 351 + i * 60, 36, 1, 0x000000);
		}
	}
}
