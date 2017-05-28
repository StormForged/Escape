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

import com.palmstudios.system.Art;
import com.palmstudios.system.Audio;
import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;
import com.palmstudios.tile.AirTile;
import com.palmstudios.tile.SpikeTile;
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
	private int		trapIFrame;
	
	private int		hurt;
	private int		wait;
	private int		slow;
	private int		collisionTick;
	private int		score = 0;
	
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
		if(hurt == 0){
			sprite = loadSprite("data/sprite/player.png");
		} else if (hurt > 1 && hurt <= (10 * 30)){
			sprite = loadSprite("data/sprite/playerhurt.png");
		} else if (hurt > (10 * 30)){
			sprite = loadSprite("data/sprite/playerreallyhurt.png");
		}
		
		if(map.getTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1).getType() == Tile.TILE_KEY)
		{
			hasKey = true;
			map.setTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1, new AirTile()); //REALLY NAUGHTY!
			Audio.playSound("data/snd/pickup.wav", 0);
			score += 100;
		}
		
		if(map.getTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1).getType() == Tile.TILE_TREASURE && hasKey)
		{
			hasKey = false;
			map.setTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1, new AirTile()); //REALLY NAUGHTY!
			Audio.playSound("data/snd/spikey.wav", 0);
			score += 500;
		}
		
		if(map.getTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1).getType() == Tile.TILE_FREESPIKE){
			traps += 2;
			map.setTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1, new AirTile()); //REALLY NAUGHTY!
			Audio.playSound("data/snd/spikey.wav", 0);
			score += 50;
		}
		
		trapCheck();		
		
		if(hurt < 10 && slow > 1){
			slow--;
		}else if(hurt == 0 && slow == 1){
			slow--;
		}
		
		decrements();
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(sprite, x, y, null);
		if(hurt > 0){
			g2d.drawString("" + wait / 4, x, y);
		}
	}

	@Override
	public void move(double vx, double vy)
	{
		if(slow > 0){
			if(wait > 0){
				return;
			}else if(wait == 0){
				wait = 10 * slow;
			}
		}
		
		int currX = (int)((x) / Tile.TILE_SIZE);
		int currY = (int)((y) / Tile.TILE_SIZE) + 1;
		int nextX = (int)((x + vx) / Tile.TILE_SIZE);
		int nextY = (int)((y + vy) / Tile.TILE_SIZE) + 1;
		
		//System.out.println("currX: " + currX + ", currY: " + currY);
		//System.out.println("nextX: " + nextX + ", nextY: " + nextY);
		
		if(map.getTileAt(nextX, currY).getType() < 7)
			x += vx;
		
		if(map.getTileAt(currX, nextY).getType() < 7)
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
		return traps;
	}
	
	public int getCollisionTick(){
		return collisionTick;
	}
	
	public void resetCollionsTick(){
		this.collisionTick = 30;
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
	
	public int getScore()
	{
		return score;
	}
	
	public Boolean placeTrap()
	{
		if(traps > 0 && map.getTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1).getType() != Tile.TILE_SPIKE){
			traps--;
			map.setTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1, new SpikeTile()); //REALLY NAUGHTY!
			trapIFrame = 60;
			return true;
		}
		
		return false;
	}

	public void trapCheck(){
		if(map.getTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1).getType() == Tile.TILE_SPIKE && trapIFrame == 0){
			map.setTileAt(x / Tile.TILE_SIZE, (y / Tile.TILE_SIZE) + 1, new AirTile()); //REALLY NAUGHTY!
			slow ++;
			wait = 10 * slow;
			hurt += 20 * 30;
		}
	}
	
	public void decrements(){
		if(hurt > 0){
			hurt--;
		}
		
		if(wait > 0){
			wait--;
		}
		
		if(trapIFrame > 0){
			trapIFrame--;
		}
		
		if(collisionTick > 0){
			collisionTick--;
		}
	}
}
