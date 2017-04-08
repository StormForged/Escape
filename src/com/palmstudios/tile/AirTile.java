/**
 * 	@file
 * 
 * 	An air tile. All entities can pass through this (they are unblocked) 
 * 
 *	@date 7-4-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.palmstudios.system.Art;
import com.palmstudios.system.Tile;

public class AirTile extends Tile
{
	private BufferedImage sprite;
	
	public AirTile()
	{
		super(Tile.TILE_AIR, false, "Air");
	}
	
	@Override
	public void draw(Graphics2D g2d, int x, int y)
	{
		g2d.drawImage(Art.tiles[0][0], x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
	}

}
