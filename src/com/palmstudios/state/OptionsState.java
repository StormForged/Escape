/**
 * 	@file
 * 
 *	@date
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;

/**
 * @author Matt
 *
 */
public class OptionsState extends GameState
{
	private HashMap<String, Integer> options;
	private int selected;
	
	public OptionsState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}

	@Override
	public void init()
	{
		FileInputStream fis;
		selected = 0;
		options = new HashMap<String, Integer>();
		
		try
		{
			Path optf = Paths.get("options.txt");
			BufferedReader reader = Files.newBufferedReader(optf);
			String line;
			
			while((line = reader.readLine()) != null)
			{
				options.put(line.split("=")[0], Integer.parseInt(line.split("=")[1]));
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
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g2d.setFont(new Font("constantine", Font.PLAIN, 32));
		g2d.setColor(Color.WHITE);
		g2d.drawString("OPTIONS", 10, 30);
		
		g2d.setFont(new Font("constantine", Font.PLAIN, 22));
		g2d.setColor(Color.WHITE);
		
		for(int i = 0; i < options.size(); i++)
		{
			g2d.drawString("Game Time: " + options.get("time"), 14, 120);
			g2d.drawString("Health: " + options.get("health"), 14, 120 + g2d.getFontMetrics().getHeight());
		}
	}

	@Override
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_ESCAPE)
		{
			try
			{
				FileWriter fw = new FileWriter("options.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				
				bw.write("time" + "=" + options.get("time") + "\r\n");
				bw.write("health" + "=" + options.get("health") + "\r\n");
				bw.close();
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gsm.unloadState(gsm.getNumberStates() - 1);
			gsm.changeState(GameStateManager.MENU_STATE);
		}
		
		if(k == KeyEvent.VK_UP)
		{
			if(selected > 0)
			{
				selected--;
			}
		}
		
		if(k == KeyEvent.VK_DOWN)
		{
			if(selected < 1)
			{
				selected++;
			}
		}
		
		if(k == KeyEvent.VK_LEFT)
		{
			if(selected == 0)
			{
				int t = options.get("time");
				if(t > 0)
					t -= 100;
				options.put("time", t);
			}
			
			if(selected == 1)
			{
				int h = options.get("health");
				if(h > 1)
					h -= 1;
				options.put("health", h);
			}
		}
		
		if(k == KeyEvent.VK_RIGHT)
		{
			if(selected == 0)
			{
				int t = options.get("time");
				t += 100;
				options.put("time", t);
			}
			
			if(selected == 1)
			{
				int h = options.get("health");
				if(h < 10)
					h += 1;
				options.put("health", h);
			}
		}
	}

	@Override
	public void keyReleased(int k)
	{
		// TODO Auto-generated method stub

	}
}
