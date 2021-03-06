package com.germistry.spriteGarden.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.germistry.spriteGarden.entity.mob.LadyBeetle;

//TODO render a level as a background?
public class MainMenuLevel extends Level {
	
	public MainMenuLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: Could not load spawn level image.");
		}
		for (int i = 0; i < 10; i++) {
			add(new LadyBeetle((random.nextInt(32) + 16), (random.nextInt(32) + 16)));
		}
		//render entities that player can walk behind after the player
	}
}
