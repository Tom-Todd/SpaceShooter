package org.msquirrel.SpaceShooter.Entities;

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
	protected Team team = Team.PLAYER_TEAM;
	
	public Player(float x, float y, World world) throws SlickException{
		super(x, y, world);
		entityImage = new Image("res/Player.png");
		isMoving = true;
		hitBox = new Rectangle(x,y,entityImage.getWidth(), entityImage.getHeight());
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
		move(delta);
		setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
	}
}
