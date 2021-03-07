package com.germistry.spriteGarden.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.germistry.spriteGarden.level.Level;
import com.germistry.spriteGarden.level.TileCoord;

public class MapUtils {

	public static void generateMapData(String mapName, Level level) {
		String mapData = "";
		TileCoord map = new TileCoord(level.getWidth() >> 4, level.getHeight() >> 4); 
		for (int y = 0; y < map.getY(); y++) {
			for (int x = 0; x < map.getX(); x++) {
				  mapData += level.getTileId(x, y) + ",";
			}
			mapData += "\n";
		}
		try {
			File file = new File(mapName);
			System.out.println("Reading Writing file ...");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(mapData);
			bw.close();
			System.out.println("Finished writing file ...");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("File could not be saved or written.");
		}
	}
}
