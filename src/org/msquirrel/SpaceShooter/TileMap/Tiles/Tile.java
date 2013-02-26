package org.msquirrel.SpaceShooter.TileMap.Tiles;

public class Tile {
	private int x;
	private int y;
	private boolean Blocked;
	
	public Tile(){
		
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
