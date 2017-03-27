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

import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;

/**
 * @author Jesse
 *
 */
public class TestState extends GameState
{

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k)
	{
		// TODO Auto-generated method stub
		
	}

}
