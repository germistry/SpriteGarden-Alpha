package com.germistry.spriteGarden.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.entity.items.Item;
import com.germistry.spriteGarden.entity.mob.Player;
import com.germistry.spriteGarden.entity.particle.Particle;
import com.germistry.spriteGarden.entity.projectile.Projectile;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.level.tile.Tile;
import com.germistry.spriteGarden.utils.Vector2i;

public class Level {

	protected int width, height;
	//tiles Int for random level & loading maps saved to text files
	protected int[] tilesInt;
	//identifier for level colour values from the level map
	protected int[] tiles;
	protected int levelId;
	protected final Random random = new Random();
	private int xScroll, yScroll;
	
	private Comparator<TileNode> nodeSorter = new Comparator<TileNode>() {
		public int compare(TileNode node0, TileNode node1) {
			if(node1.fCost < node0.fCost) return 1;
			if(node1.fCost > node0.fCost) return -1;
			return 0;
		}
	}; 
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		public int compare(Entity entityA, Entity entityB) {
			if(entityA.getY() < entityB.getY()) return -1;
			else return 1;
		}
	};
	
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Player> players = new ArrayList<Player>();
	private List<Item> items = new ArrayList<Item>();
	
	public static Level spawn = new SpawnLevel("/levelmaps/spawnLevel.png");
	public static Level mainMenu = new MainMenuLevel("/levelmaps/mainMenuLevel.png");
	public static Level loadedLevel = new LoadedLevel("test2");
	
	//This constructor used for loading saved maps from text files and also used for the random level that is depreciated. 
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height]; //used for the random level & loadedLevel
		
	}
	//This constructor used for loading default maps from images.  
	public Level(String path) {
		loadLevel(path);
	}
	//generate was only used for the random level
	protected void generateLevel() {
	}
	
	protected void loadLevel(String path) {
	}
		
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
		entities.sort(renderSorter);
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update();
		}
	}
	
	
	public void setScroll(int xScroll, int yScroll) {
		this.xScroll = xScroll;
		this.yScroll = yScroll;
	}
	
	public int getxScroll() {
		return xScroll;
	}

	public int getyScroll() {
		return yScroll;
	}

	public void render(Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(screen);
		}
		remove();	
	}
	
	public void add(Entity entity) {
		entity.init(this);
		if (entity instanceof Particle) {
			particles.add((Particle) entity);
		} else if (entity instanceof Projectile) {
			projectiles.add((Projectile) entity);
		} else if (entity instanceof Player) {
			players.add((Player) entity);
		} else if (entity instanceof Item) {
			items.add((Item) entity);
		} else {
			entities.add(entity); 
		}
	}
	
	public void addPlayer(Player player) {
		player.init(this);
		players.add(player);
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).isRemoved()) players.remove(i);
		}
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).isRemoved()) items.remove(i);
		}
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for(int corner = 0; corner < 4; corner++) {
			int xt = (x - corner % 2 * size + xOffset) >> 4;
			int yt = (y - corner / 2 * size + yOffset) >> 4;
			//System.out.println(x + ", " + y + "/ " + size + "/ " + xOffset + ", " + yOffset);
			if (getTile(xt, yt).solid()) solid = true;
		}
		 
		return solid;
	}
	//The A* search algorithm for working out best path. Uses private methods vectorInList & getDistance, 
	//overriden equals method in Vector2i and the Tile Node Comparator.  
	public List<TileNode> findPath(Vector2i start, Vector2i finish) {
		List<TileNode> openList = new ArrayList<TileNode>();
		List<TileNode> closedList = new ArrayList<TileNode>();
		TileNode current = new TileNode(start, null, 0, getDistance(start, finish));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);	
			if(current.tile.equals(finish)) {
				List<TileNode> path = new ArrayList<TileNode>(); 
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile tileAt = getTile(x + xi, y + yi);
				if (tileAt == null) continue;
				if (tileAt.solid()) continue;
				Vector2i tAt = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + getDistance(current.tile, tAt);
				double hCost = getDistance(tAt, finish);
				TileNode node = new TileNode(tAt, current, gCost, hCost);
				if (vectorInList(closedList, tAt) && gCost >= node.gCost) continue;
				if (!vectorInList(openList, tAt) || gCost < node.gCost) openList.add(node);
			}
		}
		closedList.clear();
		return null;
	}
	
	private boolean vectorInList(List<TileNode> list, Vector2i vector) {
		for (TileNode tn : list) {
			if (tn.tile.equals(vector)) return true;
		}
		return false;
	}
	
	private double getDistance(Vector2i start, Vector2i finish) {
		double dx = start.getX() - finish.getX();
		double dy = start.getY() - finish.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public List<Item> getItems() {
		return items;
	}
	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Player getClientPlayer() {
		return (Player) players.get(0);
	}
	
	public int getLevelId() {
		return levelId;
	}
	
	public List<Entity> getEntities(Entity entity, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int entityX = (int)entity.getX();
		int entityY = (int)entity.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			int x = (int)e.getX();
			int y = (int)e.getY();
			int dx = Math.abs(x - entityX);
			int dy = Math.abs(y - entityY);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius) result.add(e);
		}
		return result;
	}
	//
	public List<Player> getPlayers(Entity entity, int radius) {
		List<Player> result = new ArrayList<Player>();
		int entityX = (int)entity.getX();
		int entityY = (int)entity.getY();
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			int x = (int)p.getX();
			int y = (int)p.getY();
			int dx = Math.abs(x - entityX);
			int dy = Math.abs(y - entityY);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius) result.add(p);
		}
		return result;
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		switch(tiles[x + y * width]) {
			case Tile.col_gardenGateTile:
			case Tile.id_gardenGateTile:
				return Tile.gardenGateTile;
			case Tile.col_plainLightSand:
			case Tile.id_plainLightSand:
				return Tile.plainLightSand;
			case Tile.col_detailLightSand:
			case Tile.id_detailLightSand:
				return Tile.detailLightSand;
			case Tile.col_pittedLightSand: 
			case Tile.id_pittedLightSand:
				return Tile.pittedLightSand;
			case Tile.col_plainDarkGreenGrass:
			case Tile.id_plainDarkGreenGrass:
				return Tile.plainDarkGreenGrass;
			case Tile.col_detailDarkGreenGrass:
			case Tile.id_detailDarkGreenGrass:
				return Tile.detailDarkGreenGrass;
			case Tile.col_weedDarkGreenGrass:
			case Tile.id_weedDarkGreenGrass:	
				return Tile.weedDarkGreenGrass;
			case Tile.col_plainMidGreenGrass:
			case Tile.id_plainMidGreenGrass:
				return Tile.plainMidGreenGrass;
			case Tile.col_detailMidGreenGrass:
			case Tile.id_detailMidGreenGrass:	
				return Tile.detailMidGreenGrass;
			case Tile.col_weedMidGreenGrass: 
			case Tile.id_weedMidGreenGrass:
				return Tile.weedMidGreenGrass;
			case Tile.col_dirtPatch: 
			case Tile.id_dirtPatch:
				return Tile.dirtPatch;
			case Tile.col_detailDirtPatch: 
			case Tile.id_detailDirtPatch: 
				return Tile.detailDirtPatch;
			case Tile.col_pittedDirtPatch: 
			case Tile.id_pittedDirtPatch:
				return Tile.pittedDirtPatch;
			case Tile.col_weedDirtPatch: 
			case Tile.id_weedDirtPatch:
				return Tile.weedDirtPatch;
			case Tile.col_fertileDirt: 
			case Tile.id_fertileDirt:
				return Tile.fertileDirt;
			case Tile.col_midGrGrassDirtEdgeTop:
			case Tile.id_midGrGrassDirtEdgeTop:
				return Tile.midGrGrassDirtEdgeTop;
			case Tile.col_midGrGrassDirtEdgeBase:
			case Tile.id_midGrGrassDirtEdgeBase:	
				return Tile.midGrGrassDirtEdgeBase;
			case Tile.col_midGrGrassDirtEdgeLeft:
			case Tile.id_midGrGrassDirtEdgeLeft:	
				return Tile.midGrGrassDirtEdgeLeft;
			case Tile.col_midGrGrassDirtEdgeRight:
			case Tile.id_midGrGrassDirtEdgeRight:	
				return Tile.midGrGrassDirtEdgeRight;
			case Tile.col_midGrGrassFertDirtEdgeLeft:
			case Tile.id_midGrGrassFertDirtEdgeLeft:	
				return Tile.midGrGrassFertDirtEdgeLeft;
			case Tile.col_midGrGrassFertDirtEdgeRight:
			case Tile.id_midGrGrassFertDirtEdgeRight:	
				return Tile.midGrGrassFertDirtEdgeRight;
			case Tile.col_midGrGrassDirtCornerTL:
			case Tile.id_midGrGrassDirtCornerTL:	
				return Tile.midGrGrassDirtCornerTL;
			case Tile.col_midGrGrassDirtCornerBL:
			case Tile.id_midGrGrassDirtCornerBL:	
				return Tile.midGrGrassDirtCornerBL;
			case Tile.col_midGrGrassDirtCornerTR:
			case Tile.id_midGrGrassDirtCornerTR:	
				return Tile.midGrGrassDirtCornerTR;
			case Tile.col_midGrGrassDirtCornerBR:
			case Tile.id_midGrGrassDirtCornerBR:	
				return Tile.midGrGrassDirtCornerBR;
			case Tile.col_midGrGrassLSandEdgeTop:
			case Tile.id_midGrGrassLSandEdgeTop:	
				return Tile.midGrGrassLSandEdgeTop;
			case Tile.col_midGrGrassLSandEdgeBase:
			case Tile.id_midGrGrassLSandEdgeBase:	
				return Tile.midGrGrassLSandEdgeBase;
			case Tile.col_midGrGrassLSandEdgeLeft:
			case Tile.id_midGrGrassLSandEdgeLeft:	
				return Tile.midGrGrassLSandEdgeLeft;
			case Tile.col_midGrGrassLSandEdgeRight:
			case Tile.id_midGrGrassLSandEdgeRight:	
				return Tile.midGrGrassLSandEdgeRight;
			case Tile.col_midGrGrassLSandCornerTL:
			case Tile.id_midGrGrassLSandCornerTL:	
				return Tile.midGrGrassLSandCornerTL;
			case Tile.col_midGrGrassLSandCornerBL:
			case Tile.id_midGrGrassLSandCornerBL:	
				return Tile.midGrGrassLSandCornerBL;
			case Tile.col_midGrGrassLSandCornerTR:
			case Tile.id_midGrGrassLSandCornerTR:	
				return Tile.midGrGrassLSandCornerTR;
			case Tile.col_midGrGrassLSandCornerBR:
			case Tile.id_midGrGrassLSandCornerBR:	
				return Tile.midGrGrassLSandCornerBR;
			case Tile.col_lgtSandMidGrGrassCornerTL:
			case Tile.id_lightSandMidGrGrassCornerTL:	
				return Tile.lgtSandMidGrGrassCornerTL;
			case Tile.col_lgtSandMidGrGrassCornerBL:
			case Tile.id_lightSandMidGrGrassCornerBL:	
				return Tile.lgtSandMidGrGrassCornerBL;
			case Tile.col_lgtSandMidGrGrassCornerTR:
			case Tile.id_lightSandMidGrGrassCornerTR:	
				return Tile.lgtSandMidGrGrassCornerTR;
			case Tile.col_lgtSandMidGrGrassCornerBR:
			case Tile.id_lightSandMidGrGrassCornerBR:	
				return Tile.lgtSandMidGrGrassCornerBR;
			case Tile.col_pinkTulipMidGrGrass:
			case Tile.id_pinkTulipMidGrGrass:	
				return Tile.pinkTulipMidGrGrass;
			case Tile.col_orangeTulipMidGrGrass:
			case Tile.id_orangeTulipMidGrGrass:
				return Tile.orangeTulipMidGrGrass;
			case Tile.col_purpleTulipMidGrGrass:
			case Tile.id_purpleTulipMidGrGrass:
				return Tile.purpleTulipMidGrGrass;
			case Tile.col_whiteDaisyMidGrGrass:
			case Tile.id_whiteDaisyMidGrGrass:
				return Tile.whiteDaisyMidGrGrass;
			case Tile.col_rockMidGrGrass:
			case Tile.id_RockMidGrGrass:	
				return Tile.rockMidGrGrass;
			case Tile.col_smallPlant1MidGrGrass:
			case Tile.id_SmallPlant1MidGrGrass:	
				return Tile.smallPlant1MidGrGrass;
			case Tile.col_smallPlant2MidGrGrass:
			case Tile.id_SmallPlant2MidGrGrass:
				return Tile.smallPlant2MidGrGrass;
			case Tile.col_wallTile1:
			case Tile.id_wallTile1:	
				return Tile.wallTile1;
			case Tile.col_wallTile2:
			case Tile.id_wallTile2:	
				return Tile.wallTile2;
			case Tile.col_wallTile3:
			case Tile.id_wallTile3:		
				return Tile.wallTile3;
			case Tile.col_wallTile4:
			case Tile.id_wallTile4:	
				return Tile.wallTile4;
		}
		return Tile.voidTile;
	}
	
	public void setTile(int x, int y, Tile tile) {
		tiles[x + y * width] = tile.getMapColour();
	}
	public void setTileById(int x, int y, int tileId) {
		getTile(x,y).getTileId();
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getTileId(int x, int y) {
		return getTile(x, y).getTileId();
	}
		
}
