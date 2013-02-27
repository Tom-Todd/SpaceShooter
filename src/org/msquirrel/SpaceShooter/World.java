package org.msquirrel.SpaceShooter;

import java.util.ArrayList;
import java.util.List;

import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Player;
import org.msquirrel.SpaceShooter.Entities.bullet;
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
	
	public World() throws SlickException{
		player = new Player(100,100, this);
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
		player.draw(g);
		for(Entity entity : entities){
			entity.draw(g);
		}
	}
}
