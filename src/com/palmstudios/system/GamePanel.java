/**
 * 	@file
 * 
 *	Main Game Panel. Game loop is defined here (though it should probably be moved!)
 *
 *	@date 9-3-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.system;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.palmstudios.state.GameStateManager;
import com.palmstudios.state.MenuState;
import com.palmstudios.state.SplashState;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	
	public static final int WIDTH 	= 640;
	public static final int HEIGHT 	= 480;
	public static final int SCALE 	= 1;

	private boolean 		running = false;	/**< True if we should be running, false if not. */
	private Thread 			gThread = null; 	/**< Game Thread. */
	
	private Graphics2D 		g2d;				/**< Graphics of the framebuffer */
	private BufferedImage	framebuffer;		/**< The Framebuffer we can write to */
	
	private GameStateManager gsm;

	/**
	 * Class constructor
	 */
	public GamePanel()
	{
		super(); // Call superconstructor for our JPanel
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // Preferred size of this JPanel
		setFocusable(true); // Lets us request focus of this JPanel
		addKeyListener(this); // Add a key listener (implemented in this class)
		requestFocus(); // Manually request focus
	}

	/**
	 * Run when this class is notified it has been added (??)
	 */
	public void addNotify()
	{
		super.addNotify();
		
		if(gThread == null) // Make sure gThread hasn't been initialised already.
		{
			gThread = new Thread(this, "GamePanel Thread");
			gThread.start();
		}
	}

	/**
	 * Thread run function.
	 */
	public synchronized void run()
	{
		long currTime;
		long dt;
		
		init();
		while(running)
		{
			currTime = System.nanoTime();
			
			update();
			render();
			draw();
			
			dt = System.nanoTime() - currTime;
			
			try
			{
				Thread.sleep((1000 / 60) / dt / 1000000); // Try to maintain 60FPS
			} 
			catch (InterruptedException e)
			{
				System.err.println("Fatal: Game Thread Exception!");
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Initialisation routine. 
	 * Any data that needs to be initialised should be initialised here.
	 */
	private void init()
	{
		gsm 		= new GameStateManager();
		framebuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create our 24-bit framebuffer
		g2d 		= (Graphics2D)framebuffer.getGraphics();
		
		gsm.loadState(new SplashState(gsm));
		gsm.loadState(new MenuState(gsm));
		gsm.changeState(GameStateManager.SPLASH_STATE);
		
		Art.init(); // Load all tilesheets
		
		running = true;
	}
	
	/**
	 * Game logic update. Run once per frame. 
	 * All logic is performed in it's own state. 
	 */
	public void update()
	{
		gsm.getCurrentState().update();
	}
	
	/**
	 * Render the state to our buffered image.
	 */
	public void render()
	{
		gsm.getCurrentState().draw(g2d);
	}
	
	/**
	 * Draw our buffered image to this component. 
	 */
	public void draw()
	{
		Graphics g = this.getGraphics();
		g.drawImage(framebuffer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
	}

	/**
	 * Function called when a key release has been detected by the instance of this component (GamePanel)
	 * @param k - Key the user has pressed.
	 */
	public void keyPressed(KeyEvent k)
	{
		gsm.getCurrentState().keyPressed(k.getKeyCode());
	}

	/**
	 * Function called when a key release has been detected by the instance of this component (GamePanel)
	 * @param k - Key the user has released.
	 */
	public void keyReleased(KeyEvent k)
	{
		gsm.getCurrentState().keyReleased(k.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent k)
	{
		// TODO Auto-generated method stub
		
	}
}