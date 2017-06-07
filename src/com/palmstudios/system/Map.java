/**
 * 	@file
 * 
 * 	Map class. This is instantiated when we load a new map in the main Game state.
 * 
 *	@date 3-4-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.system;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.rmi.CORBA.Tie;

import com.palmstudios.tile.AirTile;
import com.palmstudios.tile.KeyTile;
import com.palmstudios.tile.NullTile;
import com.palmstudios.tile.SpikePickUp;
import com.palmstudios.tile.SpikeTile;
import com.palmstudios.tile.StairTile;
import com.palmstudios.tile.TreasureTile;
import com.palmstudios.tile.WallTile;

public class Map
{
	/**
	 * How many tiles we can fit into the width and height of the map
	 */
	public static final int MAP_WIDTH 	= GamePanel.WIDTH / Tile.TILE_SIZE;
	public static final int MAP_HEIGHT 	= GamePanel.HEIGHT / Tile.TILE_SIZE;
	
	private String mapName;		/**< Name of the map we have loaded */
	private Tile[][] map; 		/**< Tile map */
	
	/**
	 * Default constructor
	 */
	public Map()
	{
		map = new Tile[MAP_WIDTH][MAP_HEIGHT];
	}
	
	/**
	 * Class Constructor
	 * @param mapName - Level name (i.e "Crypt of Despair")
	 */
	public Map(String mapName, String path)
	{
		this.mapName = mapName;
		map = new Tile[MAP_WIDTH][MAP_HEIGHT];
		load(path);
	}
	
	/**
	 * Load a map from disk and convert the integer values into tiles.
	 * 
	 * @param path - File path of the map to load.
	 * @return Returns true if map was successfully loaded from disk, false otherwise.
	 */
	public boolean load(String path)
	{
		try
		{
			BufferedReader 	br = new BufferedReader(new FileReader(path));
			String 			currentLine;
			String[] 		row;
			
			Tile t = null; // Local tile. Initialised properly when we walk the map
			int x = 0; // x pos counter
			int y = 0; // y pos counter
			
			while((currentLine = br.readLine()) != null) // Read each l
			{
				if(currentLine.isEmpty())
					continue;

				row = currentLine.trim().split(" "); // Each value is split with a space (' ')
				
				x = 0; // Reset x location so we don't overrun the array!
				
				for(String value : row)
				{
					int id = Integer.parseInt(value); // Get tile type
					
					switch(id) // Create a new tile depending on what type we hit and initialise it as such.
					{
					case Tile.TILE_AIR:
						t = new AirTile();
						break;
					case Tile.TILE_WALL:
						t = new WallTile(WallTile.WALL_DOWNBOTTOM, id);
						break;
					case Tile.TILE_KEY:
						t = new KeyTile();
						break;
					case Tile.TILE_FREESPIKE:
						t = new SpikePickUp();
						break;
					case Tile.TILE_TREASURE:
						t = new TreasureTile();
						break;
					case Tile.TILE_SPIKE:
						t = new SpikeTile();
						break;
					case Tile.WALL_LEFTTOP:
						t = new WallTile(WallTile.WALL_LEFTTOP, id);
						break;
					case Tile.WALL_LEFTBOTTOM:
						t = new WallTile(WallTile.WALL_LEFTBOTTOM, id);
						break;
					case Tile.WALL_UP:
						t = new WallTile(WallTile.WALL_UP, id);
						break;
					case Tile.WALL_CENTRE:
						t = new WallTile(WallTile.WALL_CENTRE, id);
						break;
					case Tile.WALL_DOWNTOP:
						t = new WallTile(WallTile.WALL_DOWNTOP, id);
						break;
					case Tile.WALL_RIGHTTOP:
						t = new WallTile(WallTile.WALL_RIGHTTOP, id);
						break;
					case Tile.WALL_RIGHTBOTTOM:
						t = new WallTile(WallTile.WALL_RIGHTBOTTOM, id);
						break;
					case Tile.WALL_HORIZONTAL:
						t = new WallTile(WallTile.WALL_HORIZONTAL, id);
						break;
					case Tile.WALL_VERTICAL:
						t = new WallTile(WallTile.WALL_VERTICAL, id);
						break;
					case Tile.WALL_EMPTY:
						t = new WallTile(WallTile.WALL_EMPTY, id);
						break;
					case Tile.WALL_CORNER1:
						t = new WallTile(WallTile.WALL_CORNER1, id);
						break;
					case Tile.WALL_CORNER2:
						t = new WallTile(WallTile.WALL_CORNER2, id);
						break;
					case Tile.WALL_CORNER3:
						t = new WallTile(WallTile.WALL_CORNER3, id);
						break;
					case Tile.WALL_CORNER4:
						t = new WallTile(WallTile.WALL_CORNER4, id);
						break;
					case Tile.WALL_LEFTVERTICAL:
						t = new WallTile(WallTile.WALL_LEFTVERTICAL, id);
						break;
					case Tile.WALL_RIGHTVERTICAL:
						t = new WallTile(WallTile.WALL_RIGHTVERTICAL, id);
						break;
					case Tile.WALL_BORDERBOTTOM:
						t = new WallTile(WallTile.WALL_BORDERBOTTOM, id);
						break;
					case Tile.WALL_BORDERTOP:
						t = new WallTile(WallTile.WALL_BORDERTOP, id);
						break;
					case Tile.WALL_BORDERLEFT:
						t = new WallTile(WallTile.WALL_BORDERLEFT, id);
						break;
					case Tile.WALL_BORDERRIGHT:
						t = new WallTile(WallTile.WALL_BORDERRIGHT, id);
						break;
					case 88:
						t = new NullTile();
						break;
					default:
						t = new NullTile();
						System.err.println("INVALID TILE NUMBER: " + id + "!!!");
						break;
					}
					
					map[x][y] = t; // Store this tile in the map
					x++;
				}
				
				y++;
			}
			
			br.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * Draw this map.
	 * @param g2d - Graphics object from Framebuffer
	 */
	public void draw(Graphics2D g2d)
	{
		for(int y = 0; y < MAP_HEIGHT; y++)
		{
			for(int x = 0; x < MAP_WIDTH; x++)
			{
				map[x][y].draw(g2d, x, y);
			}
		}
	}
	
	/**
	 * 
	 * @param tileX - The x position of the tile (in tile co-ords)
	 * @param tileY - The y position of the tile (int tile co-ords)
	 * @return Map index at tileX and tileY
	 */
	public Tile getTileAt(int tileX, int tileY)
	{
		return map[tileX][tileY];
	}
	
	/**
	 * 
	 * @param tileX - The x position of the tile (in tile co-ords)
	 * @param tileY - The y position of the tile (int tile co-ords)
	 * @param t - Tile to add to map
	 */
	public void setTileAt(int tileX, int tileY, Tile t)
	{
		map[tileX][tileY] = t;
	}
}
