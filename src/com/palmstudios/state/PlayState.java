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
import java.util.ArrayList;

import com.palmstudios.object.Enemy;
import com.palmstudios.object.Player;
import com.palmstudios.system.Audio;
import com.palmstudios.system.GameState;
import com.palmstudios.system.Map;

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
		
		Audio.playSound("data/snd/start.wav");
		time = GAME_TIME;
	}

	public void update()
	{
		if(time <= 0)
		{
			gsm.loadState(new VictoryState(gsm));
			gsm.changeState(gsm.getNumberStates() - 1);
		}
		
		player.update();
		
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).update();
		}
		
		time--;
	}

	public void draw(Graphics2D g2d)
	{
		map.draw(g2d);
		
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
