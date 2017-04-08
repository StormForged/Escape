/**
 * 	@file
 * 
 * 	Null tile (just a black square). Drawn if something goes wrong or we hit an invalid ID.
 * 
 *	@date 4-7-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.tile;

import java.awt.Color;
import java.awt.Graphics2D;

import com.palmstudios.system.Tile;

public class NullTile extends Tile
{
	
	public NullTile()
	{
		super();
	}

	public void draw(Graphics2D g2d, int x, int y)
	{
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
	}

}
