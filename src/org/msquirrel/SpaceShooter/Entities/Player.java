package org.msquirrel.SpaceShooter.Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Player extends Entity{
	private float x;
	private float y;
	
	public Player(float x, float y) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	public void update(GameContainer container, Graphics g){
		
	}
	
	public void draw(Graphics g){
		g.drawRect(x, y, 10, 10);
	}
}
