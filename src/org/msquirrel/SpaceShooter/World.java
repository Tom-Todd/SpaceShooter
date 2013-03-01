package org.msquirrel.SpaceShooter;

import java.util.ArrayList;
import java.util.List;

import org.msquirrel.SpaceShooter.Entities.Enemy;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Player;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class World {
	private static int WORLD_WIDTH;
	private static int WORLD_HEIGHT;
	public final List<Entity> entities = new ArrayList();
	private Map map = new Map();
	private Player player;
	private Enemy enemy;
	
	public World() throws SlickException{
		player = new Player(100,100, this);
		/*entities.add(new Enemy(200, 200, this, player));
		entities.add(new Enemy(250, 250, this, player));
		entities.add(new Enemy(400, 500, this, player));
		entities.add(new Enemy(700, 400, this, player));
		entities.add(new Enemy(500, 300, this, player));
		entities.add(new Enemy(50, 20, this, player));*/
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		player.update(container, delta);
		for(int i = 0; i < entities.size();i++){
			if(entities.get(i) != null){
				entities.get(i).update(container, delta);
			}
		}
		
	}
	
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	public void draw(Graphics g){
		map.draw(g);
		player.draw(g);	
		for(Entity entity : entities){
			entity.draw(g);
		}
	}
}
