/**
 * 	@file
 * 
 *
 * 
 *	@date 
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.palmstudios.object.Enemy;
import com.palmstudios.object.Player;
import com.palmstudios.system.Audio;
import com.palmstudios.system.GameState;
import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;
import com.palmstudios.tile.KeyTile;
import com.palmstudios.tile.SpikePickUp;
import com.palmstudios.tile.TreasureTile;

/**
 * @author Jesse
 *
 */
public class PlayState extends GameState
{
	private final int			GAME_TIME = 6000;// This is determined by length of timer in minutes * 60 * 60 (eg. 7200 is 2mins)
	
	private GameStateManager 	gsm;
	private Map					map = new Map();
	private Player 				player;
	private ArrayList<Enemy> 	enemies;
	
	private int 				currentLevel;
	private int					time;
	private int					dropCooldown = 40;
	private boolean				pause = false;
	
	public PlayState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	public void init()
	{
		FileInputStream fis;
		try
		{
			Path optf = Paths.get("options.txt");
			BufferedReader reader = Files.newBufferedReader(optf);
			String line;
			int i = 0;
			
			while((line = reader.readLine()) != null)
			{
				if(i == 0)
					time = Integer.parseInt(line.split("=")[1]);
				
				if(i == 1)
					player = new Player("Player", map, 32, 64, Integer.parseInt(line.split("=")[1]), false, 0);
				
				i++;	
			}
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currentLevel = 1;
		map.load("data/map/map" + currentLevel + ".txt");
	
		
		enemies = new ArrayList<Enemy>();
		
		enemies.add(new Enemy("Goblin1", map, player, 32, 384));
		enemies.add(new Enemy("Goblin2", map, player, 320, 128));
		
		Audio.playSound("data/snd/start.wav", 0);
	}

	public void update()
	{
		if(!pause)
		{
			if((time / 60) % 10 == 0)
			{
				if(dropCooldown <= 0)
				{
					int tx = (int)Math.round(Math.random() * (Map.MAP_WIDTH - 1));
					int ty = (int)Math.round(Math.random() * (Map.MAP_HEIGHT - 1));
				
					if(map.getTileAt(tx, ty).getType() == Tile.TILE_AIR)
					{
						double chance = Math.random();
						
						if(chance <= 0.5)
						{
							map.setTileAt(tx, ty, new SpikePickUp());
						}
						else
						{
							
							map.setTileAt(tx, ty, new TreasureTile());
							tx = (int)Math.round(Math.random() * (Map.MAP_WIDTH - 1));
							ty = (int)Math.round(Math.random() * (Map.MAP_HEIGHT - 1));
							
							if(map.getTileAt(tx, ty).getType() == Tile.TILE_AIR)
								map.setTileAt(tx, ty, new KeyTile());
							
						}
					}
					dropCooldown = 40;
				}
				else
				{
					dropCooldown--;
				}
			}
			if(time <= 0)
			{
				gsm.loadState(new VictoryState(gsm, player.getScore()));
				gsm.changeState(gsm.getNumberStates() - 1);
			}
			
			if(player.getHealth() <= 0)
			{
				
				gsm.loadState(new DefeatState(gsm, player.getScore()));
				gsm.changeState(gsm.getNumberStates() - 1);
			}
			
			player.update();
			
			for(int i = 0; i < enemies.size(); i++)
			{
				enemies.get(i).update();
			}
			
			time--;
		}
	}

	public void draw(Graphics2D g2d)
	{
		map.draw(g2d);
		
		if(pause)
		{
			g2d.setColor(Color.WHITE);
			g2d.drawString("PAUSE", 200, 300);
		}
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("Health: " + player.getHealth() + "     Time: " + time / 60  + "     Traps: " + player.getTraps(), 0, 32);
		
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).draw(g2d);
		}
		
		player.draw(g2d);
	}

	public void keyPressed(int k)
	{
		if(pause == false)
		{
			if(k == KeyEvent.VK_W || k == KeyEvent.VK_UP)
			{
				player.move(0, -32);
			}
			
			if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT)
			{
				player.move(-32, 0);
			}
			
			if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN)
			{
				player.move(0, 32);
			}
			
			if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT)
			{
				player.move(32, 0);
			}
			
			if(k == KeyEvent.VK_E || k == KeyEvent.VK_SPACE){
				player.placeTrap();
			}
		}
		
		if(k == KeyEvent.VK_P)
		{
			if(pause == false)
				pause = true;
			else
				pause = false;
			
		}

		if (k == KeyEvent.VK_ESCAPE)
		{
			gsm.changeState(GameStateManager.MENU_STATE);
			gsm.unloadState(GameStateManager.PLAY_STATE);
		}
	}

	public void keyReleased(int k)
	{
		
	}
}
