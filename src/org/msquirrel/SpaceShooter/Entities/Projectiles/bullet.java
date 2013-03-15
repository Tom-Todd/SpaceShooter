package org.msquirrel.SpaceShooter.Entities.Projectiles;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Effects.Glow;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class bullet extends projectile{
	
	public bullet(float x, float y,float targetX, float targetY, World world, Entity Origin) throws SlickException{
		super(x, y, targetX, targetY, world, Origin);
		this.entityImage = new Image("res/bullet.png");
		this.width = 8;
		this.height = 8;
		this.effect = new Glow(x, y, world, this);
		world.entities.add(this.effect);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		this.move(delta);
		if(lifeTime > 50){
			this.alive = false;
			die();
		}
		this.setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
		lifeTime++;
		collision();
		effect.setX(x);
		effect.setY(y);
	}
	
	@Override
	public void draw(Graphics g){
		g.setDrawMode(g.MODE_ADD_ALPHA);
		entityImage.draw(x,y);
		g.setDrawMode(g.MODE_NORMAL);
		//g.draw(hitBox);
	}
}
