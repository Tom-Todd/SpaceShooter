package org.msquirrel.SpaceShooter.Entities.Effects;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.newdawn.slick.SlickException;

public class EnemyExplosion extends Effect{

	public EnemyExplosion(float x, float y, World world, Entity parent)throws SlickException {
		super(x, y, world, parent);
	}

}
