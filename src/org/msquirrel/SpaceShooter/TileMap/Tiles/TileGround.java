package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.msquirrel.SpaceShooter.Camera;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TileGround extends Tile{
	
	public TileGround(int x, int y,  Camera cam){
		super(x, y, cam);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.black);
		g.drawRect(x, y, 32, 32);
	}
}
