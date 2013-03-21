package org.msquirrel.SpaceShooter.Entities.Projectiles;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Team;
import org.msquirrel.SpaceShooter.Entities.Effects.Explosion;
import org.msquirrel.SpaceShooter.Entities.Effects.Glow;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class grenade extends projectile{

	public grenade(float x, float y, float targetX, float targetY, World world,
			Entity Origin) throws SlickException {
		super(x, y, targetX, targetY, world, Origin);
		this.entityImage = new Image("res/bullet.png");
		this.effect = new Glow(x, y, world, this);
		world.entities.add(this.effect);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		this.move(delta);
		if(lifeTime > 100){
			this.alive = false;
			world.entities.add(new Explosion(x, y, world, this, 0, true, 1));
			die();
		}
		this.setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
		lifeTime++;
		collision();
		effect.setX(x);
		effect.setY(y);
	}
	
	@Override
	public void move(int delta){
		nextX += velocity.x*delta;
		nextY += velocity.y*delta;
		
		if(!world.getMap().blocked(nextX, nextY, width, height)){
			x += velocity.x*delta;
			y += velocity.y*delta;
		}
		if(world.getMap().blocked(nextX, nextY, width, height)){
			nextX = x;
			nextY = y;
		}
	}
	
	
	@Override
	public void draw(Graphics g){
		g.setDrawMode(g.MODE_ADD_ALPHA);
		entityImage.draw(x,y);
		g.setDrawMode(g.MODE_NORMAL);
		//g.draw(hitBox);
	}
}
