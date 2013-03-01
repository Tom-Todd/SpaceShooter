package org.msquirrel.SpaceShooter.Entities.Projectiles;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class bullet extends projectile{
	
	public bullet(float x, float y,float targetX, float targetY, World world,Entity Origin) throws SlickException{
		super(x, y, targetX, targetY, world, Origin);
		this.entityImage = new Image("res/bullet.png");
	}
	
	@Override
	public void update(GameContainer container, int delta){
		this.move(delta);
		if(lifeTime > 50){
			this.alive = false;
			die();
		}
		this.setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
		lifeTime++;
		collision();
	}
}
