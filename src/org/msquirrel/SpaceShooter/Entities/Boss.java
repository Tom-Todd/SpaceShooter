package org.msquirrel.SpaceShooter.Entities;

import java.io.IOException;
import java.util.Random;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Effects.Explosion;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.msquirrel.SpaceShooter.Entities.Projectiles.grenade;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Boss extends EnemyBase{
	private SpriteSheet sprites;
	private Animation boss;
	private int hitPoints = 20;
	private float xAmount = -120;
	private float shotTargetX;
	private float shotTargetY;
	private float grenadeCount;
	private int coolDown;
	
	public Boss(float x, float y, World world, Player player)
			throws SlickException, IOException {
		super(x, y, world, player);
		this.sprites = new SpriteSheet(new Image("res/Boss.png"), 32, 32);
		sprites.setFilter(Image.FILTER_NEAREST);
		this.boss = new Animation(new Image[]{sprites.getSprite(0, 0),sprites.getSprite(1, 0), sprites.getSprite(2, 0)}, 100);
		boss.setPingPong(true);
		this.width = 64;
		this.height = 64;
		attacking = true;
		boss.setCurrentFrame(0);
		boss.stop();
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
		
		if(!attacking){
			coolDown++;
		}
		
		if(attacking && attackCounter > 5){
			shotTargetX = x + (xAmount);
			shotTargetY = y+100;
			world.projectiles.add(new grenade(x+32, y+25, shotTargetX, shotTargetY, world, this));
			grenadeCount++;
			attackCounter = 0;
			xAmount += 10;
			if(grenadeCount >= 28){
				boss.stopAt(2);
				boss.start();
				grenadeCount = 0;
				xAmount = -100;
				attacking = false;
			}
		}
		
		if(coolDown > 250){
			coolDown = 0;
			boss.stopAt(0);
			boss.start();
			attacking = true;
		}
		this.setHitBox(x, y, width, height);
		attackCounter++;
		moveCounter++;
		if(hitPoints <= 0){
			world.entities.add(new Explosion(x, y, world, this, 0));
			world.entities.add(new Explosion(x-10, y+5, world, this, 30));
			world.entities.add(new Explosion(x+15, y, world, this, 50));
			this.die();
		}
		for(int i = 0; i < boss.getFrameCount(); i++){
			boss.getImage(i).setColor(0, 1, 1, 1);
			boss.getImage(i).setColor(1, 1, 1, 1);
			boss.getImage(i).setColor(2, 1, 1, 1);
			boss.getImage(i).setColor(3, 1, 1, 1);
		}
	}
	
	@Override
	public void move(int delta){
		
	}
	
	@Override
	public void hit(){
		if(boss.getFrame() == 0){
			hitPoints --;
			for(int i = 0; i < boss.getFrameCount(); i++){
				boss.getImage(i).setColor(0, 1, 0, 0);
				boss.getImage(i).setColor(1, 1, 0, 0);
				boss.getImage(i).setColor(2, 1, 0, 0);
				boss.getImage(i).setColor(3, 1, 0, 0);
			}
		}
	}
	
	@Override
	public void draw(Graphics g){
		boss.draw(x, y, 64, 64);
	}
	public int getMapTileX(){
		return (int) (x+32)/map.TILE_SIZE;
	}
	
	public int getMapTileY(){
		return (int) (y+25)/map.TILE_SIZE;
	}
}
