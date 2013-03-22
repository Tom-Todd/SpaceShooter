package org.msquirrel.SpaceShooter.Entities;

import java.io.File;
import java.io.IOException;

import org.lwjgl.util.vector.Vector2f;
import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Effects.Explosion;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileSafeZone;
import org.msquirrel.SpaceShooter.TileMap.Tiles.TileExit;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class Player extends Entity{
	
	public Player(float x, float y, World world) throws SlickException{
		super(x, y, world);
		this.setTeam(Team.PLAYER_TEAM);
		this.width = 16;
		this.height = 16;
		entityImage = new Image("res/PlayerSprites.png");
		hitBox = new Rectangle(x, y, this.width, this.height);
		this.Shield = new Shield(x, y, world, this);
		world.addEntity(Shield);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		movingUp = container.getInput().isKeyDown(Input.KEY_W);
		movingRight = container.getInput().isKeyDown(Input.KEY_D);
		movingLeft = container.getInput().isKeyDown(Input.KEY_A);	
		movingDown = container.getInput().isKeyDown(Input.KEY_S);
		if(!world.isLoadingMap()){
			if(container.getInput().isMouseButtonDown(1)){ //<------this line for windows
				//if(container.getInput().isKeyDown(Input.KEY_SPACE)){ //<---------this line for MAC version
				shielded = true;
			}else{
				shielded = false;
			}
			if(container.getInput().isMouseButtonDown(0) && attackCounter > 10){
				world.projectiles.add(new bullet(this.x+(this.width)/2, this.y+(this.height)/2, 
						(float)container.getInput().getMouseX() - cam.getX(), (float)container.getInput().getMouseY() - cam.getY(), 
						world, this));
				attackCounter = 0;
			}
			float deltaX = container.getInput().getMouseX() - (x+ cam.getX());
			float deltaY = container.getInput().getMouseY() - (y+ cam.getY());

			entityImage.setRotation((float) ((Math.toDegrees(Math.atan2(deltaY, deltaX))-90 )));
			attackCounter++;
			
			if(shielded && !(Shield.getLifeTime() <= 0)){
				Shield.setLifeTime(Shield.getLifeTime()-1);
			}
			if(Shield.getLifeTime() <= 0){
				shielded = false;
			}
			if(dying){
				this.deathCounter ++;
				if(deathCounter > 15){
					this.die();
				}
			}
			
			if(!world.isLoadingMap() && !dying){
				this.move(delta);
			}
			setHitBox(x, y, entityImage.getWidth(), entityImage.getHeight());
	
			if(map.map[this.getMapTileX()][this.getMapTileY()] instanceof TileSafeZone){
				this.inSafeZone = true;
			}else{
				this.inSafeZone = false;
			}
			
			if(map.map[this.getMapTileX()][this.getMapTileY()] instanceof TileExit){
				world.setLoadingMap(true);
				world.setTransitioningOut(true);
			}
		}
	}
	
	@Override
	public void move(int delta){
		
		moved = false;
		velocity.x = 0;
		velocity.y = 0;
		if(movingRight){
			velocity.x = -speed;
		}
		if(movingLeft){
			velocity.x = (speed);
		}
		if(movingUp){
			velocity.y = (speed);
		}
		if(movingDown){
			velocity.y = -speed;
		}
		
		if(movingRight && movingUp){
			velocity.x = (float) -Math.sqrt(((speed*speed)/2));
			velocity.y = (float) Math.sqrt(((speed*speed)/2));
		}
		if(movingLeft && movingUp){
			velocity.x = (float) Math.sqrt(((speed*speed)/2));
			velocity.y = (float) Math.sqrt(((speed*speed)/2));
		}
		if(movingRight && movingDown){
			velocity.x = (float) -Math.sqrt(((speed*speed)/2));
			velocity.y = (float) -Math.sqrt(((speed*speed)/2));
		}
		if(movingLeft && movingDown){
			velocity.x = (float) Math.sqrt(((speed*speed)/2));
			velocity.y = (float) -Math.sqrt(((speed*speed)/2));
		}
		if(!map.blocked(x-velocity.x*delta, y-velocity.y*delta, width, height)){
			nextX -= velocity.x*delta;
			nextY -= velocity.y*delta;
			this.cam.setNextX(this.cam.getX()+velocity.x*delta);
			this.cam.setNextY(this.cam.getY()+velocity.y*delta);
			moved = true;
		}
		if(!map.blocked(x-velocity.x*delta, y, width, height) && !moved){
			nextX -= velocity.x*delta;
			nextY = y;
			this.cam.setNextX(this.cam.getX()+velocity.x*delta);
			this.cam.setNextY(this.cam.getY());
			moved = true;
		}
		if(!map.blocked(x, y-velocity.y*delta, width, height) && !moved){
			nextX = x;
			nextY -= velocity.y*delta;
			
			this.cam.setNextX(this.cam.getX());
			this.cam.setNextY(this.cam.getY()+velocity.y*delta);
			moved = true;
		}
		if(!moved){
			nextX = x;
			nextY = y;
		}else{
			x = nextX;
			y = nextY;
			moved = false;
		}
	}
	
	@Override
	public void die(){
		this.alive = false;
	}

	@Override
	public void hit() throws SlickException{
		if(!shielded && !dying){
			world.addEntity(new Explosion(x+40, y+40, world, world.getWorldEntity(), 0, false, 0.5f));
			this.dying = true;
		}
		if(shielded){
			Shield.setLifeTime(Shield.getLifeTime()-20);
			if(Shield.getLifeTime() < 0){
				Shield.setLifeTime(0);
			}
		}
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.green);
		entityImage.draw(x,y);
		g.setColor(Color.black);
		if(this.shielded){
		}
		if(world.isDebugging()){
			g.draw(hitBox);
		}
	}	
}
