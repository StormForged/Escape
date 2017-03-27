/**
 * 	@file
 * 
 *	Game State class. All states of the game inherit off of this class.
 *
 *	@date 24-3-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.system;

import java.awt.Graphics2D;

/**
 * ALL GAME STATES INHERIT FROM THIS CLASS!!! IT CAN AND SHOULD NEVER BE INSTANTIATED!!!
 * @author Jesse
 *
 */
public abstract class GameState
{
	
	/**
	 * State initialisation method. All local state data should be initialised in this function.
	 */
	public abstract void init();
	
	/**
	 * State update method. All game logic is controlled here. 
	 */
	public abstract void update();
	
	/**
	 * Draw objects to the framebuffer (BufferedImage in @ref GamePanel.java)
	 * @param g2d - Graphics2D passed from @ref GamePanel.java
	 */
	public abstract void draw(Graphics2D g2d);
	
	/**
	 * Function called when a user presses a key on the keyboard.
	 * @param k - Key that was pressed by the user.
	 */
	public abstract void keyPressed(int k);
	
	/**
	 * Function called when user releases a key on the keyboard.
	 * @param k - Key that was resleased.
	 */
	public abstract void keyReleased(int k);
}
