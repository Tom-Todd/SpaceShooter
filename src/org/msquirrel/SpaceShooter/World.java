package org.msquirrel.SpaceShooter;

import java.util.ArrayList;
import java.util.List;

import org.msquirrel.SpaceShooter.Entities.EnemyBase;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Player;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.msquirrel.SpaceShooter.Entities.Projectiles.projectile;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class World {
	private static int WORLD_WIDTH;
	private static int WORLD_HEIGHT;
	public final List<Entity> entities = new ArrayList();
	public final List<projectile> projectiles = new ArrayList();
	private Map map;
	private Player player;
	private Camera cam;
	private EnemyBase enemy;
	private Image background;
	private Image lv1Map;
	
	public World() throws SlickException{
		this.cam = new Camera(0,0);
		map = new Map(cam);
		player = new Player(400,300, this);
		background = new Image("res/background.png");
		lv1Map = new Image("res/lv1Map.png");
		entities.add(player);
		map.addEnemies(this);
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
	
	public Player getPlayer(){
		return player;
	}
	
	public void draw(Graphics g){
		background.draw();
		g.translate(cam.getX(), cam.getY());
		map.draw(g);
		lv1Map.draw();
		for(Entity entity : entities){
			entity.draw(g);
		}
		for(projectile p : projectiles){
			p.draw(g);
		}
		g.setColor(Color.white);
		g.translate(-cam.getX(), -cam.getY());
		int enemies = entities.size() -1;
		String enemyNo = Integer.toString(enemies);
		g.drawString("Enemies- " + enemyNo, 10, 30);
	}

	public Camera getCam() {
		return cam;
	}
}
