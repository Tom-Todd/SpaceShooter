package org.msquirrel.SpaceShooter.TileMap.Tiles;

import org.newdawn.slick.Graphics;

public class Tile {
	protected int x;
	protected int y;
	private boolean Blocked;
	
	public Tile(int x, int y){
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
