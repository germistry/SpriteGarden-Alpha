package com.germistry.spriteGarden.input;

public class KeyBuffer {

	private static final String SIMPLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789 -\'";

	private String buffer = "";
	private int position = 0;

	private String[] splitAtCurrentPosition() {
		return new String[]{
			buffer.substring(0, position),
			buffer.substring(position, buffer.length() == position ? position : buffer.length())
		};
	}

	private void addCharacter(String character) {
		String[] parts = splitAtCurrentPosition();
		buffer = parts[0].substring(0, parts[0].length()) + character + parts[1];
		position++;
	}

	public void add(String key) {
		if (SIMPLE_CHARACTERS.contains(key)) {
			addCharacter(key);
			return;
		}
		if ("backspace".equals(key) && buffer.length() > 0) {
			String[] parts = splitAtCurrentPosition();
			buffer = parts[0].substring(0, parts[0].length() - 1) + parts[1];
			position --;
		} else {
			throw new UnsupportedOperationException("The key " + key + " is not supported");
		}
	}

	public String toString() {
		return buffer;
	}	
	
}
