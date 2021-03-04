package com.germistry.spriteGarden.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right, escape, enter;
	public static final int PRESSED_RATE = 15;
	
	//Basic Alphabet booleans to use for player inputting their name. 
	public boolean charA, charB, charC, charD, charE, charF, charG, 
	charH, charI, charJ, charK, charL, charM, charN, charO, charP, charQ, charR, 
	charS, charT, charU, charV, charW, charX, charY, charZ, char0, char1, char2, 
	char3, char4, char5, char6, char7, char8, char9, charSpace, charApost, charHypen, 
	backSpace;
	
	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		escape = keys[KeyEvent.VK_ESCAPE];
		enter = keys[KeyEvent.VK_ENTER];		
		//These for player inputting name
		charA = keys[KeyEvent.VK_A];
		charB = keys[KeyEvent.VK_B];
		charC = keys[KeyEvent.VK_C];		
		charD = keys[KeyEvent.VK_D];
		charE = keys[KeyEvent.VK_E];
		charF = keys[KeyEvent.VK_F];
		charG = keys[KeyEvent.VK_G];
		charH = keys[KeyEvent.VK_H];
		charI = keys[KeyEvent.VK_I];
		charJ = keys[KeyEvent.VK_J];
		charK = keys[KeyEvent.VK_K];		
		charL = keys[KeyEvent.VK_L];
		charM = keys[KeyEvent.VK_M];
		charN = keys[KeyEvent.VK_N];
		charO = keys[KeyEvent.VK_O];
		charP = keys[KeyEvent.VK_P];
		charQ = keys[KeyEvent.VK_Q];
		charR = keys[KeyEvent.VK_R];
		charS = keys[KeyEvent.VK_S];		
		charT = keys[KeyEvent.VK_T];
		charU = keys[KeyEvent.VK_U];
		charV = keys[KeyEvent.VK_V];
		charW = keys[KeyEvent.VK_W];
		charX = keys[KeyEvent.VK_X];
		charY = keys[KeyEvent.VK_Y];
		charZ = keys[KeyEvent.VK_Z];
		char0 = keys[KeyEvent.VK_0];		
		char1 = keys[KeyEvent.VK_1];
		char2 = keys[KeyEvent.VK_2];
		char3 = keys[KeyEvent.VK_3];
		char4 = keys[KeyEvent.VK_4];
		char5 = keys[KeyEvent.VK_5];
		char6 = keys[KeyEvent.VK_6];
		char7 = keys[KeyEvent.VK_7];
		char8 = keys[KeyEvent.VK_8];		
		char9 = keys[KeyEvent.VK_9];
		charSpace = keys[KeyEvent.VK_SPACE];
		charApost = keys[KeyEvent.VK_QUOTE];
		charHypen = keys[KeyEvent.VK_MINUS];
		backSpace = keys[KeyEvent.VK_BACK_SPACE];
		
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
