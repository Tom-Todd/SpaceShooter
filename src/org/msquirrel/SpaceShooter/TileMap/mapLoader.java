package org.msquirrel.SpaceShooter.TileMap;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;;

public class mapLoader {
	
	public static Bitmap loadBitmap(String fileName){
		try{
			BufferedImage img = ImageIO.read(mapLoader.class.getResource(fileName));

			int w = img.getWidth();
			int h = img.getHeight();
                        
			Bitmap result = new Bitmap(w, h);
			img.getRGB(0, 0, w, h, result.pixels, 0, w);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
