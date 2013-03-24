package org.msquirrel.SpaceShooter.Entities.Pickup;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class ShieldPickup extends PickUp{
	private Sound sound;

	public ShieldPickup(float x, float y, World world) throws SlickException {
		super(x, y, world);
		this.entityImage = world.getImages().ShieldPickup;
		this.height = entityImage.getHeight();
		this.width = entityImage.getWidth();
		this.sound = new Sound("res/pickupShield.wav");
	}
	
	@Override
	public void collision() throws SlickException{
		if(this.hitBox.intersects(world.getPlayer().getHitBox())){
			if(world.getPlayer().getShield().getLifeTime() < 500 && world.getPlayer().getShield().getLifeTime()+50 < 300){
				world.getPlayer().getShield().setLifeTime(world.getPlayer().getShield().getLifeTime()+50);
			}else{
				world.getPlayer().getShield().setLifeTime(300);
			}
			sound.play();
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
