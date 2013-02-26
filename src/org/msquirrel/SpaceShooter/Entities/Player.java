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
		movingRight = container.getInput().isKeyDown(Input.KEY_RIGHT);
		movingLeft = container.getInput().isKeyDown(Input.KEY_LEFT);
		movingUp = container.getInput().isKeyDown(Input.KEY_UP);
		movingDown = container.getInput().isKeyDown(Input.KEY_DOWN);
		if(container.getInput().isKeyPressed(Input.KEY_SPACE)){
			world.entities.add(new bullet(this.x, this.y, world));
		}
		move(delta);
	}
}
