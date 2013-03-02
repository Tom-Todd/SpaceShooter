package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Entity {
	//Entity Values
	protected float speed = 0.3f;
	protected float x;
	protected float y;
	protected float nextX;
	protected float nextY;
	protected int lifeTime;
	protected Vector2f velocity;
	protected boolean alive = true;
	protected Team team = Team.NO_TEAM;
	//Movement
	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;
	//Other
	protected World world;
	protected Rectangle hitBox;
	protected Image entityImage;
	//Attack
	protected boolean attacking;
	
	
	public Entity(float x, float y, World world) throws SlickException{
		this.x = x;
		this.y = y;
		this.nextX = x;
		this.nextY = y;
		this.world = world;
		this.hitBox = new Rectangle(x,y,10,10);
		velocity = new Vector2f();
	}
	
	
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void setHitBox(float x, float y, float width, float height){
		this.hitBox = new Rectangle(x,y,width,height);
	}
	
	public Rectangle getHitBox(){
		return hitBox;
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
	}
	
	public void move(int delta){
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

		nextX += velocity.x*delta;
		nextY += velocity.y*delta;
		
		if(!world.getMap().blocked(nextX, nextY)){
			x = nextX;
			y = nextY;
		}else{
			x = x;
			y = y;
			nextX = x;
			nextY = y;
		}
	}
	
	public void draw(Graphics g){
		entityImage.draw(x,y);
		g.draw(hitBox);
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void hit(){
		
	}
	
	public void die(){
		world.entities.remove(this);
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
