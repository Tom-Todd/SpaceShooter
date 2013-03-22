package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.Camera;
import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Effects.Effect;
import org.msquirrel.SpaceShooter.TileMap.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Entity {
	//Entity Values
	protected float speed = 0.2f;
	protected float x;
	protected float y;
	protected float nextX;
	protected float nextY;
	protected float width;
	protected float height;
	protected int lifeTime;
	protected Vector2f velocity;
	protected Team team = Team.NO_TEAM;
	//Entity Life/Death
	protected boolean alive = true;
	protected boolean dying;
	protected int deathCounter;
	//Movement
	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;
	protected boolean moved;
	protected boolean inSafeZone;
	//Other
	protected World world;
	protected Rectangle hitBox;
	protected Image entityImage;
	protected Camera cam;
	protected Map map;
	protected Effect effect;
	protected float ShieldScale = 1;
	protected Entity Shield;
	//Attack
	protected boolean attacking;
	protected int difficulty;
	protected int attackCounter;
	//Migrated Player Variables
	protected boolean shielded;
	
	
	
	public Entity(float x, float y, World world) throws SlickException{
		this.x = x;
		this.y = y;
		this.nextX = x;
		this.nextY = y;
		this.world = world;
		this.cam = world.getCam();
		this.map = world.getMap();
		this.hitBox = new Rectangle(x,y,10,10);
		entityImage = world.getImages().player;
		velocity = new Vector2f();
	}
	
	public void setHitBox(float x, float y, float width, float height){
		this.hitBox = new Rectangle(x,y,width,height);
	}
	
	public Rectangle getHitBox(){
		return hitBox;
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
	}
	
	public void move(int delta){
		velocity.x = 0;
		velocity.y = 0;
		if(movingRight){
			velocity.x = speed;
		}
		if(movingLeft){
			velocity.x = -(speed);
		}
		if(movingUp){
			velocity.y = -(speed);
		}
		if(movingDown){
			velocity.y = speed;
		}
		
		if(movingRight && movingUp){
			velocity.x = (float) Math.sqrt(((speed*speed)/2));
			velocity.y = (float) -Math.sqrt(((speed*speed)/2));
		}
		if(movingLeft && movingUp){
			velocity.x = (float) -Math.sqrt(((speed*speed)/2));
			velocity.y = (float) -Math.sqrt(((speed*speed)/2));
		}
		if(movingRight && movingDown){
			velocity.x = (float) Math.sqrt(((speed*speed)/2));
			velocity.y = (float) Math.sqrt(((speed*speed)/2));
		}
		if(movingLeft && movingDown){
			velocity.x = (float) -Math.sqrt(((speed*speed)/2));
			velocity.y = (float) Math.sqrt(((speed*speed)/2));
		}

		nextX += (velocity.x*delta);
		nextY += (velocity.y*delta);
		
		if(!world.getMap().blocked(nextX, nextY, width, height)){
			x = nextX;
			y = nextY;
		}else{
			x = x;
			y = y;
			nextX = x;
			nextY = y;
		}
	}
	
	public void draw(Graphics g){
		entityImage.draw(x,y);
		if(world.isDebugging()){
			g.draw(hitBox);
		}
	}

	//Getters and Setters
	public boolean isAlive() {
		return alive;
	}
	public float getMapPosX(){
		return x+cam.getX();
	}
	public float getMapPosY(){
		return y+cam.getY();
	}
	public int getMapTileX(){
		return (int) x/map.TILE_SIZE;
	}
	public int getMapTileY(){
		return (int) y/map.TILE_SIZE;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public void die() throws SlickException{
		world.entities.remove(this);
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getNextX() {
		return nextX;
	}
	public void setNextX(float nextX) {
		this.nextX = nextX;
	}
	public float getNextY() {
		return nextY;
	}
	public void setNextY(float nextY) {
		this.nextY = nextY;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public void hit() throws SlickException {
		
	}
	public void collision() throws SlickException{
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public Effect getEffect() {
		return effect;
	}
	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	public boolean isInSafeZone() {
		return inSafeZone;
	}
	public void setInSafeZone(boolean inSafeZone) {
		this.inSafeZone = inSafeZone;
	}
	public boolean isShielded() {
		return shielded;
	}
	public void setShielded(boolean shielded) {
		this.shielded = shielded;
	}
	public int getLifeTime() {
		return lifeTime;
	}
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}
	public Entity getShield() {
		return Shield;
	}
	public void setShield(Entity shield) {
		Shield = shield;
	}
}
