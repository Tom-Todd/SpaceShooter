package org.msquirrel.SpaceShooter.Entities;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Effects.Explosion;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;

public class EnemyBase extends Entity implements Mover{
	protected Player player;
	protected int attackCounter;
	protected int moveCounter;
	protected boolean moved;
	protected Path CurPath;
	protected int targetX;
	protected int targetY;
	protected int MAX_SIGHT_DISTANCE = 6;
	protected int plrDistance;
	protected int attackTime;
	protected boolean Spotted;
	protected boolean playerVisible;
	
	public EnemyBase(float x, float y, World world, Player player) throws SlickException, IOException {
		super(x, y, world);
		this.player = player;
		this.speed = 0.1f;
		this.setTeam(Team.ENEMY_TEAM);
		this.width = 16;
		this.height = 16;
		this.nextX = x;
		this.nextY = y;
		this.attackTime = 50;
		moved = true;
		entityImage = world.getImages().enemy;
		hitBox = new Rectangle(x,y,entityImage.getWidth(),entityImage.getHeight());
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
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
		
		if(attacking && attackCounter > attackTime){
			float guessPlayerPosX = 0;
			float guessPlayerPosY = 0;
			Random accuracy = new Random();
			if(difficulty == 0){
				if(accuracy.nextInt(2) == 0){
					guessPlayerPosX = (player.x - velocity.x*delta) + accuracy.nextInt(20)+10;
					guessPlayerPosY = (player.y - velocity.y*delta) + accuracy.nextInt(20)+10;
				}
				if(accuracy.nextInt(2) == 1){
					guessPlayerPosX = (player.x - velocity.x*delta) - accuracy.nextInt(20)+10;
					guessPlayerPosY = (player.y - velocity.y*delta) - accuracy.nextInt(20)+10;
				}
			}
			if(difficulty == 1){
				if(accuracy.nextInt(2) == 0){
					guessPlayerPosX = (player.x - velocity.x*delta) + accuracy.nextInt(10);
					guessPlayerPosY = (player.y - velocity.y*delta) + accuracy.nextInt(10);
				}
				if(accuracy.nextInt(2) == 1){
					guessPlayerPosX = (player.x - velocity.x*delta) - accuracy.nextInt(10);
					guessPlayerPosY = (player.y - velocity.y*delta) - accuracy.nextInt(10);
				}
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

			world.projectiles.add(new bullet(x, y, guessPlayerPosX, guessPlayerPosY, world, this));
			attackCounter = 0;
		}
		move(delta);
		this.setHitBox(x, y, width, height);
		attackCounter++;
		moveCounter++;
	}
	
	public Path getPath(int targetX, int targetY){
		Map map = world.getMap();
		AStarPathFinder pathFinder = new AStarPathFinder(map, 100, false);
		Path path = pathFinder.findPath(null, this.getMapTileX(), this.getMapTileY(), targetX, targetY);
		return path;
	}
	
	public void move(int delta){
		//IF PLAYER IN SIGHT
		if(!(this.getMapTileX() == player.getMapTileX()
				&& this.getMapTileY() == player.getMapTileY()) && Spotted){
			if(moved){
				CurPath = this.getPath(player.getMapTileX(), player.getMapTileY());
				if(CurPath != null){
					targetX = CurPath.getX(1);
					targetY = CurPath.getY(1);
					moved = false;
				}
			}
			if(CurPath != null){
				float targetRealX = (targetX*32)+(width/2);
				float targetRealY = (targetY*32)+(width/2);
				Double angle = Math.atan2((targetRealY - y), (targetRealX - x));
				velocity.x = (float) (speed*Math.cos(angle));
				velocity.y = (float) (speed*Math.sin(angle));
				
				x += velocity.x*delta;
				y += velocity.y*delta;
				
				if(this.getHitBox().contains(targetRealX+(width/2), targetRealY+(width/2))){
					moved = true;
				}
			}
		}else{
			if(moved){
				Random generator = new Random();
				int x = generator.nextInt(map.getWidthInTiles());
				int y = generator.nextInt(map.getHeightInTiles());
				if(!map.map[x][y].isBlocked() && Math.abs(this.getMapTileY() - y) < 10 
						&& Math.abs(this.getMapTileX() - x) < 10){
					CurPath = this.getPath(x,y);
				}
				if(CurPath != null){
					targetX = CurPath.getX(1);
					targetY = CurPath.getY(1);
					moved = false;
				}
			}
			if(CurPath != null){
				float targetRealX = (targetX*32)+(width/2);
				float targetRealY = (targetY*32)+(width/2);
				Double angle = Math.atan2((targetRealY - y), (targetRealX - x));
				velocity.x = (float) (speed*Math.cos(angle));
				velocity.y = (float) (speed*Math.sin(angle));
				
				x += velocity.x*delta;
				y += velocity.y*delta;
				if(this.getHitBox().contains(targetRealX+(width/2), targetRealY+(width/2))){
					moved = true;
				}
			}
			if(CurPath == null){
				moved = true;
			}
		}
	}
	
	@Override
	public void die() throws SlickException{
		Random generator = new Random();
		world.entities.add(new Explosion(x+40, y+40, world, world.getWorldEntity(), 0, false, 0.5f));
		this.alive = false;
		if(world.getEnemies() == 1){
			world.addEntity(new key(this.x, this.y, world));
		}
		if(generator.nextInt(5) == 1 && !(world.getEnemies() == 1)){
			world.addEntity(new Shield(this.x, this.y, world));
		}
		world.setScore(world.getScore()+1);
		world.entities.remove(this);
	}
	
	public void hit() throws SlickException{
		die();
	}
	
	@Override
	public void draw(Graphics g){
		entityImage.setColor(0, 1, 1, 1);
		entityImage.setColor(1, 1, 1, 1);
		entityImage.setColor(2, 1, 1, 1);
		entityImage.setColor(3, 1, 1, 1);
		entityImage.draw(x,y);
		if(world.isDebugging()){
			if(CurPath != null){
				for (int l = 0; l < CurPath.getLength(); l++){
					g.drawRect(CurPath.getX(l)*32, CurPath.getY(l)*32, 5, 5);
				}
			}
			g.draw(hitBox);
		}
	}
}
