package com.germistry.spriteGarden.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.germistry.spriteGarden.entity.Entity;
import com.germistry.spriteGarden.entity.mob.Mob;
import com.germistry.spriteGarden.entity.mob.Player;
import com.germistry.spriteGarden.entity.particle.Particle;
import com.germistry.spriteGarden.entity.projectile.Projectile;
import com.germistry.spriteGarden.graphics.Screen;
import com.germistry.spriteGarden.level.tile.Tile;
import com.germistry.spriteGarden.utils.Vector2i;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	//identifier for level colour values from the level map
	protected int[] tiles;
	protected final Random random = new Random();
	private int xScroll, yScroll;
	
	private Comparator<TileNode> nodeSorter = new Comparator<TileNode>() {
		@Override
		public int compare(TileNode node0, TileNode node1) {
			if(node1.fCost < node0.fCost) return 1;
			if(node1.fCost > node0.fCost) return -1;
			return 0;
		}
	};
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Player> players = new ArrayList<Player>();
	
	public static Level spawn = new SpawnLevel("/levelmaps/spawnLevel.png");
	public static Level mainMenu = new MainMenuLevel("/levelmaps/mainMenuLevel.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	protected void generateLevel() {
	}
	
	protected void loadLevel(String path) {
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
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
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for(int corner = 0; corner < 4; corner++) {
			int xt = (x - corner % 2 * size + xOffset) >> 4;
			int yt = (y - corner / 2 * size + yOffset) >> 4;
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
	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Player getClientPlayer() {
		return (Player) players.get(0);
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
	//TODO change this get method to make more efficient, perhaps a switch, but keep static colour map in tiles as that is handy
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_gardenGateTile) return Tile.gardenGateTile;
		if (tiles[x + y * width] == Tile.col_plainLightSand) return Tile.plainLightSand;
		if (tiles[x + y * width] == Tile.col_detailLightSand) return Tile.detailLightSand;
		if (tiles[x + y * width] == Tile.col_pittedLightSand) return Tile.pittedLightSand;
		
		if (tiles[x + y * width] == Tile.col_plainDarkGreenGrass) return Tile.plainDarkGreenGrass;
		if (tiles[x + y * width] == Tile.col_detailDarkGreenGrass) return Tile.detailDarkGreenGrass;
		if (tiles[x + y * width] == Tile.col_weedDarkGreenGrass) return Tile.weedDarkGreenGrass;
		
		if (tiles[x + y * width] == Tile.col_plainMidGreenGrass) return Tile.plainMidGreenGrass;
		if (tiles[x + y * width] == Tile.col_detailMidGreenGrass) return Tile.detailMidGreenGrass;
		if (tiles[x + y * width] == Tile.col_weedMidGreenGrass) return Tile.weedMidGreenGrass;
		
		if (tiles[x + y * width] == Tile.col_dirtPatch) return Tile.dirtPatch;
		if (tiles[x + y * width] == Tile.col_detailDirtPatch) return Tile.detailDirtPatch;
		if (tiles[x + y * width] == Tile.col_pittedDirtPatch) return Tile.pittedDirtPatch;
		if (tiles[x + y * width] == Tile.col_weedDirtPatch) return Tile.weedDirtPatch;
		if (tiles[x + y * width] == Tile.col_fertileDirt) return Tile.fertileDirt;
		
		if (tiles[x + y * width] == Tile.col_midGrGrassDirtEdgeTop) return Tile.midGrGrassDirtEdgeTop;
		if (tiles[x + y * width] == Tile.col_midGrGrassDirtEdgeBase) return Tile.midGrGrassDirtEdgeBase;
		if (tiles[x + y * width] == Tile.col_midGrGrassDirtEdgeLeft) return Tile.midGrGrassDirtEdgeLeft;
		if (tiles[x + y * width] == Tile.col_midGrGrassDirtEdgeRight) return Tile.midGrGrassDirtEdgeRight;
		if (tiles[x + y * width] == Tile.col_midGrGrassFertDirtEdgeLeft) return Tile.midGrGrassFertDirtEdgeLeft;
		if (tiles[x + y * width] == Tile.col_midGrGrassFertDirtEdgeRight) return Tile.midGrGrassFertDirtEdgeRight;
		if (tiles[x + y * width] == Tile.col_midGrGrassDirtCornerTL) return Tile.midGrGrassDirtCornerTL;
		if (tiles[x + y * width] == Tile.col_midGrGrassDirtCornerBL) return Tile.midGrGrassDirtCornerBL;
		if (tiles[x + y * width] == Tile.col_midGrGrassDirtCornerTR) return Tile.midGrGrassDirtCornerTR;
		if (tiles[x + y * width] == Tile.col_midGrGrassDirtCornerBR) return Tile.midGrGrassDirtCornerBR;
		
		if (tiles[x + y * width] == Tile.col_midGrGrassLSandEdgeTop) return Tile.midGrGrassLSandEdgeTop;
		if (tiles[x + y * width] == Tile.col_midGrGrassLSandEdgeBase) return Tile.midGrGrassLSandEdgeBase;
		if (tiles[x + y * width] == Tile.col_midGrGrassLSandEdgeLeft) return Tile.midGrGrassLSandEdgeLeft;
		if (tiles[x + y * width] == Tile.col_midGrGrassLSandEdgeRight) return Tile.midGrGrassLSandEdgeRight;
		if (tiles[x + y * width] == Tile.col_midGrGrassLSandCornerTL) return Tile.midGrGrassLSandCornerTL;
		if (tiles[x + y * width] == Tile.col_midGrGrassLSandCornerBL) return Tile.midGrGrassLSandCornerBL;
		if (tiles[x + y * width] == Tile.col_midGrGrassLSandCornerTR) return Tile.midGrGrassLSandCornerTR;
		if (tiles[x + y * width] == Tile.col_midGrGrassLSandCornerBR) return Tile.midGrGrassLSandCornerBR;
		if (tiles[x + y * width] == Tile.col_lgtSandMidGrGrassCornerTL) return Tile.lgtSandMidGrGrassCornerTL;
		if (tiles[x + y * width] == Tile.col_lgtSandMidGrGrassCornerBL) return Tile.lgtSandMidGrGrassCornerBL;
		if (tiles[x + y * width] == Tile.col_lgtSandMidGrGrassCornerTR) return Tile.lgtSandMidGrGrassCornerTR;
		if (tiles[x + y * width] == Tile.col_lgtSandMidGrGrassCornerBR) return Tile.lgtSandMidGrGrassCornerBR;
		
		if (tiles[x + y * width] == Tile.col_pinkTulipMidGrGrass) return Tile.pinkTulipMidGrGrass;
		if (tiles[x + y * width] == Tile.col_orangeTulipMidGrGrass) return Tile.orangeTulipMidGrGrass;
		if (tiles[x + y * width] == Tile.col_purpleTulipMidGrGrass) return Tile.purpleTulipMidGrGrass;
		if (tiles[x + y * width] == Tile.col_whiteDaisyMidGrGrass) return Tile.whiteDaisyMidGrGrass;
		if (tiles[x + y * width] == Tile.col_rockMidGrGrass) return Tile.rockMidGrGrass;
		if (tiles[x + y * width] == Tile.col_smallPlant1MidGrGrass) return Tile.smallPlant1MidGrGrass;
		if (tiles[x + y * width] == Tile.col_smallPlant2MidGrGrass) return Tile.smallPlant2MidGrGrass;
		
		if (tiles[x + y * width] == Tile.col_wallTile1) return Tile.wallTile1;
		if (tiles[x + y * width] == Tile.col_wallTile2) return Tile.wallTile2;
		if (tiles[x + y * width] == Tile.col_wallTile3) return Tile.wallTile3;
		if (tiles[x + y * width] == Tile.col_wallTile4) return Tile.wallTile4;
				
		return Tile.voidTile;
	}
	
	public void setTile(int x, int y, Tile tile) {
		tiles[x + y * width] = tile.getMapColour();
	}
}
