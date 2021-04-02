package com.germistry.spriteGarden.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadedLevel extends Level {

	public LoadedLevel(String mapName) {
		super(mapName);
	}

	protected void loadLevel(String mapName) {
		ArrayList<ArrayList<Integer>> tempLayout = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(mapName))) 
		{
			System.out.println("Reading level file ..");
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				if(currentLine.isEmpty())
					continue;
				ArrayList<Integer> row = new ArrayList<>();
				String[] values = currentLine.trim().split(",");
				for(String string : values) {
					if(!string.isEmpty()) {
						int id = Integer.parseInt(string);
						row.add(id);
					}
				}
				tempLayout.add(row);
			}	
			System.out.println("Level file has been read.");
		}
		catch (IOException e){
			e.printStackTrace();
			System.err.println("File could not be read");
		} 
		int w = width = tempLayout.get(0).size();
		int h = height = tempLayout.size();
		tiles = new int[w * h];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x + y * width] = tempLayout.get(x).get(y);
			}
		}
	}
}
