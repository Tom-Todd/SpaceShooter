package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class bullet extends Entity{
	
	public bullet(float x, float y, World world) throws SlickException{
		super(x, y, world);
		this.entityImage = new Image("res/bullet.png");
	}
	
	@Override
	public void update(GameContainer container, int delta){
		velocity.y = bulletSpeed;
		this.y -= bulletSpeed * delta;
		if(lifeTime > 100){
			this.alive = false;
		}
		lifeTime++;
	}
}
