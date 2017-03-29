/**
 * 	@file
 * 
 * 	State shown when the game is loaded.
 * 
 *	@date
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;

/**
 * @author Jesse
 *
 */
public class SplashState extends GameState
{
	
	public SplashState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.CYAN);
		g2d.fillRect(0, 0, GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);
		
	}

	@Override
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_SPACE)
			gsm.changeState(GameStateManager.MENU_STATE);
	}

	@Override
	public void keyReleased(int k)
	{

		
	}
}
