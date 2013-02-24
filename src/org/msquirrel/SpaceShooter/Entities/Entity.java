package org.msquirrel.SpaceShooter.Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Entity {
	private float x;
	private float y;
	private boolean alive;
	
	public Entity(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void update(GameContainer container, int delta){
		
	}
	
	public void draw(Graphics g){
		
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}
	
}
