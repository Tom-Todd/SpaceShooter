package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
	
	public EnemyBase(float x, float y, World world, Player player) throws SlickException {
		super(x, y, world);
		this.player = player;
		this.speed = 2f;
		this.setTeam(Team.ENEMY_TEAM);
		this.width = 16;
		this.height = 16;
		this.x = (this.getMapPosX()+8);
		this.y = (this.getMapPosY()+8);
		this.nextX = x;
		this.nextY = y;
		moved = true;
		entityImage = new Image("res/Player.png");
		hitBox = new Rectangle(x,y,entityImage.getWidth(),entityImage.getHeight());
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		int difX = (int) ((this.getMapPosX()/32) - (world.getPlayer().getMapPosX()/map.TILE_SIZE));
		int difY = (int) ((this.getMapPosY()/32) - (world.getPlayer().getMapPosY()/map.TILE_SIZE));
		int difXSqr = difX * difX;
		int difYSqr = difY * difY;
		plrDistance = (int) Math.sqrt(difXSqr + difYSqr);
		if(plrDistance < 5){
			attacking = true;
		}else{
			attacking = false;
		}
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
		this.setHitBox(x, y, width, height);
		attackCounter++;
		moveCounter++;
	}
	
	public Path getPath(){
		Map map = world.getMap();
		AStarPathFinder pathFinder = new AStarPathFinder(map, 100, false);
		Path path = pathFinder.findPath(null, this.getMapTileX(), this.getMapTileY(), 
			player.getMapTileX(), player.getMapTileY());
		return path;
	}
	
	public void move(int delta){
		//IF PLAYER IN SIGHT
		if(!(this.getMapPosX() == world.getPlayer().getMapPosX()
				&& this.getMapPosY() == world.getPlayer().getMapPosY()) && plrDistance < 10){	
			if(moved){
				CurPath = this.getPath();
				targetX = CurPath.getX(1);
				targetY = CurPath.getY(1);
				moved = false;
			}
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
		
	}
	
	@Override
	public void draw(Graphics g){
		if(CurPath != null){
			for (int l = 0; l < CurPath.getLength(); l++){
				g.drawRect(CurPath.getX(l)*32, CurPath.getY(l)*32, 5, 5);
			}
		}
		entityImage.draw(x,y);
		g.draw(hitBox);
	}
}
