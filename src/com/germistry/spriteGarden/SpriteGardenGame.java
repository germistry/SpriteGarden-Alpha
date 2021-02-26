package com.germistry.spriteGarden;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.germistry.spriteGarden.entity.mob.Player;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.gui.MainMenu;
import com.germistry.spriteGarden.graphics.gui.NewGameMenu;
import com.germistry.spriteGarden.graphics.gui.PauseMenu;
import com.germistry.spriteGarden.graphics.hud.HUDManager;
import com.germistry.spriteGarden.input.Keyboard;
import com.germistry.spriteGarden.input.Mouse;
import com.germistry.spriteGarden.level.Level;
import com.germistry.spriteGarden.level.TileCoord;


public class SpriteGardenGame extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int width = 340 - 80;
	private static int height = 192;
	private static int scale = 3;
	
	public static String title = "Sprite Garden";
	
	private Thread gameThread;
	private JFrame frame;;
	private Screen screen;
	private Keyboard key;
	
	private Level level;
	private static HUDManager hud;
	private Player player;
	private MainMenu mainMenu;
	private NewGameMenu newGameMenu;
	private PauseMenu pauseMenu;
	
	private boolean running = false;
	
	public static enum STATE {
		MAINMENU,
		PAUSEMENU,
		NEWGAMEMENU, 
		LOADGAME,
		HOWTOPLAY,
		ABOUT,
		PLAY
	}

	public static STATE State = STATE.MAINMENU;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public SpriteGardenGame() {
		Dimension size = new Dimension(width * scale + (80 * 3), height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		
		//game entry point
		setStartMenu();
		//new game lands on this level
		initSpawnLevel();
		
		addKeyListener(key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	private void initSpawnLevel() {
		hud = new HUDManager();
		level = Level.spawn;	
		TileCoord playerSpawn = new TileCoord(64, 64);  
		player = new Player("Germistry", playerSpawn.getX(), playerSpawn.getY(), key); 
		level.add(player);
		
	}
	
	private void setStartMenu() {
		State = STATE.MAINMENU; 
		mainMenu = new MainMenu(key);
		pauseMenu = new PauseMenu(key);
		newGameMenu = new NewGameMenu(key);
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "SpriteGarden");
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}	
	
	public void update() {
		key.update();
		if(State == STATE.MAINMENU) {
			mainMenu.update();
		}
		if(State == STATE.PAUSEMENU) {
			newGameMenu.update();
		}
		if(State == STATE.NEWGAMEMENU) {
			newGameMenu.update();
		}
		if(State == STATE.PLAY) {
			if(!key.escape) {
				level.update();
				hud.update();
			} else {
				State = STATE.PAUSEMENU;
			}	
		}
		System.out.println(State);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		
		Graphics g = bs.getDrawGraphics();
		screen.graphics(g);
		
		if(State == STATE.MAINMENU) {
			mainMenu.render(screen);
		}
		if(State == STATE.PAUSEMENU) {
			pauseMenu.render(screen);
		}
		if(State == STATE.NEWGAMEMENU) {
			newGameMenu.render(screen);
		}
		if(State == STATE.LOADGAME) {
			
		}
		if(State == STATE.HOWTOPLAY) {
			
		}
		if(State == STATE.ABOUT) {
			
		}
		if(State == STATE.PLAY && !key.escape) {
			double xScroll = player.getX() - screen.width / 2;
			double yScroll = player.getY() - screen.height / 2;
			level.setScroll((int)xScroll, (int)yScroll);
			level.render(screen);
			for(int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}
			g.drawImage(image, 0, 0, width * scale, height * scale, null);
			hud.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		SpriteGardenGame  game = new SpriteGardenGame(); 
		game.frame.setResizable(false);
		game.frame.setTitle(SpriteGardenGame.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);  //centres frame
		game.frame.setVisible(true);
		
		game.start();
		game.requestFocus();
		
	}
	
	public static int getWidthScaled() {
		return width * scale;
	}
	public static int getHeightScaled() {
		return height * scale;
	}
	public static int getRawWidth() {
		return width;
	}
	public static int getRawHeight() {
		return height;
	}
	public static int getRawScale() {
		return scale;
	}
	public static HUDManager getHUDManager() {
		return hud;
	}

}
