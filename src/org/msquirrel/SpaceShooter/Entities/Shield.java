package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Shield extends PickUp{

	public Shield(float x, float y, World world) throws SlickException {
		super(x, y, world);
		this.entityImage = world.getImages().ShieldPickup;
		this.height = entityImage.getHeight();
		this.width = entityImage.getWidth();
	}
	
	@Override
	public void collision() throws SlickException{
		if(this.hitBox.intersects(world.getPlayer().getHitBox())){
			if(world.getPlayer().getShieldCounter() < 500 && world.getPlayer().getShieldCounter()+50 < 300){
				world.getPlayer().setShieldCounter(world.getPlayer().getShieldCounter()+50);
			}else{
				world.getPlayer().setShieldCounter(300);
			}
			this.die();
		}
	}
	
	@Override
	public void draw(Graphics g){
		g.setDrawMode(g.MODE_ADD_ALPHA);
		entityImage.draw(x,y);
		g.setDrawMode(g.MODE_NORMAL);
	}
}
