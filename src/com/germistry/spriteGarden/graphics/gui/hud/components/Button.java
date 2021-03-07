package com.germistry.spriteGarden.graphics.gui.hud.components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.germistry.spriteGarden.graphics.gui.hud.HUDComponent;
import com.germistry.spriteGarden.graphics.gui.hud.HUDPanel;
import com.germistry.spriteGarden.graphics.gui.hud.IHUDActionListener;
import com.germistry.spriteGarden.input.Mouse;
import com.germistry.spriteGarden.utils.Vector2i;

public class Button extends HUDComponent {

	private HUDButtonListener btnListener;
	private IHUDActionListener actionListener;
	
	public Label label;
	
	private Image image;
	
	private boolean inside = false;
	private boolean pressed = false;
	private boolean ignorePressed = false;
	private boolean ignoreExecute = false;
	
	public Button(Vector2i position, Vector2i size, IHUDActionListener actionListener) {
		super(position, size);
		this.actionListener = actionListener;
		Vector2i labelPos = new Vector2i(position);
		labelPos.x +=  10;
		labelPos.y += size.y / 2 + 6;
		label = new Label(labelPos, "");
		label.setColour(0xff27511C);
		label.setFont(new Font("Courier New", Font.BOLD, 18));
		label.active = false;
		init();
		
	}
	
	public Button(Vector2i position, BufferedImage image, IHUDActionListener actionListener) {
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		this.actionListener = actionListener;
		setImage(image);
		init();
	}
	
	private void init() {
		setColour(0xffABFF96);
		btnListener = new HUDButtonListener();
	}
	
	public void init(HUDPanel panel) {
		super.init(panel);
		if (label != null)
			panel.addComponent(label);
	}
	
	public void update() {
		//rect is the bounds of the button 
		Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
		boolean leftMouseBtnDown = Mouse.getMouseButton() == MouseEvent.BUTTON1;
		if(rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
			if(!inside) {
				if(leftMouseBtnDown)
					ignorePressed = true;
				else 
					ignorePressed = false;
				btnListener.entered(this);
			}
			inside = true;
			if(!pressed && !ignorePressed && leftMouseBtnDown) {
				btnListener.pressed(this);
				pressed = true;
			} else if(Mouse.getMouseButton() == MouseEvent.NOBUTTON) {
				if(pressed) {
					btnListener.released(this);
					if(!ignoreExecute) {
						actionListener.execute();
					} else 
						ignoreExecute = false;
					
					pressed = false;
				}
				ignorePressed = false;
			}
		} else {
			if (inside) { 
				btnListener.exited(this);
				pressed = false;
			}
			inside = false;
			
		}
	}
	
	public void executeAction() {
		actionListener.execute();
	}
	
	public void ignoreNextPress() {
		ignoreExecute = true;
	}

	public void render(Graphics graphics) {
		int x = position.x + offset.x;
		int y = position.y + offset.y;
		if (image != null) {
			graphics.drawImage(image, x, y, null);
		} else {
			graphics.setColor(colour);
			graphics.fillRect(x, y, size.x, size.y);
			if (label != null) label.render(graphics);
		}
		
	}

	public void setText(String text) {
		if(text == "") 
			label.active = false;		
		else
			label.text = text;
	}

	public void setHUDButtonListener(HUDButtonListener btnListener) {
		this.btnListener = btnListener;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}
