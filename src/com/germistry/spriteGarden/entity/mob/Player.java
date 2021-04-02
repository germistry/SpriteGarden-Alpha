package com.germistry.spriteGarden.entity.mob;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.germistry.spriteGarden.Main;
import com.germistry.spriteGarden.Main.STATE;
import com.germistry.spriteGarden.entity.items.ItemRock;
import com.germistry.spriteGarden.entity.projectile.ArrowProjectile;
import com.germistry.spriteGarden.entity.projectile.Projectile;
//import com.germistry.spriteGarden.entity.projectile.SparklesProjectile;
import com.germistry.spriteGarden.graphics.AnimatedSprite;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.SpriteSheet;
import com.germistry.spriteGarden.graphics.gui.hud.HUDManager;
import com.germistry.spriteGarden.graphics.gui.hud.HUDPanel;
import com.germistry.spriteGarden.graphics.gui.hud.IHUDActionListener;
import com.germistry.spriteGarden.graphics.gui.hud.components.Button;
import com.germistry.spriteGarden.graphics.gui.hud.components.HUDButtonListener;
import com.germistry.spriteGarden.graphics.gui.hud.components.Label;
import com.germistry.spriteGarden.graphics.gui.hud.components.ProgressBar;
import com.germistry.spriteGarden.input.Keyboard;
import com.germistry.spriteGarden.input.Mouse;

import com.germistry.spriteGarden.level.tile.Tile;
import com.germistry.spriteGarden.utils.ImageUtils;

import com.germistry.spriteGarden.utils.Vector2i;

public class Player extends Mob {

	private Keyboard input;
	private int animate = 0;
	private int fireRate = 0, clickRate = 0;
	private static String name;
	public int cash;
	//private int experience;  // player will get experience when they sell crop produce? Enough get them to next level - better garden/more money
	
	//probably want a 4th frame in animation so maybe 48, 48, 4
	private AnimatedSprite defaultGirlPlayer_down = new AnimatedSprite(SpriteSheet.defaultGirlPlayer_down, 48, 48, 3);
	private AnimatedSprite defaultGirlPlayer_up = new AnimatedSprite(SpriteSheet.defaultGirlPlayer_up, 48, 48, 3);
	private AnimatedSprite defaultGirlPlayer_left = new AnimatedSprite(SpriteSheet.defaultGirlPlayer_left, 48, 48, 3);
	private AnimatedSprite defaultGirlPlayer_right = new AnimatedSprite(SpriteSheet.defaultGirlPlayer_right, 48, 48, 3);
	
	private AnimatedSprite animatedSprite = defaultGirlPlayer_down;
	
	private HUDManager hud;
	private ProgressBar expBar;
	//private Button testBtn;
	private BufferedImage inventoryBtnImage = null;
	private Tile selectedTile;
	private Label selectedTileLbl;
	private Label playerName;
	private Label playerCash;
	//setting a local player, but will change for multiplayer
	
	public Player(Keyboard input) {
		this.input = input;
	}

