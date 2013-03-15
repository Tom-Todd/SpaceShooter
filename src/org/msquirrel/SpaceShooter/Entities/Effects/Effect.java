package org.msquirrel.SpaceShooter.Entities.Effects;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.newdawn.slick.SlickException;

public class Effect extends Entity{
	protected Entity parent;
	public Effect(float x, float y, World world,Entity parent) throws SlickException {
		super(x, y, world);
		this.parent = parent;
	}
	
}
