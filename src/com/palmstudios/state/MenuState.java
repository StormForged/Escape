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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.palmstudios.system.GameState;
import com.palmstudios.system.UserDatFile;

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
	
	private UserDatFile 	users;
	
	private String[] admin_opts 	= {"New Game", "Options", "Users", "Map Editor", "Credits", "Quit"};
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
		users = new UserDatFile();
		
		users.read();
		
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
		g2d.setColor(Color.WHITE);
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
					
					users.add(uname, pw);
					users.write();
				}
				if(selected == 1) // User Login
				{
					String uname = JOptionPane.showInputDialog("Please enter your name");
					
					if(!users.userExists(uname))
					{
						JOptionPane.showMessageDialog(null, "Username " + uname + " does not exist!\nPlease register first!" );
						return;
					}
					
					String pw = JOptionPane.showInputDialog("Please enter password for: " + uname);
					

					if(!users.getPassword(uname).equals(pw))
					{
						JOptionPane.showMessageDialog(null, "Incorrect username or password!");
						return;
					}
					
					if(users.getPassword(uname).equals(pw))
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
					gsm.loadState(new UserListState(gsm, users));
					gsm.changeState(gsm.getNumberStates() - 1);
				}
				
				if(selected == 3)
				{
					gsm.loadState(new MapEditorState(gsm));
					gsm.changeState(gsm.getNumberStates() - 1);
				}
				
				if(selected == 4)
				{
					gsm.loadState(new CreditsState(gsm));
					gsm.changeState(gsm.getNumberStates() - 1);
				}
				
				if(selected == 5)
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
