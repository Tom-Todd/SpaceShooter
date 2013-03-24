package org.msquirrel.SpaceShooter.Entities.Effects;

import org.msquirrel.SpaceShooter.World;
import org.msquirrel.SpaceShooter.Entities.Entity;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

public class Explosion extends Effect{
	private Animation explosion;
	private SpriteSheet sprites;
	private int delay;
	private int count;
	private Circle damageRadius;
	private boolean damaging;
	private float scale;
	private Sound sound;
	
	public Explosion(float x, float y, World world, Entity parent, int Delay, boolean damaging, float scale)throws SlickException {
		super(x, y, world, parent);
		this.sound = new Sound("res/explosion.wav");
		sound.play();
		sprites = new SpriteSheet("res/explosion.png", 59, 59);
		this.scale = scale;
		explosion = new Animation(new Image[]{sprites.getSprite(0, 0),
				sprites.getSprite(1, 0),sprites.getSprite(2, 0)}, 100);
		explosion.setLooping(false);
		this.delay = Delay;
		this.damaging = damaging;
		if(damaging){
			damageRadius = new Circle(x, y, 64*scale);
		}
		if(!damaging){
			damageRadius = new Circle(0, 0, 64*scale);
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException{
		if(count > delay){
			if(explosion.getFrame() == explosion.getFrameCount()-1){
				world.entities.remove(this);
				this.die();
			}
		}
		if(world.getPlayer().getHitBox().intersects(this.damageRadius) && damaging){
			world.getPlayer().hit();
		}
		count++;
	}
	
	@Override
	public void draw(Graphics g){
		if(count > delay){
			explosion.draw(x-60, y-60, 128*scale, 128*scale);
		}
		if(world.isDebugging()){
			g.draw(damageRadius);
		}
	}

	public Circle getDamageRadius(){
		return damageRadius;
	}
	public void setDamageRadius(Circle damageRadius){
		this.damageRadius = damageRadius;
	}
}
