package org.msquirrel.SpaceShooter.Entities;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.msquirrel.SpaceShooter.TileMap.Map;

public class RayCasting {
  
    
    public static boolean tileVisible(int x1, int x2, int y1, int y2, Map map){
        
        int xinc1;
        int xinc2;
        int yinc1;
        int yinc2;
        int abs;
        int den;
        int num;
        int numadd;
        int numTiles;
        int deltax = Math.abs(x2 - x1);          
        int deltay = Math.abs(y2 - y1);         
        int x = x1;                                 
        int y = y1;                                 

        if (x2 >= x1)                           
        {
            xinc1 = 1;
            xinc2 = 1;
        }
        else                                              
        {
            xinc1 = -1;
            xinc2 = -1;
        }

        if (y2 >= y1)                          
        {
            yinc1 = 1;
            yinc2 = 1;
        }
        else                                              
        {
            yinc1 = -1;
            yinc2 = -1;
        }

        if (deltax >= deltay)           
        {
            xinc1 = 0;                              
            yinc2 = 0;                              
            den = deltax;
            num = deltax / 2;
            numadd = deltay;
            numTiles = deltax;           
        }
        else                                              
        {
            xinc2 = 0;                              
            yinc1 = 0;                              
            den = deltay;
            num = deltay / 2;
            numadd = deltax;
            numTiles = deltay;           
        }

        for (int currentTile = 0; currentTile <= numTiles; currentTile++)
        {
            if(x > -1 && x <= map.getWidthInTiles()){
                if(y > -1 && y <= map.getHeightInTiles()){
                    if(map.map[x][y] != null){
                        if(map.map[x][y].isBlocked()){
                            return false;
                        }
                    }
                }
            }
            num += numadd;                          
            if (num >= den)                       
            {
                num -= den;                     
                x += xinc1;                     
                y += yinc1;                     
            }
            x += xinc2;                           
            y += yinc2;                           
        }
        return true;
    }

}
