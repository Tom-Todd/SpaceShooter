package org.msquirrel.SpaceShooter;

import org.msquirrel.SpaceShooter.Entities.Entity;

public class Camera {
	private float x;
	private float y;
	private float nextX;
	private float nextY;
	private float scaleX = 1;
	private float scaleY = 1;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void update(int delta){
		this.x = nextX;
		this.y = nextY;
	}
	
	public void lookAt(Entity entity){
		this.x = -entity.getX()+(400);
		this.y = -entity.getY()+(300);
		this.nextX = -entity.getX()+(400);
		this.nextY = -entity.getY()+(300);
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

	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
}
