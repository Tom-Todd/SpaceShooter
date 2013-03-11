package org.msquirrel.SpaceShooter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {
	public Image player;
	public Image ShieldPickup;
	public Image Shield;
	public Image Bullet;
	public Image Key;
	public Image enemy;
	
	public ImageLoader() throws SlickException{
		player = new Image("res/Player.png");
		enemy = new Image("res/Player.png");
		ShieldPickup = new Image("res/Shield.png");
		Shield = new Image("res/playerShield.png");
		Bullet = new Image("res/bullet.png");
		Key = new Image("res/key.png");
	}
	
}
