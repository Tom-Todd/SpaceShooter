package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy extends Entity{

	public Enemy(float x, float y, World world) throws SlickException {
		super(x, y, world);
		entityImage = new Image("res/Player.png");
	}
	
	public void update(GameContainer container, int delta){
		
	}
}
