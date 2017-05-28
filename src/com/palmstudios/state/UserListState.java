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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;

/**
 * @author Jesse
 *
 */
public class UserListState extends GameState
{
	private HashMap<String, String> users = null;
	private int selected;
	
	public UserListState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	public void init()
	{
		selected = 0;
		
		// Read in the user text file and map each user
		try
		{		
			FileInputStream 	f = new FileInputStream("users.dat");
			ObjectInputStream 	ois = new ObjectInputStream(f);
			users = (HashMap<String, String>)ois.readObject();
			ois.close();
			f.close();
		}
		catch(IOException ex)
		{
			
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}

	public void update()
	{
		
	}

	public void draw(Graphics2D g2d)
	{
		int i = 0;
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g2d.setFont(new Font("constantine", Font.PLAIN, 32));
		g2d.setColor(Color.WHITE);
		g2d.drawString("USERS", 10, 30);
		
		g2d.setFont(new Font("constantine", Font.PLAIN, 22));
		g2d.setColor(Color.WHITE);
		
		for(String s: users.keySet())
		{
			if(i == selected)
			{
				g2d.drawString("-> " + s, 14, 80 + (g2d.getFontMetrics().getHeight() * i));
				i++;
				continue;
			}
			
			g2d.drawString(s, 14, 80 + (g2d.getFontMetrics().getHeight() * i));
			i++;
		}
	}

	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_UP)
		{
			if(selected > 0)
			{
				selected--;
			}
		}
		
		if(k == KeyEvent.VK_DOWN)
		{
			if(selected < users.size())
			{
				selected++;
			}
		}
		
		if(k == KeyEvent.VK_DELETE)
		{
			int i = 0;
			
			// HACK HACK HACK HACK!
			for(String s: users.keySet())
			{
				if(i == selected)
				{
					users.remove(s);
					
					try
					{
						FileOutputStream 	f = new FileOutputStream("users.dat");
						ObjectOutputStream 	oos = new ObjectOutputStream(f);
						oos.writeObject(users);
						oos.close();
						f.close();
					}
					catch(IOException ex)
					{
						
					}
					
					return;
				}
				
				i++;
			}
		}
		
		if(k == KeyEvent.VK_ESCAPE)
		{
			gsm.unloadState(gsm.getNumberStates() - 1);
			gsm.changeState(GameStateManager.MENU_STATE);
		}
	}

	public void keyReleased(int k)
	{
		// TODO Auto-generated method stub
		
	}
	
}
