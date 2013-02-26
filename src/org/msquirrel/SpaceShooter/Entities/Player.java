package org.msquirrel.SpaceShooter.Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity{
	
	public Player(float x, float y) throws SlickException{
		super(x, y);
		entityImage = new Image("res/Player.png");
		isMoving = true;
		hitBox = new Rectangle(x,y,entityImage.getWidth(), entityImage.getHeight());
	}
	
	public void update(GameContainer container, int delta){
		setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
		move();
		movingRight = container.getInput().isKeyDown(Input.KEY_RIGHT);
		movingLeft = container.getInput().isKeyDown(Input.KEY_LEFT);
		movingUp = container.getInput().isKeyDown(Input.KEY_UP);
		movingDown = container.getInput().isKeyDown(Input.KEY_DOWN);
	}
}
