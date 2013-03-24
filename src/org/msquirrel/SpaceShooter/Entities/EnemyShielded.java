package org.msquirrel.SpaceShooter.Entities;

import java.io.IOException;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class EnemyShielded extends EnemyBase{

	public EnemyShielded(float x, float y, World world, Player player)
			throws SlickException, IOException {
		super(x, y, world, player);
		this.shielded = true;
		Shield = new Shield(x, y, world, this);
		world.addEntity(Shield);
		Shield.setLifeTime(100);
	}
	
	public void update(GameContainer container, int Delta) throws SlickException{
		super.update(container, Delta);
		if(Shield.getLifeTime() <= 0){
			Shield.die();
			this.shielded = false;
		}
	}
}
