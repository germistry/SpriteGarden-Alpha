package com.germistry.spriteGarden;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.germistry.spriteGarden.entity.mob.Player;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.graphics.gui.hud.HUDManager;
import com.germistry.spriteGarden.graphics.gui.menu.MainMenu;
import com.germistry.spriteGarden.graphics.gui.menu.NewGameMenu;
import com.germistry.spriteGarden.graphics.gui.menu.PauseMenu;
import com.germistry.spriteGarden.input.Keyboard;
import com.germistry.spriteGarden.input.Mouse;
import com.germistry.spriteGarden.level.Level;
import com.germistry.spriteGarden.level.TileCoord;


public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int width = 340 - 80;
	private static int height = 192;
	private static int scale = 3;
	
	public static String title = "Sprite Garden";
	
	private Thread gameThread;
	private JFrame frame;
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
		SAVEGAME,  //TODO Save Game Menu 
		LOADGAME,  //TODO Load Game Menu
		HOWTOPLAY, //TODO How to Play Menu
		ABOUT,		//TODO About Menu 
		PLAY
	}

	public static STATE State = STATE.MAINMENU;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Main() {
		Dimension size = new Dimension(width * scale + (80 * 3), height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		
		key = new Keyboard();
		
		//game entry point
		setMenus();
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
		player = new Player("", playerSpawn.getX(), playerSpawn.getY(), key); 
		level.add(player);
		
	}
	
	private void setMenus() {
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
		switch(State) {
		case MAINMENU:
			mainMenu.update();
			break;
		case PAUSEMENU:
			pauseMenu.update();
			break;
		case NEWGAMEMENU:
			newGameMenu.update();
			break;
		case SAVEGAME:
			
			break;
		case LOADGAME:
			level = Level.loadedLevel;	
			TileCoord playerSpawn = new TileCoord(64, 64);  
			player = new Player("", playerSpawn.getX(), playerSpawn.getY(), key); 
			level.add(player);
			State = STATE.PLAY;
			break;
		case HOWTOPLAY:
			
			break;
		case ABOUT:
			
			break;
		case PLAY:
			if(!key.escape) {
				level.update();
				hud.update();
			} else {
				State = STATE.PAUSEMENU;
			}	
			break;
		}	
		//System.out.println(State);
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
		
		switch(State) {
		case MAINMENU:
			mainMenu.render(screen);
			g.dispose();
			bs.show();
			break;
		case PAUSEMENU:
			pauseMenu.render(screen);
			g.dispose();
			bs.show();
			break;
		case NEWGAMEMENU:
			newGameMenu.render(screen);
			g.dispose();
			bs.show();
			break;
		case SAVEGAME:
			
			break;
		case LOADGAME:
			
			break;
		case HOWTOPLAY:
			
			break;
		case ABOUT:
			
			break;
		case PLAY:
			if(!key.escape) {
				double xScroll = player.getX() - screen.width / 2;
				double yScroll = player.getY() - screen.height / 2;
				level.setScroll((int)xScroll, (int)yScroll);
				level.render(screen);
				for(int i = 0; i < pixels.length; i++) {
					pixels[i] = screen.pixels[i];
				}
				g.drawImage(image, 0, 0, width * scale, height * scale, null);
				hud.render(g);
				g.dispose();
				bs.show();
			} else {
				State = STATE.PAUSEMENU;
				g.dispose();
				bs.show();
			}	
			break;
		}	
		
	}
	
	public static void main(String[] args) {
		Main  game = new Main(); 
		game.frame.setResizable(false);
		game.frame.setTitle(Main.title);
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(game, BorderLayout.CENTER);
		game.frame.setContentPane(gamePanel);
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
	
	public Level getLevel() {
		return level;
	}

}
