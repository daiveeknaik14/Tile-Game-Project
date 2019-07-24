package dev.codenmore.tilegame.worlds;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.EntityManager;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Rock;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.items.ItemManager;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private int treex=32,treey=32,rockx=32,rocky=32;
	//Entities
	private EntityManager entityManager;
	// Item
	private ItemManager itemManager;
	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		// Temporary entity code!
		entityManager.addEntity(new Tree(handler, 164, 110));
		
		entityManager.addEntity(new Rock(handler, 232, 50));
		entityManager.addEntity(new Rock(handler, 264, 50));
		entityManager.addEntity(new Tree(handler, 360, -20));
		entityManager.addEntity(new Rock(handler, 330, 300));
		entityManager.addEntity(new Tree(handler, 330, 332));
		entityManager.addEntity(new Tree(handler, treex*2, treey*10));
		entityManager.addEntity(new Rock(handler, rockx*2, rocky*16));
		entityManager.addEntity(new Rock(handler, rockx*2, rocky*24));
		entityManager.addEntity(new Tree(handler, rockx*2, rocky*27));
		entityManager.addEntity(new Rock(handler, rockx*8, rocky*26));
		entityManager.addEntity(new Rock(handler, rockx*15, rocky*26));
		entityManager.addEntity(new Rock(handler, rockx*24, rocky*15));
		entityManager.addEntity(new Tree(handler, rockx*24, rocky*13));
		entityManager.addEntity(new Tree(handler, rockx*16, rocky*26));
		entityManager.addEntity(new Rock(handler, rockx*14, rocky*26));
		entityManager.addEntity(new Tree(handler, 625, 325));
		entityManager.addEntity(new Rock(handler, rockx*24, rocky*23));
		entityManager.addEntity(new Tree(handler, rockx*26, rocky*26));
		entityManager.addEntity(new Rock(handler, rockx*27, rocky*26));
		entityManager.addEntity(new Tree(handler, rockx*28, rocky*26));
		//Populate
		entityManager.addEntity(new Rock(handler, rockx*23, rocky*9));
		entityManager.addEntity(new Tree(handler, rockx*13, rocky*17));
		entityManager.addEntity(new Rock(handler, rockx*19, rocky*10));
		entityManager.addEntity(new Tree(handler, rockx*5, rocky*26));
		entityManager.addEntity(new Rock(handler, rockx*26, rocky*12));
		entityManager.addEntity(new Tree(handler, rockx*15, rocky*8));
		entityManager.addEntity(new Tree(handler, rockx*20, rocky*7));
		entityManager.addEntity(new Rock(handler, rockx*21, rocky*14));
		entityManager.addEntity(new Rock(handler, rockx*26, rocky*10));
		entityManager.addEntity(new Tree(handler, rockx*24, rocky*12));
		
		
		loadWorld(path);
		
		//entityManager.getPlayer().setX(spawnX);
		//entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick(){
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
}








