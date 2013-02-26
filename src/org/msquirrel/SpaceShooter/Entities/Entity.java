package org.msquirrel.SpaceShooter.Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Entity {
	protected float x;
	protected float y;
	protected Vector2f velocity;
	protected boolean alive;
	protected Rectangle hitBox;
	protected boolean isMoving;
	protected Image entityImage;
	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;
	protected float speed = 0.3f;
	
	public Entity(float x, float y) throws SlickException{
		this.x = x;
		this.y = y;
		this.hitBox = new Rectangle(x,y,10,10);
		velocity = new Vector2f();
	}
	
	public void setHitBox(float x, float y, float width, float height){
		this.hitBox = new Rectangle(x,y,width,height);
	}
	
	public Rectangle getHitBox(){
		return hitBox;
	}
	
	public void move(){
		velocity.x = 0;
		velocity.y = 0;
		if(movingRight){
			velocity.x = speed;
		}
		if(movingLeft){
			velocity.x = -(speed);
		}
		if(movingUp){
			velocity.y = -(speed);
		}
		if(movingDown){
			velocity.y = speed;
		}
		
		if(movingRight && movingUp){
			velocity.x = (float) Math.sqrt(((speed*speed)/2));
			velocity.y = (float) -Math.sqrt(((speed*speed)/2));
		}
		if(movingLeft && movingUp){
			velocity.x = (float) -Math.sqrt(((speed*speed)/2));
			velocity.y = (float) -Math.sqrt(((speed*speed)/2));
		}
		if(movingRight && movingDown){
			velocity.x = (float) Math.sqrt(((speed*speed)/2));
			velocity.y = (float) Math.sqrt(((speed*speed)/2));
		}
		if(movingLeft && movingDown){
			velocity.x = (float) -Math.sqrt(((speed*speed)/2));
			velocity.y = (float) Math.sqrt(((speed*speed)/2));
		}
		x += velocity.x;
		y += velocity.y;
	}
	
	public void draw(Graphics g){
		entityImage.draw(x,y);
		g.draw(hitBox);
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
