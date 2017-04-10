/**
 * 	@file
 * 
 * 	Implementation of wall tiles that the player/monster can collide with.
 * 
 *	@date 10-4-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.tile;

import java.awt.Graphics2D;
import java.awt.Image;

import com.palmstudios.system.Art;
import com.palmstudios.system.Tile;

public class WallTile extends Tile
{	
	public static final int CORNER 	= 0;
	public static final int WALL	= 1;
	
	private Image 			sprite;	/**< The sprite of this wall tile */
	
	public WallTile(int wallType)
	{
		super(Tile.TILE_WALL, true, "Wall");
		
		switch(wallType)
		{
		case CORNER:
			sprite = Art.tiles[0][3];
			break;
		case WALL:
			sprite = Art.tiles[1][1];
			break;
		default: // If we get here we're in a bit of trouble....
			System.err.println("Invalid Wall Type!!");
			break;
		}
		
	}
	
	public void draw(Graphics2D g2d, int x, int y)
	{
		g2d.drawImage(sprite, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
	}

}
