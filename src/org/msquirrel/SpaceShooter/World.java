package org.msquirrel.SpaceShooter;

import java.util.ArrayList;
import java.util.List;

import org.msquirrel.SpaceShooter.Entities.EnemyBase;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Player;
import org.msquirrel.SpaceShooter.Entities.Team;
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
	private int enemies;
	private int entityCount;
	private int Score;
	private ImageLoader images;
	
	public World() throws SlickException{
		this.cam = new Camera(50,200);
		images = new ImageLoader();
		map = new Map(cam);
		player = new Player(400,100, this);
		background = new Image("res/background.png");
		entities.add(player);
		map.addEnemies(this, 20);
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		enemies = 0;
		entityCount = entities.size() + projectiles.size();
		for(int i = 0; i < entities.size();i++){
			if(entities.get(i) != null){
				entities.get(i).update(container, delta);
			}
		}
		for(int i = 0; i < entities.size();i++){
			if(entities.get(i).getTeam() == Team.ENEMY_TEAM){
				enemies++;
			}
		}
		for(int i = 0; i < projectiles.size();i++){
			if(projectiles.get(i) != null){
				projectiles.get(i).update(container, delta);
			}
		}
		cam.update(delta);
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
	
	public int getEnemies(){
		return enemies;
	}
	
	public void setEnemies(int enemies){
		this.enemies = enemies;
	}
	
	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public ImageLoader getImages() {
		return images;
	}

	public void setImages(ImageLoader images) {
		this.images = images;
	}

	public void draw(Graphics g){
		background.draw();
		g.translate(cam.getX(), cam.getY());
		map.draw(g);
		for(Entity entity : entities){
			entity.draw(g);
		}
		for(projectile p : projectiles){
			p.draw(g);
		}
		g.setColor(Color.white);
		g.translate(-cam.getX(), -cam.getY());
		String enemyNo = Integer.toString(enemies);
		g.drawString("Enemies- " + enemyNo, 10, 30);
		String ec = Integer.toString(entityCount);
		g.drawString("Entities- " + ec, 10, 50);
	}

	public Camera getCam() {
		return cam;
	}

	public void loadMap() throws SlickException {
		map.loadMap(map.getCurrentMap()+1);
		if(map.getCurrentMap() == 1){
			player.setX(400);
			player.setY(100);
			player.setNextX(400);
			player.setNextY(100);
			cam.setX(50);
			cam.setY(200);
			cam.setNextX(50);
			cam.setNextY(200);
			map.addEnemies(this, 30);
		}
	}
}
