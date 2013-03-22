package org.msquirrel.SpaceShooter.Entities;

import java.io.IOException;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.SlickException;

public class EnemyEMP extends EnemyBase{

	public EnemyEMP(float x, float y, World world, Player player)
			throws SlickException, IOException {
		super(x, y, world, player);
		this.shielded = true;
		Shield = new Shield(x, y, world, this);
		world.addEntity(Shield);
	}

}
