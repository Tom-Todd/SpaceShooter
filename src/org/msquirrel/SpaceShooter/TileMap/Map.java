package org.msquirrel.SpaceShooter.TileMap;

import org.msquirrel.SpaceShooter.TileMap.Tiles.Tile;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileGround;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileWall;
import org.newdawn.slick.Graphics;

public class Map {
	private Tile[][] map = new Tile[32][32];
	
	public Map(){
		for(int x = 0;x < 32; x++){
			for(int y = 0;y < 32; y++){
				map[x][y]= new TileGround(x,y);
				map[0][y]= new TileWall(0,y);
			}
		}
		
	}
	
	public void draw(Graphics g){
		for(int x = 0;x < 32; x++){
			for(int y = 0;y < 32; y++){
				map[x][y].draw(g);
			}
		}
	}
}
