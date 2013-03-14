package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.msquirrel.SpaceShooter.Camera;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class TileDoor extends Tile {
	protected Image door;
	
	public TileDoor(int x, int y, Image door) {
		super(x, y);
		this.door = door;
		this.Blocked = true;
	}
	
	public void open(){
		this.Blocked = false;
	}
	
	@Override
	public void draw(Graphics g){
		if(Blocked){
			door.draw(x, y);
		}
	}
	
}
