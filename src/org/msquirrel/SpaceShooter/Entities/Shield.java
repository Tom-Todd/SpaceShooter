package org.msquirrel.SpaceShooter.Entities;

import org.msquirrel.SpaceShooter.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Shield extends Entity{
	protected Circle ShieldCircle;
	protected Entity parent;
	
	public Shield(float x, float y, World world, Entity parent) throws SlickException {
		super(x, y, world);
		this.entityImage = world.getImages().Shield;
		this.lifeTime = 300;
		ShieldCircle = new Circle(x-23, y-23, 30);
		this.parent = parent;
	}
	
	@Override
	public void update(GameContainer container, int Delta) throws SlickException{
		if(parent.shielded){
			this.x = parent.x;
			this.y = parent.y;
		}else{
			this.x = -100;
			this.y = -100;
		}
		ShieldCircle.setX(parent.x-23);
		ShieldCircle.setY(parent.y-23);
		collision();
		if(!this.parent.alive){
			this.die();
		}
	}
	
	public void draw(Graphics g){
		if(parent.shielded){
			g.setDrawMode(g.MODE_ADD_ALPHA);
			entityImage.draw(x-24, y-24, ShieldScale);
			g.setDrawMode(g.MODE_NORMAL);
			g.draw(ShieldCircle);
		}
	}
	
	@Override
	public void collision() throws SlickException{
		for(int i = 0; i < world.projectiles.size(); i++){
			if(this.ShieldCircle.intersects(world.projectiles.get(i).getHitBox())
					&& !(world.projectiles.get(i).getOrigin() == this.parent)){
				world.projectiles.get(i).die();
			}
		}
	}
}
