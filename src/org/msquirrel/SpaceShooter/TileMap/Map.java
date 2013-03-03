package org.msquirrel.SpaceShooter.TileMap;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.TileMap.Tiles.Tile;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileGround;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileWall;
import org.newdawn.slick.Graphics;

public class Map {
	private Tile[][] map;
	private Camera cam;
	private static int WIDTH;
	private static int HEIGHT;
	
	public Map(Camera cam){
		this.cam = cam;
		Bitmap loadmap = mapLoader.loadBitmap("res/map.png");
		WIDTH = loadmap.width;
		HEIGHT = loadmap.height;
		map = new Tile[WIDTH][HEIGHT];
		for(int x = 0;x < WIDTH; x++){
			for(int y = 0;y < HEIGHT; y++){
				map[x][y]= new TileGround(x, y, this.cam);
				switch (loadmap.pixels[(y*WIDTH)+x]){
					case 0xFF999999:
					{
						map[x][y] = new TileWall(x,y,this.cam);
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
	}

	public boolean blocked(float nextX, float nextY, float width, float height) {
		int x = (int) (((nextX)-cam.getX())/32);
		int y = (int) (((nextY)-cam.getY())/32);
		if(x > -1 && x < WIDTH){
			if(y > -1 && y < HEIGHT){
				if(this.map[x][y].isBlocked()){
					return true;
				}
			}
		}
		x = (int) (((nextX+width)-cam.getX())/32);
		y = (int) (((nextY)-cam.getY())/32);
		if(x > -1 && x < WIDTH){
			if(y > -1 && y < HEIGHT){
				if(this.map[x][y].isBlocked()){
					return true;
				}
			}
		}
		x = (int) (((nextX)-cam.getX())/32);
		y = (int) (((nextY+height)-cam.getY())/32);
		if(x > -1 && x < WIDTH){
			if(y > -1 && y < HEIGHT){
				if(this.map[x][y].isBlocked()){
					return true;
				}
			}
		}
		x = (int) (((nextX+width)-cam.getX())/32);
		y = (int) (((nextY+height)-cam.getY())/32);
		if(x > -1 && x < WIDTH){
			if(y > -1 && y < HEIGHT){
				if(this.map[x][y].isBlocked()){
					return true;
				}
			}
		}
		return false;
	}
}
