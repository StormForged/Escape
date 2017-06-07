/**
 * 	@file
 * 
 * 	Escape! Map Editor
 * 
 *	@date 28-5-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.palmstudios.system.GameState;
import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;
import com.palmstudios.tile.AirTile;
import com.palmstudios.tile.KeyTile;
import com.palmstudios.tile.NullTile;
import com.palmstudios.tile.SpikePickUp;
import com.palmstudios.tile.TreasureTile;
import com.palmstudios.tile.WallTile;

/**
 * @author Jesse
 *
 */
public class MapEditorState extends GameState
{
	private Map 	map;
	private Tile 	currTile;
	private int 	mx;
	private int		my;
	private int 	tileType;
	private int		wallType;
	private int 	wallID = 7;
	
	private ArrayList<Tile> tiles;
	
	private String[] tileNames = {"NULL", "Air", "Wall", "Spike", "Treasure", "Key"};
	
	private String[] wallNames = 	{"WALL_LEFTTOP", "WALL_LEFTBOTTOM", "WALL_UP", "WALL_CENTRE", "WALL_DOWNTOP", 
									 "WALL_DOWNBOTTOM", "WALL_RIGHTTOP", "WALL_RIGHTBOTTOM", "WALL_HORIZONTAL", "WALL_VERTICAL", 
									 "WALL_EMPTY", "WALL_CORNER1", "WALL_CORNER2", "WALL_CORNER3", "WALL_CORNER4", "WALL_LEFTVERTICAL",
									 "WALL_RIGHTVERTICAL", "WALL_BORDERBOTTOM", "WALL_BORDERTOP", "WALL_BORDERLEFT", "WALL_BORDERRIGHT"};

	public MapEditorState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init()
	{
		tiles = new ArrayList<Tile>();
		
		tiles.add(new NullTile());
		tiles.add(new AirTile());
		tiles.add(new WallTile(wallType, wallID));
		tiles.add(new SpikePickUp());
		tiles.add(new TreasureTile());
		tiles.add(new KeyTile());
		
		map = new Map();
		map.load("data/map/map1.txt");
		
		mx = 0;
		my = 0;
		tileType = 0;
		wallType = 0;
		
		currTile = tiles.get(0);
	}


	@Override
	public void update()
	{
		currTile = tiles.get(tileType);
		
		if(currTile.getType() >= 7)
		{
			currTile = new WallTile(wallType, wallID);
		}
	}


	@Override
	public void draw(Graphics2D g2d)
	{
		map.draw(g2d);
		
		g2d.setColor(Color.RED);
		g2d.drawRect(mx * Tile.TILE_SIZE, my * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("constantine", Font.PLAIN, 18));
		g2d.drawString("Tile Type: " + tileNames[tileType] + "      Wall Type: " + wallNames[wallType], 32, 32);
	}


	@Override
	public void keyPressed(int k)
	{
		
		if(k == KeyEvent.VK_ESCAPE)
		{
			FileWriter f = null;
			BufferedWriter bw = null;
			
			try
			{
				f = new FileWriter("data/map/map1.txt");
				bw = new BufferedWriter(f);
				
				for(int y = 0; y < Map.MAP_HEIGHT; y++)
				{
					for(int x = 0; x < Map.MAP_WIDTH; x++)
					{
						Tile t = map.getTileAt(x, y);
						
						if(y == 0)
						{
							bw.write("88"); // Write an invalid tile to force a NULL tile
						}
						else
						{
							bw.write(Integer.toString(t.getType()));
						}
						bw.write(" ");
					}
					
					bw.write("\n");
				}
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try
				{
					bw.close();
					f.close();	
				}
				catch(IOException ex)
				{
					
				}
			}

			
			gsm.unloadState(gsm.getNumberStates() - 1);
			gsm.changeState(GameStateManager.MENU_STATE);
		}
		
		if(k == KeyEvent.VK_UP)
		{
			if(my > 0)
				my--;
		}
		
		if(k == KeyEvent.VK_DOWN)
		{
			if(my < Map.MAP_HEIGHT)
				my++;
		}
		
		if(k == KeyEvent.VK_RIGHT)
		{
			if(mx < Map.MAP_WIDTH)
				mx++;
			
		}
		
		if(k == KeyEvent.VK_LEFT)
		{
			if(mx > 0)
				mx--;
		}
		
		if(k == KeyEvent.VK_ENTER)
		{
			map.setTileAt(mx, my, currTile);
		}
		
		if(k == KeyEvent.VK_SPACE)
		{
			if(tileType + 1 > tiles.size() - 1)
			{
				tileType = 0;
			}
			else
			{
				tileType++;
			}
		}
		
		if(k == KeyEvent.VK_F)
		{
			if(currTile.getType() >= 7)
			{
				if(wallType + 1 > wallNames.length - 1)
				{
					wallType = 0;
					wallID = 7;
				}
				else
				{
					wallType++;
					wallID++;
				}
			}
		}
		
		if(k == KeyEvent.VK_I)
		{
			System.out.println(map.getTileAt(mx, my).getType());
		}
	}

	@Override
	public void keyReleased(int k)
	{

	}

}
