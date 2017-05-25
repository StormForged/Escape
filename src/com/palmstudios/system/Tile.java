/**
 * 	@file
 * 
 * 	Base tile class. All tiles inherit from this and implement their own functionality.
 * 
 *	@date 2-4-2017
 *
 *	@author Curtis Maunder
 */
package com.palmstudios.system;

import java.awt.Graphics2D;

public abstract class Tile
{
	
	public static final int TILE_SIZE = 32; /**< Size of a single tile (both width and height) */
	
	/** TILE TYPE CONSTANTS */
	public static final int TILE_AIR		= 0;	/**< Air tile type */
	public static final int TILE_TREASURE 	= 1; 	/**< Treasure tile type */
	public static final int TILE_KEY		= 2;	/**< Key tile type */
	public static final int TILE_STAIR 		= 3;	/**< Stair tile type */
	public static final int TILE_SPIKE 		= 5;	/**< Spike tile type */
	public static final int TILE_FREESPIKE	= 6;	/**< Spike to pick up */
	public static final int WALL_LEFTTOP 		= 7;
	public static final int WALL_LEFTBOTTOM 	= 8;
	public static final int WALL_UP 			= 9;
	public static final int WALL_CENTRE			= 10;
	public static final int WALL_DOWNTOP		= 11;
	public static final int TILE_WALL			= 12;
	public static final int WALL_RIGHTTOP		= 13;
	public static final int WALL_RIGHTBOTTOM	= 14;
	public static final int WALL_HORIZONTAL		= 15;
	public static final int WALL_VERTICAL		= 16;
	public static final int WALL_EMPTY			= 17;
	public static final int WALL_CORNER1		= 18;
	public static final int WALL_CORNER2		= 19;
	public static final int WALL_CORNER3		= 20;
	public static final int WALL_CORNER4		= 21;

	
	
	public int 		type; 	/**< The type of this tile */
	public String 	name; 	/**< Name of this tile. Probably not worth bothering with */
	public boolean	solid;	/**< Will this tile block movement??? */
	
	/**
	 * Default constructor (for NULL Tile).
	 */
	public Tile()
	{
		this.type 	= TILE_AIR;
		this.solid 	= false;
		this.name 	= "NULL";
	}
	
	/**
	 * 
	 * @param type - The type of this tile (defined in Tile.java)
	 * @param solid - Will this tile allow an entity to pass through it?
	 */
	public Tile(int type, boolean solid)
	{
		this.type 	= type;
		this.solid 	= solid;
		this.name 	= "DEFAULT";
	}
	
	/**
	 * 
	 * @param type - The type of this tile (defined in Tile.java)
	 * @param solid - Will this tile allow an entity to pass through it?
	 * @param name - The name of this tile.
	 */
	public Tile(int type, boolean solid, String name)
	{
		this.type 	= type;
		this.solid 	= solid;
		this.name 	= name;
	}
	
	/**
	 * Draw this tile.
	 */
	public abstract void draw(Graphics2D g2d, int x, int y);
	
	/**
	 * Get the type of this tile 
	 */
	public int getType()
	{
		return this.type;
	}
}
