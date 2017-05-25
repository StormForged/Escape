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
import java.util.Random;

import com.palmstudios.system.Art;
import com.palmstudios.system.Audio;
import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;
import com.palmstudios.tile.AirTile;

/**
 * @author Curtis
 *
 */
public class Enemy extends Entity
{
	
	public static final int ANIM_TIME = 20;
	
	private Random 			rand;
	
	private final int 		NORTH  	= 1;
	private final int 		EAST 	= 2;
	private final int 		SOUTH 	= 3;
	private final int 		WEST 	= 4;
	
	private int 			x;
	private int 			y;
	private int 			speed;
	private int 			direction;
	private BufferedImage 	currentFrame;
	private int				animTimer;
	private int				framePtr;
	private int				hurt;
	private int				wait;
	private int				slow;
	
	private Player player; 
	
	// AI Variables
	private boolean patrol;
	
	public Enemy(String name, Map map, Player player, int x, int y)
	{
		super(name, map, x, y);
		this.player = player;
		this.name = name;
		this.x = x;
		this.y = y;
		
		rand = new Random();
		
		framePtr 	= 0;
		animTimer 	= 0;
		
		speed = 32;
		patrol 	= true;
	}

	@Override
	public void update()
	{
		// Check for traps
		trapCheck();
		
		// Check if can move this update
		
		if(slow > 0){
			if(wait > 0){
				wait--;
				return;
			}else if(wait == 0){
				wait = 2 * slow;
			}
		}
		
		// Do Animation
		if(hurt == 0){
			currentFrame = (BufferedImage)Art.enemyFrames[framePtr][0];
		} else if (hurt > 1 && hurt <= 10){
			currentFrame = (BufferedImage)Art.enemyHurtFrames[framePtr][0];
		} else if (hurt > 10){
			currentFrame = (BufferedImage)Art.enemyReallyHurtFrames[framePtr][0];
		}
		animTimer++;
		
		if(animTimer >= ANIM_TIME)
		{
			if(framePtr + 1 > 3)
				framePtr = 0;
			else
				framePtr++;
			
			animTimer = 0;
		}
		else
		{
			return;
		}
		
		// Do A.I
		int dx = (player.getX() - x) / Tile.TILE_SIZE;
		int dy = (player.getY() - y) / Tile.TILE_SIZE;
		double tolerance = Math.sqrt((dx*dx) + (dy*dy));
		
		if(tolerance <= 6)
			patrol = false;
		else
			patrol = true;
		
		if(patrol)
		{
			// TODO: Random movement here!
			
			int currX = (x / Tile.TILE_SIZE);
			int currY = (y / Tile.TILE_SIZE);
			int nextX = 0;
			int nextY = 0;
			Tile t;
			
			direction = rand.nextInt(4) + 1;
			if(direction == NORTH)
			{
				nextY = currY;
			}
			else if(direction == EAST)
			{
				nextX = currX + 1;
			}
			else if(direction == SOUTH)
			{
				nextY = currY + 2;
			}
			else if(direction == WEST)
			{
				nextX = currX - 1;
			}
			
			
			if(map.getTileAt(nextX, currY).getType() == Tile.TILE_WALL)
			{
				// Try the next value
				nextX = currX - 1;
			}
			
			if(map.getTileAt(currX, nextY).getType() == Tile.TILE_WALL)
			{
				// Try the next value
				nextY = currY;
			}
			
			if(nextX > currX)
				move(32, 0);
			else if(nextX < currX)
				move(-32, 0);
			
			if(nextY > currY)
				move(0, 32);
			else if(nextY < currY)
				move(0, -32);
		}
		else // FOUND YA!
		{
			// Get Player's X and Y value (in tiles)
			int pTx = player.getX() / Tile.TILE_SIZE;
			int pTy = player.getY() / Tile.TILE_SIZE;;
			int ourTx = x / Tile.TILE_SIZE;
			int ourTy = y / Tile.TILE_SIZE;
			
			// Test each surrounding tile (in a one tile radius)
			
			// Move to player
			if(dx > 0)
			{
				move(speed, 0);
			}
			else if(dx < 0)
			{
				move(-speed, 0);
			}
			
			if(dy > 0)
			{
				move(0, speed);
			}
			else if(dy < 0)
			{
				move(0, -speed);
			}
			
		}
		
		if(hurt < 10 && slow > 1){
			slow--;
		}else if(hurt == 0 && slow == 1){
			slow--;
		}
		
		if(hurt > 0){
			hurt--;
		}
		
		playerCollisionCheck();
		//System.out.println("dx: " + dx + ", dy: " + dy + ", dist: " + tolerance);
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(currentFrame, x, y, null);
	}

	@Override
	public void move(double vx, double vy)
	{
		int currX = (int)((x) / Tile.TILE_SIZE);
		int currY = (int)((y) / Tile.TILE_SIZE) + 1;
		int nextX = (int)((x + vx) / Tile.TILE_SIZE);
		int nextY = (int)((y + vy) / Tile.TILE_SIZE) + 1;
		
		//System.out.println("currX: " + currX + ", currY: " + currY);
		//System.out.println("nextX: " + nextX + ", nextY: " + nextY);
		
		if(map.getTileAt(nextX, currY).getType() != Tile.TILE_WALL)
			x += vx;
		
		if(map.getTileAt(currX, nextY).getType() != Tile.TILE_WALL)
			y += vy;
	}
	
	public void trapCheck(){
		int currX = (int)((x) / Tile.TILE_SIZE);
		int currY = (int)((y) / Tile.TILE_SIZE) + 1;
		
		if(map.getTileAt(currX, currY).getType() == Tile.TILE_SPIKE){
			Audio.playSound("data/snd/scream.wav", 0);
			slow ++;
			wait = 2 * slow;
			hurt += 10;
			map.setTileAt(currX, currY, new AirTile()); //REALLY NAUGHTY!
		}
	}
	
	public void playerCollisionCheck(){
		if(player.getCollisionTick() == 0){
			if(x == player.getX() && y == player.getY()){
				player.hurtPlayer(1);
				player.resetCollionsTick();
			}
		}
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
}
