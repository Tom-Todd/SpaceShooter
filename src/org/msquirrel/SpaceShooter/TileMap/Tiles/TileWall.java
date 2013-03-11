package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.msquirrel.SpaceShooter.Camera;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TileWall extends Tile{
	
	public TileWall(int x, int y) throws SlickException{
		super(x,y);
		this.Blocked = true;
	}
	
	public void draw(Graphics g){
		/*g.setColor(Color.darkGray);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.black);
		g.drawRect(x, y, 32, 32);*/
	}
}
