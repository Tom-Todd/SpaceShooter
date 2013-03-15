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
	private boolean started = false;
	private boolean loading = true;
	private int loadCounter;
	private int lastScore;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		world = new World();
		container.setVSync(true);
		container.setTargetFrameRate(60);
		//container.setSmoothDeltas(true);
		//container.setFullscreen(true);
		container.setUpdateOnlyWhenVisible(true);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(!started){
			loading = true;
			loadCounter++;
			if(loadCounter > 100){
				started = true;
				loading = false;
			}
		}
		if(started){
			world.update(container, delta);
			if(!world.getPlayer().isAlive()){
				restart();
			}
		}
	}
	
	public void restart() throws SlickException{
		this.lastScore = world.getScore();
		world = new World();
		loadCounter = 0;
		started = false;
		loading = true;
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		world.draw(g);
		if(loading){
			String score = Integer.toString(lastScore);
			g.setColor(Color.black);
			g.fillRect(0, 0, container.getWidth(), container.getHeight());
			g.setColor(Color.white);
			g.drawString("Loading", 360, 300);
			g.drawString("Score: " + score, 360, 320);
		}
	}
	
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new Game("Space Shooter"));
			container.setDisplayMode(832, 640, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
