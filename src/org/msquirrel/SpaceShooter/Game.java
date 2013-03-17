package org.msquirrel.SpaceShooter;


import java.io.IOException;

import org.msquirrel.SpaceShooter.Entities.Player;
import org.msquirrel.SpaceShooter.Entities.Projectiles.bullet;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame{
	private World world;
	private boolean started = true;
	private boolean loading = true;
	private int loadCounter;
	private int lastScore;
	private Image ShieldBarSheet;
	private Image ShieldBarFull;
	private Image ShieldBarEmpty;
	private boolean restarted;
	private Player player;
	private Music inGame;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		world = new World(0);
		this.ShieldBarSheet = new Image("res/ShieldBar.png");
		this.ShieldBarFull = ShieldBarSheet.getSubImage(0, 0, ShieldBarSheet.getWidth(), ShieldBarSheet.getHeight()/2);
		this.ShieldBarEmpty = ShieldBarSheet.getSubImage(0, 24, 332, 21);
		container.setVSync(true);
		container.setTargetFrameRate(60);
		container.setSmoothDeltas(true);
		container.setUpdateOnlyWhenVisible(true);
		inGame = new Music("res/inGame.wav");
		container.setMusicVolume(0.5f);
		//inGame.play();
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			container.destroy();
		}
		if(container.getInput().isKeyPressed(Input.KEY_F1)){
			world.setDebugging(!world.isDebugging());
		}
		if(this.loading){
			if(!restarted){
				try {
					restart();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			loadCounter++;
			if(loadCounter > 70){
				loadCounter = 0;
				loading = false;
			}
		}
		if(started && !loading){
			restarted = false;
			world.update(container, delta);
			if(!world.getPlayer().isAlive()){
				this.loading = true;
			}
		}
		int r = (int)  (3.32*(world.getPlayer().getShieldCounter()/3));
		ShieldBarFull = ShieldBarSheet.getSubImage(0, 0, r, 21);
	}
	
	public void restart() throws SlickException, IOException{
		lastScore = world.getScore();
		System.out.println(lastScore);
		int shieldLife = world.getPlayer().getShieldCounter();
		world = new World(world.getMap().getCurrentMap());
		world.getPlayer().setShieldCounter(shieldLife);
		restarted = true;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		world.draw(g);
		ShieldBarEmpty.draw(450, 10);
		ShieldBarFull.draw(450, 10);
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
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
