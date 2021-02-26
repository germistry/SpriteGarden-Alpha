package com.germistry.spriteGarden.graphics.gui;

import java.util.Random;

import com.germistry.spriteGarden.SpriteGardenGame;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.input.Keyboard;

public class Menu {
	
	private Keyboard input; // will use this for player to enter their name
	protected SpriteGardenGame game;
	protected final Random random = new Random();
	
	public Menu(Keyboard input) {
		this.input = input;
	}

	public Menu(SpriteGardenGame game) {
		this.game = game;
	}

	public void update() {
	}

	public void render(Screen screen) {
	}
}
