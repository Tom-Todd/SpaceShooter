package org.msquirrel.SpaceShooter.Entities.Projectiles;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Team;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.SlickException;

public class projectile extends Entity{
	protected float projectileSpeed = 1f;
	protected Entity Origin;
	
	public projectile(float x, float y,float targetX, float targetY, World world, Entity Origin) throws SlickException{
		super(x, y,world);
		this.Origin = Origin;
		Double angle = Math.atan2((targetY - y), (targetX - x));
		velocity.x = (float) (projectileSpeed*Math.cos(angle));
		velocity.y = (float) (projectileSpeed*Math.sin(angle));
	}

	public float getProjectileSpeed() {
		return projectileSpeed;
	}

	public void setProjectileSpeed(float projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}
	
	@Override
	public void move(int delta){
		nextX += velocity.x*delta;
		nextY += velocity.y*delta;
		
		if(!world.getMap().blocked(nextX, nextY, width, height)){
			x += velocity.x*delta;
			y += velocity.y*delta;
		}else{
			die();
		}
		if(world.getMap().blocked(nextX, nextY, width, height)){
			die();
		}
	}
	
	@Override
	public void collision() throws SlickException{
		for(int index = 0;index < world.entities.size(); index++){
			if(this.hitBox.intersects(world.entities.get(index).getHitBox())){
				if(world.entities.get(index).getTeam() != Origin.getTeam() && world.entities.get(index).getTeam() != Team.OBJECT){
					world.entities.get(index).hit();
					die();
				}
			}
			if(this.hitBox.intersects(world.getPlayer().getShieldCircle()) 
					&& Origin != world.getPlayer() && world.getPlayer().isShielded()){
				die();
			}
		}
	}
	
	@Override
	public void die(){
		world.projectiles.remove(this);
	}
}
