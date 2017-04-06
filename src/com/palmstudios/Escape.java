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
		JFrame hwnd = new JFrame("Escape!");
		hwnd.setSize(800, 600);
		hwnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hwnd.add(new GamePanel());
		hwnd.setVisible(true);
	}

}