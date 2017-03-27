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
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.palmstudios.state.GameStateManager;
import com.palmstudios.state.TestState;

public class GamePanel extends JPanel implements Runnable
{
	
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	public static final int SCALE = 2;

	private boolean 		running = false;	/**< True if we should be running, false if not. */
	private Thread 			gThread = null; 	/**< Game Thread. */
	
	private Graphics2D 		g2d;				/**< Graphics of the framebuffer */
	private BufferedImage	framebuffer;		/**< The Framebuffer we can write to */
	
	private GameStateManager gsm = new GameStateManager();

	/**
	 * Class constructor
	 */
	public GamePanel()
	{
		super(); // Call superconstructor for our JPanel
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // Preferred size of this JPanel
		setFocusable(true); // Lets us request focus of this JPanel
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
	public void run()
	{
		init();
		
		while(running)
		{
			update();
			render();
			draw();
		}
	}
	
	/**
	 * Initialisation routine. 
	 * Any data that needs to be initialised should be initialised here.
	 */
	private void init()
	{
		framebuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create our 24-bit framebuffer
		g2d = (Graphics2D)framebuffer.getGraphics();
		
		gsm.loadState(new TestState());
		gsm.changeState(0);
		
//		for(int x = 0; x < WIDTH; x++)
//		{
//			for(int y = 0; y < HEIGHT; y++)
//			{
//				framebuffer.setRGB(x, y, (int) (Math.random() * 0xFFFFFF));
//			}
//		}
		
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
}