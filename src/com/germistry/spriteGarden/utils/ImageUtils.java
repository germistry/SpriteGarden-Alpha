package com.germistry.spriteGarden.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;


public class ImageUtils {

	private ImageUtils() {
	}
	
	//creates a new image from an already existing image
	public static BufferedImage changeBrightness(BufferedImage original, int amount) {
		BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
		byte[] pixels = ((DataBufferByte)original.getRaster().getDataBuffer()).getData();
		int[] resultPixels = ((DataBufferInt)result.getRaster().getDataBuffer()).getData();
				
		int offset = 0;
		for (int y = 0; y < original.getHeight(); y++) {
			for (int x = 0; x < original.getWidth(); x++) {
				
				int a = 255;
				if(pixels.length / resultPixels.length == 4) {
					a = Byte.toUnsignedInt(pixels[offset++]);
				}
				
				int b = Byte.toUnsignedInt(pixels[offset++]);
				int g = Byte.toUnsignedInt(pixels[offset++]);
				int r = Byte.toUnsignedInt(pixels[offset++]);
				
				r = MathUtils.clamp(r + amount, 0, 255);
				g = MathUtils.clamp(g + amount, 0, 255);
				b = MathUtils.clamp(b + amount, 0, 255);

				resultPixels[x + y * original.getWidth()] = a << 24 | r << 16 | g << 8 | b;
			}
		}
		return result;
	}
}
