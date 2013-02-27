package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TileGround extends Tile{
	
	public TileGround(int x, int y){
		super(x,y);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(x, y, 64, 64);
		g.setColor(Color.black);
		g.drawRect(x, y, 64, 64);
	}
}
