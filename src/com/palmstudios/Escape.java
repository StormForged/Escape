/**
 * 	@file
 * 
 *	Escape! A game by a bunch of dudes in ISYS1118
 *
 *	@date 9-3-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.palmstudios.system.GamePanel;

/**
 * 	Main Class
 */
public class Escape {
	
	/**
	 * 	Program entry point.
	 * 
	 * 	@param args - Arguments passed to us by the command line.
	 */
	public static void main(String[] args)
	{
		JFrame 		hwnd = new JFrame("Escape!");
		GamePanel	gpan = new GamePanel();
		
		hwnd.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
		hwnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hwnd.add(gpan);
		hwnd.setResizable(false);
		hwnd.setVisible(true);
		hwnd.pack();
		hwnd.setPreferredSize(new Dimension(GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE));
	}
}