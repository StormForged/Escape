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

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.palmstudios.object.Enemy;
import com.palmstudios.object.Player;
import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;
import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;
import com.palmstudios.tile.SpikeTile;

/**
 * @author Jesse
 *
 */
public class PlayState extends GameState
{
	private GameStateManager 	gsm;
	private Map					map = new Map();
	private Player 				player;
	private ArrayList<Enemy> 	enemies;
	
	int currentLevel;
	
	public PlayState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	public void init()
	{
		currentLevel = 1;
		map.load("data/map/map" + currentLevel + ".txt");
	
		player = new Player("Player", map, 32, 64, 5, false, 0);
		enemies = new ArrayList<Enemy>();
		
		enemies.add(new Enemy("Goblin1", map, player, 32, 384));
		//enemies.add(new Enemy("Goblin2", map, 32, 384));
	}

	public void update()
	{
		player.update();
		
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).update();
		}
	}

	public void draw(Graphics2D g2d)
	{
		map.draw(g2d);

		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).draw(g2d);
		}
		
		player.draw(g2d);
	}

	public void keyPressed(int k)
	{
		if (k == KeyEvent.VK_W && player.getY() != 0
				&& map.getTileAt((player.getX() / Tile.TILE_SIZE), (player.getY() / Tile.TILE_SIZE))
						.getType() != Tile.TILE_WALL)
			player.move(0, -32);
		if (k == KeyEvent.VK_A && player.getX() != 0
				&& map.getTileAt(((player.getX() - 32) / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
						.getType() != Tile.TILE_WALL)
			player.move(-32, 0);
		if (k == KeyEvent.VK_S && player.getY() != (GamePanel.HEIGHT - (Tile.TILE_SIZE + Tile.TILE_SIZE))
				&& map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 64) / Tile.TILE_SIZE))
						.getType() != Tile.TILE_WALL)
			player.move(0, 32);
		if (k == KeyEvent.VK_D && player.getX() != GamePanel.WIDTH - Tile.TILE_SIZE
				&& map.getTileAt(((player.getX() + 32) / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
						.getType() != Tile.TILE_WALL)
			player.move(32, 0);

		if (k == KeyEvent.VK_E && player.placeTrap()
				&& map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
						.getType() == Tile.TILE_AIR)
		{
			map.setTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE), new SpikeTile());
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
