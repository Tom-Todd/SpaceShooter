package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.msquirrel.SpaceShooter.Camera;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TileDoor extends Tile {

	public TileDoor(int x, int y) {
		super(x, y);
		this.Blocked = true;
	}
	
	public void open(){
		this.Blocked = false;
	}
	
	public void draw(Graphics g){
		/*g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.black);
		g.drawRect(x, y, 32, 32);*/
	}
	
}
