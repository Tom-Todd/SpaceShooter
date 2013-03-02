package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity{
	protected int ShootCounter;
	protected Camera cam;
	
	public Player(float x, float y, World world, Camera cam) throws SlickException{
		super(x, y, world);
		this.setTeam(Team.PLAYER_TEAM);
		this.cam = cam;
		this.width = 16;
		this.height = 16;
		entityImage = new Image("res/Player.png");
		hitBox = new Rectangle(x, y, entityImage.getWidth(), entityImage.getHeight());
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		movingUp = container.getInput().isKeyDown(Input.KEY_W);
		movingRight = container.getInput().isKeyDown(Input.KEY_D);
		movingLeft = container.getInput().isKeyDown(Input.KEY_A);	
		movingDown = container.getInput().isKeyDown(Input.KEY_S);
	
		if(container.getInput().isMouseButtonDown(0) && ShootCounter > 10){
			world.projectiles.add(new bullet(this.x, this.y, (float)container.getInput().getMouseX(), (float)container.getInput().getMouseY(), world, this));
			ShootCounter = 0;
		}
		ShootCounter++;
		this.move(delta);
		setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
	}
	
	@Override
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
		
		if(!world.getMap().blocked(nextX, nextY, width, height)){
			x = nextX;
			y = nextY;
		}else{
			x = x;
			y = y;
			nextX = x;
			nextY = y;
		}
	}
	
	@Override
	public void die(){
		System.out.println("Die");
	}
}
