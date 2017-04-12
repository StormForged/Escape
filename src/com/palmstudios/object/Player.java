/**
 * 	@file
 * 
 * 	Player object.
 * 
 *	@date 4-6-2017
 *
 *	@author Curtis Maunder
 */
package com.palmstudios.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.palmstudios.system.Tile;
/**
 * @author Curtis
 *
 */
public class Player extends Entity{
	public BufferedImage sprite;
	
	public boolean hit = false;
	int x = 32;
	int y = 64;
	
	public BufferedImage loadSprite(String path)
	{	
		try
		{
			return ImageIO.read(new File(path));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Load the image
		return sprite;
	}
	
	public Player(String name){
		super(name);	
		sprite = loadSprite("player.png");
	}

	@Override
	public void update(){
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics2D g2d){
		g2d.drawImage(sprite, x, y, null);
	}

	@Override
	public void move(double vx, double vy){
		x += vx * Tile.TILE_SIZE;
		y += vy * Tile.TILE_SIZE;
		System.out.println("x:" + x);
		System.out.println("y:" + y);
	}
	
	@Override
	public int getX(){
		return x;
	}
	
	@Override
	public int getY(){
		return y;
	}
}
