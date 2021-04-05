package com.germistry.spriteGarden.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.germistry.spriteGarden.entity.mob.Bee;
import com.germistry.spriteGarden.entity.mob.Kitty;
import com.germistry.spriteGarden.entity.mob.LadyBeetle;
import com.germistry.spriteGarden.entity.staticEntities.GardenGate;
import com.germistry.spriteGarden.entity.staticEntities.Tree;
//import com.germistry.spriteGarden.entity.staticEntities.Tree;

//TODO next level logic & saving / loading multiple maps
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
		levelId = 0;
		
		add(new GardenGate(80, 20));
		add(new Tree(43, 36));
		for (int i = 0; i < 50; i++) {
			add(new LadyBeetle((random.nextInt(70) + 30), (random.nextInt(70) + 30)));
		}
		for (int i = 0; i < 2; i++) {
			add(new Kitty((random.nextInt(70) + 30), (random.nextInt(70) + 30)));
		}
		for (int i = 0; i < 2; i++) {
			add(new Bee((random.nextInt(70) + 30), (random.nextInt(70) + 30)));
		}
		
		//render entities that player can walk behind after the player - render the player init here?
	}
	
}
