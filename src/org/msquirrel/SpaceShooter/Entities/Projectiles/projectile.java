package org.msquirrel.SpaceShooter.Entities.Projectiles;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.newdawn.slick.SlickException;

public class projectile extends Entity{
	protected float projectileSpeed = 1.5f;
	
	public projectile(float x, float y,float targetX, float targetY, World world) throws SlickException{
		super(x, y, world);
		Double angle = Math.atan2((targetY - y), (targetX - x));
		velocity.x = (float) (projectileSpeed*Math.cos(angle));
		velocity.y = (float) (projectileSpeed*Math.sin(angle));
		System.out.println(velocity.y);
	}

	public float getProjectileSpeed() {
		return projectileSpeed;
	}

	public void setProjectileSpeed(float projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}
	
}
