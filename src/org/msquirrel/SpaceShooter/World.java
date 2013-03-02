package org.msquirrel.SpaceShooter;

import java.util.ArrayList;
import java.util.List;

import org.msquirrel.SpaceShooter.Entities.EnemyBase;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Player;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.msquirrel.SpaceShooter.Entities.Projectiles.projectile;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class World {
	private static int WORLD_WIDTH;
	private static int WORLD_HEIGHT;
	public final List<Entity> entities = new ArrayList();
	public final List<projectile> projectiles = new ArrayList();
	private Map map = new Map();
	private Player player;
	private EnemyBase enemy;
	
	public World() throws SlickException{
		player = new Player(100,100, this);
		entities.add(player);
		entities.add(new EnemyBase(200, 200, this, player));
		entities.add(new EnemyBase(250, 250, this, player));
		entities.add(new EnemyBase(400, 500, this, player));
		entities.add(new EnemyBase(500, 300, this, player));
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		for(int i = 0; i < entities.size();i++){
			if(entities.get(i) != null){
				entities.get(i).update(container, delta);
			}
		}
		for(int i = 0; i < projectiles.size();i++){
			if(projectiles.get(i) != null){
				projectiles.get(i).update(container, delta);
			}
		}
		
	}
	
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	public Map getMap(){
		return map;
	}
	
	public void draw(Graphics g){
		map.draw(g);
		for(Entity entity : entities){
			entity.draw(g);
		}
		for(projectile p : projectiles){
			p.draw(g);
		}
	}
}
