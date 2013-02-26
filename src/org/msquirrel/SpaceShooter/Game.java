package org.msquirrel.SpaceShooter;

import org.msquirrel.SpaceShooter.Entities.Player;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	private Player player;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new Player(150, 100);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		player.update(container, delta);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.draw(g);
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
