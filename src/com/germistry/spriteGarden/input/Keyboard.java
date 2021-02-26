package com.germistry.spriteGarden.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right, escape;
	
	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		escape = keys[KeyEvent.VK_ESCAPE];
//		for(int i = 0; i < keys.length; i++) {
//			if(keys[i]) {
//				System.out.println("KEY: " + i);
//			}
//		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
