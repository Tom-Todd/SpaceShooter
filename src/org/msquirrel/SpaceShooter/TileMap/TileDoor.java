package org.msquirrel.SpaceShooter.TileMap;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.TileMap.Tiles.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TileDoor extends Tile {

	public TileDoor(int x, int y, Camera cam) {
		super(x, y, cam);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(x + cam.getX(), y + cam.getY(), 32, 32);
		g.setColor(Color.black);
		g.drawRect(x + cam.getX(), y + cam.getY(), 32, 32);
	}
	
}
