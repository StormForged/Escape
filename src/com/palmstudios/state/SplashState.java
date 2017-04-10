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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;

/**
 * @author Jesse
 *
 */
public class SplashState extends GameState
{
	private static final int DELAY_TIME = 5000;
	
	private BufferedImage 	logo;
	private int				count;
	
	public SplashState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}

	@Override
	public void init()
	{
		count = DELAY_TIME;
		
		try
		{
			logo = ImageIO.read(new File("plogo.png"));
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update()
	{
		count--;
		
		if(count <= 0)
		{
			gsm.loadState(new TestState(gsm));
			gsm.changeState(GameStateManager.TEST_STATE);
		}
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(logo, 0, 0, logo.getWidth(), logo.getHeight(), null);
	}

	@Override
	public void keyPressed(int k)
	{
		// Check if any key on the keyboard has been pressed.
		if(k > 0)
		{
			gsm.loadState(new TestState(gsm));
			gsm.changeState(GameStateManager.TEST_STATE);
		}
	}

	@Override
	public void keyReleased(int k)
	{

		
	}
}
