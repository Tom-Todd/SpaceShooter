package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.msquirrel.SpaceShooter.Camera;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TileSpace extends Tile{

	public TileSpace(int x, int y, Camera cam) {
		super(x, y, cam);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect(x + cam.getX(), y + cam.getY(), 32, 32);
		g.setColor(Color.black);
		g.drawRect(x + cam.getX(), y + cam.getY(), 32, 32);
	}

}
