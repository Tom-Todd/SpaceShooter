package org.msquirrel.SpaceShooter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.msquirrel.SpaceShooter.Entities.Boss;
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
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
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
	private boolean loadingMap;
	private boolean transitioningOut;
	private boolean transitioningIn;
	private int transitionTimer = 1;
	private boolean debugging;
	private Boss boss;
	private Entity worldEntity;
	private boolean gameEnded;
	private int gameEndCounter;
	
	public World(int Map) throws SlickException{
		this.cam = new Camera(50,200);
		images = new ImageLoader();
		map = new Map(cam, Map);
		player = new Player(400,100, this);
		background = new Image("res/background.png");
		background.setFilter(Image.FILTER_NEAREST);
		entities.add(player);
		cam.lookAt(player);
		worldEntity = new Entity(0, 0, this);
		if(Map == 0){
			map.addEnemies(this, 20);
		}
		if(Map == 1){
			map.addEnemies(this, 30);
			player.setX(400);
			player.setY(100);
			player.setNextX(400);
			player.setNextY(100);
			cam.lookAt(player);
		}
		if(Map == 2){
			player.setX(1025);
			player.setY(1870);
			player.setNextX(1020);
			player.setNextY(1870);
			cam.lookAt(player);
			map.addEnemies(this, 0);
			map.openDoors();
		}
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
		if(loadingMap){
			loadMap(map.getCurrentMap()+1);
		}
		if(player.getY() < 1093 && map.getCurrentMap() == 2){
			map.closeDoors();
		}
		if(boss != null){
			if(!boss.isAlive()){
				gameEndCounter ++;
				if(this.gameEndCounter > 500){
					this.gameEnded = true;
				}
			}
		}
	}
	
	public void draw(Graphics g){
		background.draw();
		g.scale(cam.getScaleX(), cam.getScaleY());
		g.translate(cam.getX(), cam.getY());
		map.draw(g);
		for(Entity entity : entities){
			entity.draw(g);
		}
		for(projectile p : projectiles){
			p.draw(g);
		}
		g.setColor(Color.white);
		g.resetTransform();
		if(debugging){
			String enemyNo = Integer.toString(enemies);
			g.drawString("Enemies- " + enemyNo, 10, 30);
			String ec = Integer.toString(entityCount);
			g.drawString("Entities- " + ec, 10, 50);
			String px = Float.toString(player.getX());
			g.drawString("PlayerX- " + px, 10, 70);
			String py = Float.toString(player.getY());
			g.drawString("PlayerY- " + py, 10, 90);
		}
	}

	public void loadMap(int Map) throws SlickException {
		if(transitioningOut){
			cam.setScaleX(1-(0.01f*transitionTimer));
			cam.setScaleY(1-(0.01f*transitionTimer));
			transitionTimer++;
			if(transitionTimer > 100){
				transitionTimer = 1;
				transitioningOut = false;
			}
		}
		
		if(!transitioningOut && !transitioningIn){
			map.loadMap(Map);
			if(map.getCurrentMap() == 1){
				for(int i = 0; i < entities.size();i++){
					if(entities.get(i) != null){
						entities.get(i).setDifficulty(1);
					}
				}
				player.setX(400);
				player.setY(100);
				player.setNextX(400);
				player.setNextY(100);
				cam.lookAt(player);
				map.addEnemies(this, 30);
				transitioningIn = true;
			}
			if(map.getCurrentMap() == 2){
				for(int i = 0; i < entities.size();i++){
					if(entities.get(i) != null){
						entities.get(i).setDifficulty(1);
					}
				}
				player.setX(1000);
				player.setY(1850);
				player.setNextX(1000);
				player.setNextY(1850);
				cam.lookAt(player);
				map.addEnemies(this, 0);
				map.openDoors();
				transitioningIn = true;
			}
		}
		if(transitioningIn){
			transitionTimer++;
			cam.setScaleX(0.01f*transitionTimer);
			cam.setScaleY(0.01f*transitionTimer);
			if(transitionTimer > 100){
				transitionTimer = 1;
				loadingMap = false;
				transitioningIn = false;
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
	public boolean isTransitioningOut() {
		return transitioningOut;
	}
	public void setTransitioningOut(boolean transitioningOut) {
		this.transitioningOut = transitioningOut;
	}
	public boolean isLoadingMap() {
		return loadingMap;
	}
	public void setLoadingMap(boolean loadingMap) {
		this.loadingMap = loadingMap;
	}
	public Camera getCam() {
		return cam;
	}
	public boolean isDebugging() {
		return debugging;
	}
	public void setDebugging(boolean debugging) {
		this.debugging = debugging;
	}
	public Entity getWorldEntity() {
		return worldEntity;
	}
	public void setWorldEntity(Entity worldEntity) {
		this.worldEntity = worldEntity;
	}
	public boolean isGameEnded() {
		return gameEnded;
	}
	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public int getGameEndCounter() {
		return gameEndCounter;
	}

	public void setGameEndCounter(int gameEndCounter) {
		this.gameEndCounter = gameEndCounter;
	}
	
}
