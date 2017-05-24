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

import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;
/**
 * @author Curtis
 *
 */
public class Player extends Entity{
	public BufferedImage sprite;
	
	public boolean 	hit = false;

	private int 	health;
	private boolean hasKey;
	private int 	traps;
	
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
	
	public Player(String name, Map map)
	{
		super(name, map);
	}
	
	public Player(String name, Map map, int x, int y, int health, boolean hasKey, int traps){
		super(name, map, x, y);	
		sprite = loadSprite("data/sprite/player.png");
		this.health = health;
		this.hasKey = hasKey;
		this.traps = traps;
	}

	@Override
	public void update()
	{
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(sprite, x, y, null);
	}

	@Override
	public void move(double vx, double vy)
	{
		x += vx;
		y += vy;
	}
	
	@Override
	public int getX()
	{
		return x;
	}
	
	@Override
	public int getY()
	{
		return y;
	}
	
	public int getHealth()
	{
		return health;	
	}
	
	public int getTraps()
	{
		return health;
	}
	
	public void hurtPlayer(int damage)
	{
		health -= damage;
	}
	
	public void setKey()
	{
		hasKey = true;
	}
	
	public Boolean hasKey()
	{
		return hasKey;
	}
	
	public Boolean placeTrap()
	{
		if(traps > 0){
			traps--;
			return true;
		}
		
		return false;
	}
}
