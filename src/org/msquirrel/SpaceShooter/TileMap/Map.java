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

	public boolean blocked(float nextX, float nextY) {
		int x = (int) nextX/64;
		int y = (int) nextY/64;
		if(x > -1 && x < 32){
			if(y > -1 && y < 32){
				if(this.map[x][y] instanceof TileWall){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
}
