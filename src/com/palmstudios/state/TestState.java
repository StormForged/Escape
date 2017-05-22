/**
 * 	@file
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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.palmstudios.object.Enemy;
import com.palmstudios.object.Player;
import com.palmstudios.system.Art;
import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;
import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;
import com.palmstudios.tile.AirTile;
import com.palmstudios.tile.SpikeTile;

/**
 * @author Jesse
 *
 */
public class TestState extends GameState
{
	public static Map map = new Map();
	private Player player = new Player("player", 32, 64, 5, false, 2);
	private int levelTimer = 7200; // This is determined by length of timer in
									// minutes * 60 * 60 (eg. 7200 is 2mins)
	private int enemyTimer = 0;
	private int collisionTick = 0;
	private int score = 0;
	private int currentScore = 0;
	private String scoreFile = "score.txt";

	public void load(String path)
	{
		try
		{
			Scanner scanner = new Scanner(new File(path));

			currentScore = scanner.nextInt();

			scanner.close();
		} catch (IOException e)
		{

		}
		;
	}

	public void saveScore(String path, int score)
	{
		try
		{
			FileWriter fw = new FileWriter(path);
			PrintWriter pw = new PrintWriter(fw);

			pw.println(score);

			pw.close();
		} catch (IOException e)
		{

		}
	}

	Enemy[] enemies = new Enemy[]
	{ new Enemy("Steve", 32, 384), new Enemy("Barry", 448, 384), new Enemy("George", 448, 64) };

	public TestState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}

	@Override
	public void init()
	{
		map = new Map("test", "map.txt");
		load(scoreFile);
	}

	@Override
	public void update()
	{
		enemyTimer++;

		collisionCheck();

		if (map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
				.getType() == Tile.TILE_KEY)
		{
			map.setTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE), new AirTile());
			player.setKey();
		}

		if (map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
				.getType() == Tile.TILE_STAIR && player.hasKey())
		{
			score = score + player.getHealth() + (levelTimer / 60);
			saveScore(scoreFile, score);
			load(scoreFile);
			gsm.loadState(new LevelTwo(gsm));
			gsm.changeState(GameStateManager.LEVEL_TWO);
			gsm.unloadState(GameStateManager.TEST_STATE);
		}

		if (map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
				.getType() == Tile.TILE_TREASURE)
		{
			map.setTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE), new AirTile());
			score++;
		}

		if (levelTimer == 0)
			loadDefeat();

		if (player.getHealth() == 0)
			loadDefeat();

		levelTimer--;
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		map.draw(g2d);
		enemies[0].draw(g2d);
		enemies[1].draw(g2d);
		enemies[2].draw(g2d);
		player.draw(g2d);
		g2d.drawString("Health:" + player.getHealth(), 0, 16);
		if(player.hasKey())
			g2d.drawImage(Art.tiles[2][3], 150, 0, null);
		g2d.drawString("Score: " + score, 250, 16);
		g2d.drawString("Time: " + (levelTimer / 60), 350, 16);
		g2d.drawString("Traps: " + player.getTraps(), 460, 16);
	}

	@Override
	public void keyPressed(int k)
	{
		if (k == KeyEvent.VK_W && player.getY() != 0
				&& map.getTileAt((player.getX() / Tile.TILE_SIZE), (player.getY() / Tile.TILE_SIZE))
						.getType() != Tile.TILE_WALL)
			takeTurn(0, -1);
		if (k == KeyEvent.VK_A && player.getX() != 0
				&& map.getTileAt(((player.getX() - 32) / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
						.getType() != Tile.TILE_WALL)
			takeTurn(-1, 0);
		if (k == KeyEvent.VK_S && player.getY() != (GamePanel.HEIGHT - (Tile.TILE_SIZE + Tile.TILE_SIZE))
				&& map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 64) / Tile.TILE_SIZE))
						.getType() != Tile.TILE_WALL)
			takeTurn(0, 1);
		if (k == KeyEvent.VK_D && player.getX() != GamePanel.WIDTH - Tile.TILE_SIZE
				&& map.getTileAt(((player.getX() + 32) / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
						.getType() != Tile.TILE_WALL)
			takeTurn(1, 0);

		if (k == KeyEvent.VK_E && player.placeTrap()
				&& map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE))
						.getType() == Tile.TILE_AIR)
		{
			map.setTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32) / Tile.TILE_SIZE), new SpikeTile());
		}

		if (k == KeyEvent.VK_ESCAPE)
		{
			gsm.changeState(GameStateManager.MENU_STATE);
			gsm.unloadState(GameStateManager.TEST_STATE);
		}

	}

	@Override
	public void keyReleased(int k)
	{
		// TODO Auto-generated method stub

	}

	public void collisionCheck()
	{
		// Collision tick is counts down before the collision is checked again
		// so that the player will not infinitely take damage
		if (collisionTick == 0)
		{
			for (int i = 0; i < enemies.length; i++)
			{
				if (player.getX() == enemies[i].getX() && player.getY() == enemies[i].getY())
				{
					player.hurtPlayer(1);
					collisionTick = 120;
				}
			}
		} else if (collisionTick > 0)
			collisionTick--;
	}
	
	//Makes the game pseudo turn based, like a rogue-like i.e Mystery Dungeon
	public void takeTurn(int x, int y){
		player.move(x, y);
		enemies[0].update();
		enemies[1].update();
		enemies[2].update();
	}
	
	public void loadDefeat()
	{
		saveScore(scoreFile, score);
		load(scoreFile);
		gsm.loadState(new DefeatState(gsm));
		gsm.changeState(GameStateManager.DEFEAT_STATE);
		gsm.unloadState(GameStateManager.TEST_STATE);
	}
}
