package com.germistry.spriteGarden.graphics.gui.menu;

import java.util.Random;

import com.germistry.spriteGarden.Main;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.input.Keyboard;

public class Menu {
	
	protected Keyboard input; // will use this for player to enter their name
	protected Main game;
	protected final Random random = new Random();
	
	public Menu(Keyboard input) {
		this.input = input;
	}

	public Menu(Main game) {
		this.game = game;
	}

	public void update() {
	}

	public void render(Screen screen) {
	}

}
