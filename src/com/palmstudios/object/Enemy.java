/**
 * 	@file
 * 
 * 	Player object.
 * 
 *	@date 11-4-2017
 *
 *	@author Curtis Maunder
 */
package com.palmstudios.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.palmstudios.state.TestState;
import com.palmstudios.system.Tile;

/**
 * @author Curtis
 *
 */
public class Enemy extends Entity{
	
	public Enemy(String name, int x, int y)
	{
		super(name, x, y);
		this.name = name;
		this.x = x;
		this.y = y;
		
		sprite = loadSprite("enemy.png");
	}

	private BufferedImage sprite;
	private Random rand = new Random();
	
	private final int NORTH  = 1;
	private final int EAST = 2;
	private final int SOUTH = 3;
	private final int WEST = 4;
	
	private int x = 32;
	private int y = 64;
	private int direction = 2;

	private BufferedImage loadSprite(String path)
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


	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		switch(direction){
			case 1:
				if(TestState.map.getTileAt((x / Tile.TILE_SIZE), (y / Tile.TILE_SIZE)).getType() != Tile.TILE_WALL){
					move(0, -1);
				}else
					direction = rand.nextInt(4) + 1;
				break;
			
			case 2:
				if(TestState.map.getTileAt(((x + 32) / Tile.TILE_SIZE), ((y + 32) / Tile.TILE_SIZE)).getType() != Tile.TILE_WALL){
					move(1, 0);
				}else
					direction = rand.nextInt(4) + 1;
				break;
			case 3:
				if(TestState.map.getTileAt((x / Tile.TILE_SIZE), ((y + 64) / Tile.TILE_SIZE)).getType() != Tile.TILE_WALL){
					move(0, 1);
				}else
					direction = rand.nextInt(4) + 1;
				break;
			case 4:
				if(TestState.map.getTileAt(((x - 32) / Tile.TILE_SIZE), ((y + 32) / Tile.TILE_SIZE)).getType() != Tile.TILE_WALL){
					move(-1, 0);
				}else
					direction = rand.nextInt(4) + 1;
				break;
		}
		
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		// TODO Auto-generated method stub
		g2d.drawImage(sprite, x, y, null);
	}

	@Override
	public void move(double vx, double vy)
	{
		// TODO Auto-generated method stub\
		x += vx * Tile.TILE_SIZE;
		y += vy * Tile.TILE_SIZE;
		
	}

	@Override
	public int getX()
	{
		return x;
	}
	
	@Override
	public int getY(){
		return y;
	}
}
