package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.msquirrel.SpaceShooter.Camera;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TileWall extends Tile{
	private Image tileImage;
	
	public TileWall(int x, int y, Camera cam) throws SlickException{
		super(x,y, cam);
		this.Blocked = true;
		tileImage = new Image("res/floorWall.png");
	}
	
	public void draw(Graphics g){
		/*g.setColor(Color.darkGray);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.black);
		g.drawRect(x, y, 32, 32);*/
		tileImage.draw(x,y);
	}
}
