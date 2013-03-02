package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.msquirrel.SpaceShooter.Camera;
import org.newdawn.slick.Graphics;

public class Tile {
	protected int x;
	protected int y;
	protected boolean Blocked;
	protected Camera cam;
	
	public Tile(int x, int y, Camera cam){
		this.cam = cam;
		this.x = x*64;
		this.y = y*64;
	}
	
	public void draw(Graphics g){
		
	}
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public boolean isBlocked() {
		return Blocked;
	}


	public void setBlocked(boolean blocked) {
		Blocked = blocked;
	}	
}
