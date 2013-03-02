package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class EnemyBase extends Entity{
	protected Player player;
	protected int attackCounter;
	
	public EnemyBase(float x, float y, World world, Player player) throws SlickException {
		super(x, y, world);
		this.player = player;
		this.setTeam(Team.ENEMY_TEAM);
		entityImage = new Image("res/Player.png");
		hitBox = new Rectangle(x,y,entityImage.getWidth(),entityImage.getHeight());
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		attacking = true;
		if(attacking && attackCounter > 10){
			float guessPlayerPosX = 0;
			float guessPlayerPosY = 0;
			guessPlayerPosX = player.x;
			guessPlayerPosY = player.y;
			
			if(player.velocity.x < 0){
				guessPlayerPosX = (player.x - (player.speed*delta));
			}
			if(player.velocity.x > 0){
				guessPlayerPosX = (player.x + (player.speed*delta));
			}
			//---------------------------------------------//
			if(player.velocity.y < 0){
				guessPlayerPosY = (player.y - (player.speed*delta));
			}
			if(player.velocity.y > 0){
				guessPlayerPosY = (player.y + (player.speed*delta));
			}

			world.projectiles.add(new bullet(x, y, guessPlayerPosX, guessPlayerPosY, world, this));
			attackCounter = 0;
		}
		move(delta);
		attackCounter++;
	}
}