	public Player(String name, int x, int y, Keyboard input) {
		Player.name = name;
		this.x = x;
		this.y = y;
		this.input = input;
		clickRate = Mouse.CLICK_RATE;
		setupPlayerAttributes();
		setupPlayerHUD();
		
		
	}
	private void setupPlayerAttributes() {
		fireRate = ArrowProjectile.FIRE_RATE;
		addCash(50); 
		//experience = 0;
	}
	private void setupPlayerHUD() {
		hud = Main.getHUDManager();
		HUDPanel panel = new HUDPanel(new Vector2i(780, 0), new Vector2i(240, 576));
		hud.addPanel(panel);
		HUDPanel minimapHolder = new HUDPanel(new Vector2i(786, 8), new Vector2i(228, 228));
		minimapHolder.setColour(0xffff00ff);
		hud.addPanel(minimapHolder);
		
		//player name label
		playerName = new Label(new Vector2i(30, 268), getName());
		playerName.setColour(0xff27511C);
		playerName.setFont(new Font("Courier New", Font.BOLD, 26));
		playerName.shadow = true;
		//playerName.shadowOffset = 4;
		panel.addComponent(playerName);
		
		//TODO Setup actual experience logic  
		expBar = new ProgressBar(new Vector2i(12, 280), new Vector2i(216, 22));
		expBar.setColour(0xffABFF96);
		expBar.setForegroundColour(0xffffff11);
		panel.addComponent(expBar);
		
		Label expLabel = new Label(new Vector2i(20, 295), "EXP");
		expLabel.setColour(0xff27511C);
		expLabel.setFont(new Font("Courier New", Font.BOLD, 14));
		panel.addComponent(expLabel);
	
		//Inventory Button
		try {
			//IMPORTANT!!!! These images need to be type 6 - not 13 like the mob and players sheets, make sure 
			//in paint.net that these are saved as 32 bit depth!!!!! not a random type!
			inventoryBtnImage = ImageIO.read(getClass().getResource("/images/inventoryBtn.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		Button inventoryBtn = new Button(new Vector2i(12, 310), inventoryBtnImage, new IHUDActionListener() {
			public void execute() {
				//TODO open inventory action
				System.out.println("Button pressed");
				
			}
		});
		inventoryBtn.setHUDButtonListener(new HUDButtonListener() {
			public void entered(Button button) {
				button.setImage(ImageUtils.changeBrightness(inventoryBtnImage, 100));
			}
			public void exited(Button button) {
				button.setImage(inventoryBtnImage);
			}
			public void pressed(Button button) {
				button.setImage(ImageUtils.changeBrightness(inventoryBtnImage, -100));
			}
			public void released(Button button) {
				button.setImage(inventoryBtnImage);
			}
		});
		panel.addComponent(inventoryBtn);
		
		playerCash = new Label(new Vector2i(112, 320), "$ " + String.valueOf(getCash()));
		playerCash.setColour(0xff27511C);
		playerCash.setFont(new Font("Courier New", Font.PLAIN, 26));
		panel.addComponent(playerCash);
		
		//TODO Temp display of tile selected 
		selectedTileLbl = new Label(new Vector2i(12, 400), (selectedTile != null ? selectedTile.name : "") );
		selectedTileLbl.setColour(0xff000000);
		selectedTileLbl.setFont(new Font("Courier New", Font.PLAIN, 12));
		panel.addComponent(selectedTileLbl);
				
//		testBtn = new Button(new Vector2i(12, 310), new Vector2i(120, 40), new IHUDActionListener() {
//			public void execute() {
//				System.out.println("Button pressed");
//			}
//		});
//		testBtn.setHUDButtonListener(new HUDButtonListener() {
//			public void pressed(Button testBtn) {
//				super.pressed(testBtn);
//				testBtn.executeAction();
//				testBtn.ignoreNextPress();
//			}
//		});
//		testBtn.setText("A Button");
//		panel.addComponent(testBtn);
	}
	
	public void update() {		
		if(walking) animatedSprite.update();
		else animatedSprite.setFrame(0);
		
		//keep player at whole numbers else it messes up the scrolling :(
		double speed = 1.0;
		if(clickRate > 0) clickRate--;
		if(fireRate > 0) fireRate--;
		double xa = 0, ya = 0;
		if (animate < 5000) animate++;
		else animate = 0;
		
		if(input.up) {
			ya-=speed;
			animatedSprite = defaultGirlPlayer_up;
		}
		if(input.down) {
			ya+=speed;
			animatedSprite = defaultGirlPlayer_down;
		}
		if(input.left) {
			xa-=speed;
			animatedSprite = defaultGirlPlayer_left;
		}
		if(input.right) {
			xa+=speed;
			animatedSprite = defaultGirlPlayer_right;
		}
		
		clear();
		updateFireProjectile();
		breakTileItem();
		
		
		if (xa != 0 || ya != 0)  {
			movePlayer(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		
		playerName.setText(getName());
		//expBar.setProgress(experience / 100.0);
	}
	
	private void movePlayer(double xpixel, double ypixel) {
		if (xpixel != 0 && ypixel != 0) {
			move(xpixel, 0);
			move(0, ypixel);
			return;
		}
		
		if (xpixel > 0 ) direction = Direction.RIGHT; 
		if (xpixel < 0 ) direction = Direction.LEFT; 
		if (ypixel > 0 ) direction = Direction.DOWN;
		if (ypixel < 0 ) direction = Direction.UP;
		
		while (xpixel != 0) {
			if (Math.abs(xpixel) > 1) {
				if (!collision(abs(xpixel), ypixel)) {
					this.x += abs(xpixel);
				}
				xpixel -= abs(xpixel);
			} else {
				if (!collision(abs(xpixel), ypixel)) {
					this.x += xpixel;
				}
				xpixel = 0;
			}
		}
		
		while (ypixel != 0) {
			if (Math.abs(ypixel) > 1) {
				if (!collision(xpixel, abs(ypixel))) {
					this.y += abs(ypixel);
				}
				ypixel -= abs(ypixel);
			} else {
				if (!collision(xpixel, abs(ypixel))) {
					this.y += ypixel;
				}
				ypixel = 0;
			}
		}
	}
	private int abs(double value) {
		if(value < 0) return -1;
		else return 1;
	}
	private boolean collision(double xpixel, double ypixel) {
		boolean solid = false;
		for(int corner = 0; corner < 4; corner++) {
				double xt = ((x + xpixel) - corner % 2 * 24 + 4)/16;
				double yt = ((y + ypixel) - corner / 2 * 24 + 20)/16;
				int ix = (int) Math.ceil(xt);
				int iy = (int) Math.ceil(yt);
				if(corner % 2 == 0) ix =(int) Math.floor(xt);
				if(corner / 2 == 0) iy =(int) Math.floor(yt);
				if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(Screen screen) {
		sprite = animatedSprite.getSprite();
		screen.renderMob((int)(x - 24), (int)(y - 24), this);
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()) level.getProjectiles().remove(i);		
		}
	}
	
	private void updateFireProjectile() {
		if(Main.State == STATE.PLAY) {
			if(Mouse.getX() > 780) return;
			if(Mouse.getMouseButton() == 3 && fireRate <= 0) {
				double dx = Mouse.getX() - Main.getWidthScaled() / 2;  	//adjacent
				double dy = Mouse.getY() - Main.getHeightScaled() / 2;	//opposite
				double fireDirection = Math.atan2(dy, dx);
				fireProjectile(x, y, fireDirection);
				fireRate = ArrowProjectile.FIRE_RATE;	
			}
		} 
	}
	private void breakTileItem() {
		if(Main.State == STATE.PLAY) {
			if(Mouse.getX() > 780) return;
			if(Mouse.getMouseButton() == 1 && clickRate <= 0) {
				int xMouse = (int)((getX() - Main.getRawWidth() / 2) + Mouse.getX() / Main.getRawScale()) >> 4;
				int yMouse = (int)((getY() - Main.getRawHeight() / 2) + Mouse.getY() / Main.getRawScale()) >> 4;
				selectedTile = level.getTile(xMouse, yMouse);
				clickRate = Mouse.CLICK_RATE;
				int xPlayer = (int)level.getPlayerAt(0).getX() >> 4;	
				int yPlayer = (int)level.getPlayerAt(0).getY() >> 4;
				int xDist = Math.abs(xPlayer - xMouse);
				int yDist = Math.abs(yPlayer - yMouse);
				if (xDist < 3 && yDist < 3) {
					spawnBrokenTileItem(xMouse, yMouse);
				}
			}
		}
	}
	
	private void spawnBrokenTileItem(int xMouse, int yMouse) {
		selectedTileLbl.text = setSelectedTileLbl(selectedTile.name);
		if(selectedTile.breakable() == true) {
			int tileId = selectedTile.getTileId();
			//replaces the selected tile broken with a replacement
			level.setTile(xMouse, yMouse, selectedTile.getReplacementTile()); 
			//adds in the item from the tile id, gets removed after a set time limit
			if (tileId == 32) level.add(new ItemRock(xMouse, yMouse, 500));
		}
	}
	public int getCash() {
		return cash;
	}
	
	public void addCash(int amount) {
		cash += amount;
	}
	public void subtractCash(int amount) {
		cash -= amount;
	}
	
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Player.name = name;
	}

	public String getSelectedTileLbl() {
		return selectedTileLbl.text;
	}
	public String setSelectedTileLbl(String tileName) {
		return selectedTileLbl.text = tileName;
	}
	
}
