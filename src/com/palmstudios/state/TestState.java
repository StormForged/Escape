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
import java.io.IOException;

import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;
import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;

/**
 * @author Jesse
 *
 */
public class TestState extends GameState
{
	private Map map;

	public TestState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub
		map = Map.fromFile("map.txt");
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		//g2d.setColor(Color.GREEN);
		//g2d.fillRect(0, 0, GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);
		Tile.drawMap(g2d, map); // This is a change as recommended in PR! :^)
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
