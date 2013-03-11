package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PickUp extends Entity{
	private float originalY;
	private boolean bouncingDown;
	private boolean bouncingUp;
	private float bounceSpeed = 0.1f;
	
	public PickUp(float x, float y, World world) throws SlickException {
		super(x, y, world);
		originalY = y;
		this.team = Team.OBJECT;
		this.width = 32;
		this.height = 32;
		this.setHitBox(x , y, width,height);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		if(this.y >= originalY){
			bouncingUp = true;
			bouncingDown = false;
		}
		if(this.y <= originalY-5){
			bouncingDown = true;
			bouncingUp = false;
		}
		if(bouncingUp) y -= bounceSpeed;
		if(bouncingDown) y += bounceSpeed;
		this.setHitBox(x, y, width, height);
		collision();
	}
	
	@Override
	public void collision() throws SlickException{
		if(this.hitBox.intersects(world.getPlayer().getHitBox())){
			this.die();
		}
	}
}