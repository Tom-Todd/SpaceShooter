package org.msquirrel.SpaceShooter.TileMap;

import java.util.Random;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.EnemyBase;
import org.msquirrel.SpaceShooter.TileMap.Tiles.Tile;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileDoor;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileExit;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileGround;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileSafeZone;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileSpace;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileWall;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Map implements TileBasedMap{
	public Tile[][] map;
	private Camera cam;
	private static int WIDTH;
	private static int HEIGHT;
	public static int TILE_SIZE = 32;
	private Image mapImage;
	private int currentMap;
	
	public Map(Camera cam) throws SlickException{
		this.cam = cam;
		loadMap(1);
	}
	
	public void loadMap(int mapNumber) throws SlickException{
		Bitmap loadmap = mapLoader.loadBitmap("res/map.png");
		mapImage = new Image("res/lv1Map.png");
		currentMap = mapNumber;
		if(mapNumber == 0){
			mapImage = new Image("res/lv1Map.png");
			loadmap = mapLoader.loadBitmap("res/map.png");
		}
		if(mapNumber == 1){
			mapImage = new Image("res/lv2Map.png");
			loadmap = mapLoader.loadBitmap("res/map2.png");
		}
		
		WIDTH = loadmap.width;
		HEIGHT = loadmap.height;
		map = new Tile[WIDTH][HEIGHT];
		for(int x = 0;x < WIDTH; x++){
			for(int y = 0;y < HEIGHT; y++){
				map[x][y]= new TileGround(x, y);
				switch (loadmap.pixels[(y*WIDTH)+x]){
					case 0xFF999999:
					{
						map[x][y] = new TileWall(x,y);
					}
					break;
					case 0xFF000000:
					{
						map[x][y] = new TileSpace(x,y);
					}
					break;
					case 0xFF333333:
					{
						map[x][y] = new TileDoor(x,y);
					}
					break;
					case 0xFFFF6699:
					{
						map[x][y] = new TileSafeZone(x,y);
					}
					break;
					case 0xFFB380B3:
					{
						map[x][y] = new TileExit(x,y);
					}
				}
			}
		}	
	}
	
	public void draw(Graphics g){
		for(int x = 0;x < WIDTH; x++){
			for(int y = 0;y < HEIGHT; y++){
				map[x][y].draw(g);
			}
		}
		mapImage.draw();
	}
	
	public boolean blocked(float nextX, float nextY, float width, float height) {
		int x = (int) (((nextX))/32);
		int y = (int) (((nextY))/32);
		if(x > -1 && x < WIDTH){
			if(y > -1 && y < HEIGHT){
				if(this.map[x][y].isBlocked()){
					return true;
				}
			}
		}
		x = (int) (((nextX+width))/32);
		y = (int) (((nextY))/32);
		if(x > -1 && x < WIDTH){
			if(y > -1 && y < HEIGHT){
				if(this.map[x][y].isBlocked()){
					return true;
				}
			}
		}
		x = (int) (((nextX))/32);
		y = (int) (((nextY+height))/32);
		if(x > -1 && x < WIDTH){
			if(y > -1 && y < HEIGHT){
				if(this.map[x][y].isBlocked()){
					return true;
				}
			}
		}
		x = (int) (((nextX+width))/32);
		y = (int) (((nextY+height))/32);
		if(x > -1 && x < WIDTH){
			if(y > -1 && y < HEIGHT){
				if(this.map[x][y].isBlocked()){
					return true;
				}
			}
		}
		return false;
	}
	
	public void addEnemies(World world, int enemyNumber) throws SlickException{
		while(world.getEnemies() < enemyNumber){
			for(int x =0; x < getWidthInTiles(); x++){
				for(int y =0; y < getHeightInTiles(); y++){
					if(map[x][y] != null){
						if(map[x][y] instanceof TileGround){
							Random generator = new Random();
							int r = generator.nextInt(50);
							if(r == 1){
								if(world.getEnemies() < enemyNumber){
									world.entities.add(new EnemyBase(x*TILE_SIZE, y*TILE_SIZE, world, world.getPlayer()));
									world.setEnemies(world.getEnemies()+1);
								}
							}
						}
						
					}
				}
			}
		}
		//world.entities.add(new EnemyBase(10*TILE_SIZE, 10*TILE_SIZE, world, world.getPlayer()));
	}
	
	public void openDoors(){
		for(int x =0; x < getWidthInTiles(); x++){
			for(int y =0; y < getHeightInTiles(); y++){
				if(map[x][y] instanceof TileDoor){
					map[x][y].setBlocked(false);
				}
			}
		}
	}

	@Override
	public boolean blocked(PathFindingContext pc, int tx, int ty) {
		if(map[tx][ty].isBlocked() || map[tx][ty] instanceof TileSafeZone){
			return true;
		}
		return false;
	}

	@Override
	public float getCost(PathFindingContext pc, int tx, int ty) {
		return 1;
	}

	@Override
	public int getHeightInTiles() {
		return HEIGHT;
	}

	@Override
	public int getWidthInTiles() {
		return WIDTH;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		
	}

	public int getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;
	}
	
}
