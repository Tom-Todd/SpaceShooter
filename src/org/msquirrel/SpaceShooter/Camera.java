package org.msquirrel.SpaceShooter;

public class Camera {
	private float x;
	private float y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
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
	
}
