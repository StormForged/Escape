/**
 * 	@file
 * 
 * 	Menu state shown after the splash state. Where the user can load a new game, adjust settings etc.
 * 
 *	@date
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.state;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.palmstudios.system.GameState;

/**
 * @author Jesse
 *
 */
public class MenuState extends GameState
{
	
	private BufferedImage 	logo;
	private int tick = 0;
	private int selected = 0;
	
	private String[] options = {"New Game", "Quit"};

	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init()
	{
		try
		{
			logo = ImageIO.read(new File("menu.png"));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.setFont(new Font("Times New Roman", Font.BOLD, 24));
		g2d.drawImage(logo, 0, 0, logo.getWidth(), logo.getHeight(), null);
		
		for(int i = 0; i < options.length; i++)
		{
			if(i == selected)
			{
				g2d.drawString("-> " + options[i], 320, 190 + (i * 20));
				continue;
			}
			g2d.drawString(options[i], 320, 190 + (i * 20));
		}
	}

	@Override
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_ENTER)
		{
			if(selected == 0)
				gsm.changeState(GameStateManager.TEST_STATE);
			if(selected == 1)
				System.exit(0);
		}
		
		if(k == KeyEvent.VK_UP && selected > 0)
		{
			selected--;
		}
		
		if(k == KeyEvent.VK_DOWN && selected < 1)
		{
			selected++;
		}
	}

	@Override
	public void keyReleased(int k)
	{
	}
}
