package org.msquirrel.SpaceShooter;

public class Camera {
	private float x;
	private float y;
	private float nextX;
	private float nextY;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void update(int delta){
		this.x = nextX;
		this.y = nextY;
	}
	
	public float getWindowX(float x){
		return x - this.x;
	}
	
	public float getWindowY(float y){
		return y - this.y;
	}
	
	public float getWorldX(float x){
		return x + this.x;
	}
	
	public float getWorldY(float y){
		return y + this.y;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
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
	
}
