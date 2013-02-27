package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class bullet extends Entity{
	
	public bullet(float x, float y, World world, int Direction) throws SlickException{
		super(x, y, world);
		if(Direction == 0){
			velocity.y = -bulletSpeed;
			velocity.x = 0;
		}
		if(Direction == 1){
			velocity.y = bulletSpeed;
			velocity.x = 0;
		}
		if(Direction == 2){
			velocity.y = 0;
			velocity.x = bulletSpeed;
		}
		if(Direction == 3){
			velocity.y = 0;
			velocity.x = -bulletSpeed;
		}
		if(Direction == 4){
			velocity.y = -bulletSpeed;
			velocity.x = bulletSpeed;
		}
		this.entityImage = new Image("res/bullet.png");
	}
	
	@Override
	public void update(GameContainer container, int delta){
		this.y += velocity.y*delta;
		this.x += velocity.x*delta;
		if(lifeTime > 50){
			this.alive = false;
			die();
		}
		this.setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
		lifeTime++;
	}
}
