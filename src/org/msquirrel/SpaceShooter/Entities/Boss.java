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
	private float xAmount = -20;
	private float shotTargetX;
	private float shotTargetY;
	private float grenadeCount;
	private int coolDown;
	private boolean attack1;
	private int attack1Count;
	private boolean attack2;
	private boolean fightStarted;
	private Image LifeBarSheet;
	private Image LifeBarFull;
	private Image LifeBarEmpty;
	private Random generator;
	
	public Boss(float x, float y, World world, Player player)
			throws SlickException, IOException {
		super(x, y, world, player);
		this.LifeBarSheet = new Image("res/LifeBar.png");
		this.LifeBarFull = LifeBarSheet.getSubImage(0, 0, LifeBarSheet.getWidth(), LifeBarSheet.getHeight()/2);
		this.LifeBarEmpty = LifeBarSheet.getSubImage(0, 24, 332, 21);
		this.sprites = new SpriteSheet(new Image("res/Boss.png"), 32, 32);
		sprites.setFilter(Image.FILTER_NEAREST);
		this.boss = new Animation(new Image[]{sprites.getSprite(0, 0),sprites.getSprite(1, 0), sprites.getSprite(2, 0)}, 100);
		boss.setPingPong(true);
		this.width = 64;
		this.height = 64;
		boss.setCurrentFrame(0);
		boss.stop();
		generator = new Random();
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException{
		if(player.getY() < 1093 && !fightStarted){
			attacking = true;
			attack1 = true;
			fightStarted = true;
		}
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
		
		if(fightStarted){
			if(!attacking){
				coolDown++;
			}
			
			if(attack1 && attackCounter > 5 && playerVisible){
				shotTargetX = player.getX() + (xAmount);
				shotTargetY = player.getY();
				world.projectiles.add(new grenade(x+32, y+25, shotTargetX, shotTargetY, world, this));
				grenadeCount++;
				attackCounter = 0;
				xAmount += 10;
				if(grenadeCount >= 4){
					boss.stopAt(2);
					boss.start();
					grenadeCount = 0;
					xAmount = -20;
					attack1 = false;
					attacking = false;
					attack1Count++;
				}
			}
			if(attack2 && attackCounter > 5){
				shotTargetX = x + (xAmount);
				if(generator.nextInt(2) == 1){
					shotTargetY = y + 100;
				}
				world.projectiles.add(new grenade(x+32, y+25, shotTargetX, shotTargetY, world, this));
				grenadeCount++;
				attackCounter = 0;
				xAmount += 10;
				if(grenadeCount >= 28){
					boss.stopAt(2);
					boss.start();
					grenadeCount = 0;
					xAmount = -20;
					attack2 = false;
					attacking = false;
					attack1Count++;
				}
			}
			
			if(coolDown > 250){
				coolDown = 0;
				boss.stopAt(0);
				boss.start();
				if(attack1Count < 3){
					attack1 = true;
				}
				else{
					attack2 = true;
					xAmount = -100;
					attack1Count = 0;
				}
				attacking = true;
			}
		}
		this.setHitBox(x, y, width, height);
		attackCounter++;
		moveCounter++;
		if(hitPoints <= 0){
			world.entities.add(new Explosion(x, y, world, world.getWorldEntity(), 0, false, 1));
			world.entities.add(new Explosion(x-10, y+5, world, world.getWorldEntity(), 30, false,1));
			world.entities.add(new Explosion(x+15, y, world, world.getWorldEntity(), 50, false,1));
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
			if(fightStarted){
				if(attack1){
					attacking = false;
					boss.stopAt(2);
					boss.start();
					grenadeCount = 0;
					xAmount = -20;
					attack1 = false;
				}
				if(attack2){
					attacking = false;
					boss.stopAt(2);
					boss.start();
					attack2 = false;
				}
			}
		}
		int r = (int)  (16.6*(hitPoints));
		LifeBarFull = LifeBarSheet.getSubImage(0, 0, r, 21);
	}
	
	@Override
	public void draw(Graphics g){
		boss.draw(x, y, 64, 64);
		LifeBarEmpty.draw(x-30, y-30,0.4f);
		LifeBarFull.draw(x-30, y-30,0.4f);
	}
	public int getMapTileX(){
		return (int) (x+32)/map.TILE_SIZE;
	}
	
	public int getMapTileY(){
		return (int) (y+25)/map.TILE_SIZE;
	}
}
