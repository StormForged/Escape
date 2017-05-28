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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.palmstudios.system.GameState;

/**
 * @author Jesse
 *
 */
public class MenuState extends GameState
{
	
	private BufferedImage 	logo;
	private int 			tick = 180;
	private int 			selected = 0;
	private boolean 		admin = false;
	private boolean 		login = false;
	private String			playerName;
	
	private HashMap<String, String> users;
	
	private String[] admin_opts 	= {"New Game", "Options", "Users", "Credits", "Quit"};
	private String[] play_opts 		= {"New Game", "Credits", "Quit"};
	private String[] options	 	= {"Register", "Login", "Credits", "Quit"};

	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init()
	{
		users = new HashMap<String, String>();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Load main menu logo
		try
		{
			logo = ImageIO.read(new File("data/state/menu.png"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void update()
	{
		
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		g2d.setFont(new Font("constantine", Font.PLAIN, 24));
		g2d.drawImage(logo, 0, 0, logo.getWidth(), logo.getHeight(), null);
		
		if(admin == false && login == false)
		{
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
		else if(admin == true && login == false)
		{
			for(int i = 0; i < admin_opts.length; i++)
			{
				if(i == selected)
				{
					g2d.drawString("-> " + admin_opts[i], 320, 190 + (i * 20));
					continue;
				}
				g2d.drawString(admin_opts[i], 320, 190 + (i * 20));
			}	
		}
		else if(admin == false && login == true)
		{
			for(int i = 0; i < play_opts.length; i++)
			{
				if(i == selected)
				{
					g2d.drawString("-> " + play_opts[i], 320, 190 + (i * 20));
					continue;
				}
				g2d.drawString(play_opts[i], 320, 190 + (i * 20));
			}	
		}
	}

	@Override
	public void keyPressed(int k)
	{
		if(admin == false && login == false)
		{
			if(k == KeyEvent.VK_ENTER)
			{
				if(selected == 0) // User registration
				{
					String uname  = JOptionPane.showInputDialog("Please enter your name");
					String pw = JOptionPane.showInputDialog("Please enter your password");
					
					if(uname == null || pw == null)
						return;
					
					users.put(uname, pw);
					
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
				}
				if(selected == 1) // User Login
				{
					String uname = JOptionPane.showInputDialog("Please enter your name");
					String pw = JOptionPane.showInputDialog("Please enter your name");
					
					if(!users.get(uname).equals(pw))
					{
						JOptionPane.showMessageDialog(null, "Incorrect username or password!");
						return;
					}
					
					if(users.get(uname).equals(pw))
					{
						if(uname.equals("admin"))
							admin = true;
						else 
							login = true;
					}
				}
				
				if(selected == 2)
				{
					gsm.loadState(new CreditsState(gsm));
					gsm.changeState(gsm.getNumberStates() - 1);
				}
				
				if(selected == 3)
				{
					System.exit(0);
				}
			}
			
			if(k == KeyEvent.VK_UP && selected > 0)
			{
				selected--;
			}
			
			if(k == KeyEvent.VK_DOWN && selected < options.length - 1)
			{
				selected++;
			}
		}
		else if(admin == true && login == false)
		{
			if(k == KeyEvent.VK_ENTER)
			{
				if(selected == 0)
				{
					gsm.loadState(new PlayState(gsm));
					gsm.changeState(GameStateManager.PLAY_STATE);
				}
				
				if(selected == 1)
				{
					gsm.loadState(new OptionsState(gsm));
					gsm.changeState(gsm.getNumberStates() - 1);
				}
				
				if(selected == 2)
				{
					gsm.loadState(new UserListState(gsm));
					gsm.changeState(gsm.getNumberStates() - 1);
				}
				
				if(selected == 3)
				{
					gsm.loadState(new CreditsState(gsm));
					gsm.changeState(gsm.getNumberStates() - 1);
				}
				
				if(selected == 4)
				{
					System.exit(0);
				}
			}
			
			if(k == KeyEvent.VK_UP && selected > 0)
			{
				selected--;
			}
			
			if(k == KeyEvent.VK_DOWN && selected < admin_opts.length - 1)
			{
				selected++;
			}
		}
		else if(admin == false && login == true)
		{
			if(k == KeyEvent.VK_ENTER)
			{
				if(selected == 0)
				{
					gsm.loadState(new PlayState(gsm));
					gsm.changeState(GameStateManager.PLAY_STATE);
				}
				
				if(selected == 1)
				{
					gsm.loadState(new CreditsState(gsm));
					gsm.changeState(gsm.getNumberStates() - 1);
				}
				
				if(selected == 2)
				{
					System.exit(0);
				}
			}
			
			if(k == KeyEvent.VK_UP && selected > 0)
			{
				selected--;
			}
			
			if(k == KeyEvent.VK_DOWN && selected < play_opts.length - 1)
			{
				selected++;
			}
		}
	
	}

	@Override
	public void keyReleased(int k)
	{
	}
}
