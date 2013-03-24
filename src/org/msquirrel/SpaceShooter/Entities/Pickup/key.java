package org.msquirrel.SpaceShooter.Entities.Pickup;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class key extends PickUp{
	private float originalY;
	private boolean bouncingDown;
	private boolean bouncingUp;
	private float bounceSpeed = 0.1f;
	private Sound sound;
	
	public key(float x, float y, World world) throws SlickException {
		super(x, y, world);
		this.entityImage = world.getImages().Key;
		this.width = this.entityImage.getWidth();
		this.height = this.entityImage.getHeight();
		this.sound = new Sound("res/pickup.wav");
	}
	
	@Override
	public void collision() throws SlickException{
		if(this.hitBox.intersects(world.getPlayer().getHitBox())){
			System.out.println("test");
			world.getMap().openDoors();
			sound.play();
			this.die();
		}
	}

}
