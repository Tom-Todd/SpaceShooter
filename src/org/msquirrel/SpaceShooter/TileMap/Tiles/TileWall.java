package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TileWall extends Tile{
	private boolean Blocked = true;
	
	public TileWall(int x, int y){
		super(x,y);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.darkGray);
		g.fillRect(x, y, 64, 64);
		System.out.println("drawing wall");
		g.setColor(Color.black);
		g.drawRect(x, y, 64, 64);
	}
}
