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
	public static final int WALL_LEFTTOP 		= 0;
	public static final int WALL_LEFTBOTTOM 	= 1;
	public static final int WALL_UP 			= 2;
	public static final int WALL_CENTRE			= 3;
	public static final int WALL_DOWNTOP		= 4;
	public static final int WALL_DOWNBOTTOM		= 5;
	public static final int WALL_RIGHTTOP		= 6;
	public static final int WALL_RIGHTBOTTOM	= 7;
	public static final int WALL_HORIZONTAL		= 8;
	public static final int WALL_VERTICAL		= 9;
	public static final int WALL_EMPTY			= 10;
	public static final int WALL_CORNER1		= 11;
	public static final int WALL_CORNER2		= 12;
	public static final int WALL_CORNER3		= 13;
	public static final int WALL_CORNER4		= 14;
	public static final int WALL_LEFTVERTICAL	= 15;
	public static final int WALL_RIGHTVERTICAL  = 16;
	public static final int WALL_BORDERBOTTOM	= 17;
	public static final int WALL_BORDERTOP		= 18;
	public static final int WALL_BORDERLEFT		= 19;
	public static final int WALL_BORDERRIGHT	= 20;
	
	private Image 			sprite;	/**< The sprite of this wall tile */
	
	public WallTile(int wallType, int tileType)
	{
		super(tileType, true, "Wall");
		
		switch(wallType)
		{
		case WALL_LEFTTOP:
				sprite = Art.tiles[0][1];
				break;
		case WALL_LEFTBOTTOM:
			sprite = Art.tiles[0][2];
			break;
		case WALL_UP:
			sprite = Art.tiles[1][0];
			break;
		case WALL_CENTRE:
			sprite = Art.tiles[1][1];
			break;
		case WALL_DOWNTOP:
			sprite = Art.tiles[1][2];
			break;
		case WALL_DOWNBOTTOM:
			sprite = Art.tiles[1][3];
			break;
		case WALL_RIGHTTOP:
			sprite = Art.tiles[2][1];
			break;
		case WALL_RIGHTBOTTOM:
			sprite = Art.tiles[2][2];
			break;
		case WALL_HORIZONTAL:
			sprite = Art.tiles[0][3];
			break;
		case WALL_VERTICAL:
			sprite = Art.tiles[2][3];
			break;
		case WALL_EMPTY:
			sprite = Art.tiles[3][2];
			break;
		case WALL_CORNER1:
			sprite = Art.tiles[4][0];
			break;
		case WALL_CORNER2:
			sprite = Art.tiles[4][1];
			break;
		case WALL_CORNER3:
			sprite = Art.tiles[5][0];
			break;
		case WALL_CORNER4:
			sprite = Art.tiles[5][1];
			break;
		case WALL_LEFTVERTICAL:
			sprite = Art.tiles[4][2];
			break;
		case WALL_RIGHTVERTICAL:
			sprite = Art.tiles[5][2];
			break;
		case WALL_BORDERBOTTOM:
			sprite = Art.tiles[6][0];
			break;
		case WALL_BORDERTOP:
			sprite = Art.tiles[7][0];
			break;
		case WALL_BORDERLEFT:
			sprite = Art.tiles[7][1];
			break;
		case WALL_BORDERRIGHT:
			sprite = Art.tiles[6][1];
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
