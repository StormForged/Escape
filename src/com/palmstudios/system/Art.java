/**
 * 	@file
 * 
 * 	Art class. All resources are statically loaded so they can be accessed anywhere.
 * 
 *	@date 6-4-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.system;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art
{
	
	public static Image[][] tiles;
	public static Image[][] enemyFrames;
	public static Image[][] enemyHurtFrames;
	public static Image[][] enemyReallyHurtFrames;
	
	public static void init()
	{
		try
		{
			tiles = cut("data/tile/tileset.png", 32, 32);
			enemyFrames = cut("data/sprite/enemy.png", 32, 64);
			enemyHurtFrames = cut("data/sprite/enemyhurt.png", 32, 64);
			enemyReallyHurtFrames = cut("data/sprite/enemyreallyhurt.png", 32, 64);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param path - Path to the sheet.
	 * @return Loaded image
	 * @throws IOException
	 */
	private static Image loadImage(String path) throws IOException
	{	
		return ImageIO.read(new File(path)); // Load the image
	}
	
	/**
	 * 
	 * @param path 	- Path to the file
	 * @param width - Width of one of the sprites in the sheet (horizontal resolution) 
	 * @param height - Height of one of the sprites in the sheet (vertical resolution)
	 * @return 2D Array of cut images.
	 * @throws IOException
	 */
	private static Image[][] cut(String path, int width, int height) throws IOException
	{
		Image source = loadImage(path); // Load the sheet.
		Image[][] images = new Image[source.getWidth(null) / width][source.getHeight(null) / height]; // Sheet array
		
		BufferedImage img = (BufferedImage)source;
		
		for(int x = 0; x < source.getWidth(null) / width; x++)
		{
			for(int y = 0; y < source.getHeight(null) / height; y++)
			{
				Image tile = img.getSubimage(x * width, y * width, width, height);
				images[x][y] = tile;
			}
		}

		return images;
	}
}
