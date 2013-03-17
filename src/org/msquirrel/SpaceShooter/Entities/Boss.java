package org.msquirrel.SpaceShooter.Entities;

import java.io.IOException;
import java.util.Random;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Boss extends EnemyBase{
	private SpriteSheet sprites;
	private Animation boss;
	
	public Boss(float x, float y, World world, Player player)
			throws SlickException, IOException {
		super(x, y, world, player);
		this.sprites = new SpriteSheet(new Image("res/Boss.png"), 32, 32);
		sprites.setFilter(Image.FILTER_NEAREST);
		this.boss = new Animation(new Image[]{sprites.getSprite(0, 0),sprites.getSprite(1, 0), sprites.getSprite(2, 0)}, 150);
		boss.setPingPong(true);
		this.width = 64;
		this.height = 64;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException{
		int difX = (int) ((this.getMapPosX()/32) - (world.getPlayer().getMapPosX()/map.TILE_SIZE));
		int difY = (int) ((this.getMapPosY()/32) - (world.getPlayer().getMapPosY()/map.TILE_SIZE));
		int difXSqr = difX * difX;
		int difYSqr = difY * difY;
		plrDistance = (int) Math.sqrt(difXSqr + difYSqr);
		if(RayCasting.tileVisible(this.getMapTileX(), player.getMapTileX(), this.getMapTileY(), player.getMapTileY(), map)){
			playerVisible = true;
		}else{
			playerVisible = false;
		}
		if(playerVisible){
			Spotted = true;
		}
		if(!playerVisible && plrDistance > 10){
			Spotted = false;
		}

		if(playerVisible && plrDistance < 12 && !player.isInSafeZone() && difX < 10 && difY < 6){
			attacking = true;
		}else{
			attacking = false;
		}
		
		if(attacking && boss.getFrame() == 0 && attackCounter > 50){
			float guessPlayerPosX = 0;
			float guessPlayerPosY = 0;
			Random accuracy = new Random();
			if(accuracy.nextInt(2) == 0){
				guessPlayerPosX = (player.x - velocity.x*delta);
				guessPlayerPosY = (player.y - velocity.y*delta);
			}
			if(accuracy.nextInt(2) == 1){
				guessPlayerPosX = (player.x - velocity.x*delta);
				guessPlayerPosY = (player.y - velocity.y*delta);
			}	
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

			world.projectiles.add(new bullet(x+32, y+25, guessPlayerPosX, guessPlayerPosY, world, this));
			attackCounter = 0;
		}
		this.setHitBox(x, y, width, height);
		attackCounter++;
		moveCounter++;
	}
	
	@Override
	public void move(int delta){
		
	}
	
	@Override
	public void draw(Graphics g){
		boss.draw(x, y, 64, 64);
	}
	
}
