/**
 * 	@file
 * 
 * 	An treasure tile. All entities can pass through this (they are unblocked) 
 * 
 * 	If the player passes through this tile, their score will increase
 * 
 *	@date 03-05-2017
 *
 *	@author Curtis Maunder
 */
package com.palmstudios.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.palmstudios.system.Art;
import com.palmstudios.system.Tile;

public class TreasureTile extends Tile
{
	private BufferedImage sprite;
	
	public TreasureTile()
	{
		super(Tile.TILE_TREASURE, false, "Treasure");
	}
	
	@Override
	public void draw(Graphics2D g2d, int x, int y)
	{
		g2d.drawImage(Art.tiles[3][0], x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
	}

}
