/**
 * 	@file
 * 
 * 	An spike tile. All entities can pass through this (they are unblocked) 
 * 
 * 	If an entity passes through this tile, their movement speed will be slowed
 * 	This effect stacks and is independent from other effects
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

public class SpikeTile extends Tile
{
	private BufferedImage sprite;
	
	public SpikeTile()
	{
		super(Tile.TILE_SPIKE, false, "Spike");
	}
	
	@Override
	public void draw(Graphics2D g2d, int x, int y)
	{
		g2d.drawImage(Art.tiles[3][1], x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
	}

}

