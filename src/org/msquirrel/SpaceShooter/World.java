package org.msquirrel.SpaceShooter;

import java.util.ArrayList;
import java.util.List;

import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class World {
	public static int WORLD_WIDTH;
	public static int WORLD_HEIGHT;
	public List<Entity> Entities = new ArrayList();
	public Map map = new Map();
	
	public World(){
		
	}
	
	public void update(GameContainer container, int delta){
		
	}
	
	public void addEntity(){

	}
	
	public void draw(Graphics g){
		
	}
}
