package org.msquirrel.SpaceShooter.Entities.Effects;

import java.util.Random;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.msquirrel.SpaceShooter.Entities.Team;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Glow extends Effect{
	protected Graphics glowG;
	
	public Glow(float x, float y, World world, Entity parent) throws SlickException {
		super(x, y, world, parent);
		this.team = Team.OBJECT;
		this.entityImage = new Image("res/glow.png");
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException{
		Random colour = new Random();
		//this.x = parent.getX()-(parent.getWidth());
		//this.y = parent.getY()-(parent.getHeight());
		float r = (colour.nextInt(11)+5)/10;
		float gr = (colour.nextInt(11)+5)/10;
		float b = (colour.nextInt(11)+5)/10;
		this.entityImage.setColor(0, r, gr, b);
		this.entityImage.setColor(1, r, gr, b);
		this.entityImage.setColor(2, r, gr, b);
		this.entityImage.setColor(3, r, gr, b);
		if(!parent.isAlive() || parent == null){
			this.die();
		}
	}
	
	@Override
	public void die(){
		world.removeEntity(this);
	}
	
	@Override
	public void draw(Graphics g){
		g.setDrawMode(g.MODE_ADD_ALPHA);
		this.entityImage.draw(x-4,y-4,16,16);
		g.setDrawMode(g.MODE_NORMAL);
	}
}
