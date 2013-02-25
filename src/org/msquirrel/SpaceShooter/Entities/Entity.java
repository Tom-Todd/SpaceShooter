package org.msquirrel.SpaceShooter.Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Entity {
	private float x;
	private float y;
	private boolean alive;
	private Image entityImage;
	private Rectangle hitBox;
	
	public Entity(float x, float y){
		this.x = x;
		this.y = y;
		this.hitBox = new Rectangle(this.x, this.y, entityImage.getWidth(), entityImage.getHeight());
	}
	
	public void setHitBox(float x, float y, float width, float height){
		this.hitBox = new Rectangle(x,y,width,height);
	}
	
	public Rectangle getHitBox(){
		return hitBox;
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
