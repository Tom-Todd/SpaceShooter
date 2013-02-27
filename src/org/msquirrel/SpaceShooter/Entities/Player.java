package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity{
	
	public Player(float x, float y, World world) throws SlickException{
		super(x, y, world);
		entityImage = new Image("res/Player.png");
		isMoving = true;
		hitBox = new Rectangle(x,y,entityImage.getWidth(), entityImage.getHeight());
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
		movingUp = container.getInput().isKeyDown(Input.KEY_W);
		movingRight = container.getInput().isKeyDown(Input.KEY_D);
		movingLeft = container.getInput().isKeyDown(Input.KEY_A);	
		movingDown = container.getInput().isKeyDown(Input.KEY_S);
		attacking = container.getInput().isKeyPressed(Input.KEY_SPACE);
	
		if(attacking && movingRight){
			world.entities.add(new bullet(this.x, this.y, world, 2));
		}
		if(attacking && movingLeft){
			world.entities.add(new bullet(this.x, this.y, world, 3));
		}
		if(attacking && movingUp){
			world.entities.add(new bullet(this.x, this.y, world, 0));
		}
		if(attacking && movingDown){
			world.entities.add(new bullet(this.x, this.y, world, 1));
		}
		
		move(delta);
	}
}
