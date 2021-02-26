package com.germistry.spriteGarden.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.germistry.spriteGarden.entity.mob.Bee;
import com.germistry.spriteGarden.entity.mob.GardenGate;
import com.germistry.spriteGarden.entity.mob.Kitty;
import com.germistry.spriteGarden.entity.mob.LadyBeetle;

public class SpawnLevel extends Level {

	
	public SpawnLevel(String path) {
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
		add(new GardenGate(80, 20));
		for (int i = 0; i < 20; i++) {
			add(new LadyBeetle((random.nextInt(76) + 26), (random.nextInt(76) + 26)));
		}
		for (int i = 0; i < 2; i++) {
			add(new Kitty((random.nextInt(74) + 28), (random.nextInt(74) + 28)));
		}
		for (int i = 0; i < 2; i++) {
			add(new Bee((random.nextInt(76) + 26), (random.nextInt(76) + 26)));
		}
		
		//render entities that player can walk behind after the player
	}
	
}
