package org.msquirrel.SpaceShooter;


import org.msquirrel.SpaceShooter.Entities.Player;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame{
	private World world;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		world = new World();
		container.setVSync(true);
		container.setTargetFrameRate(60);
		//container.setFullscreen(true);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		world.update(container, delta);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		world.draw(g);
	}
	
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new Game("Space Shooter"));
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
