package com.germistry.spriteGarden.graphics.gui.hud.components;

public class HUDButtonListener {

	public void entered(Button button) {
		button.setColour(0xff8AFF6D);
	}
	public void exited(Button button) {
		button.setColour(0xffABFF96);
	}
	
	public void pressed(Button button) {
		button.setColour(0xff555555);
	}
	
	public void released(Button button) {
		button.setColour(0xff8AFF6D);
	}
}
