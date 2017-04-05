package com.palmstudios.system;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Tile
{
	public static BufferedImage tileSheet;
	
	//Define a width and height for the tiles, this will need to change if tile size changes at all
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	//Loads the tileset
	public static BufferedImage loadTileSheet(String fileName){
		BufferedImage img = null;
		
		try{
			img = ImageIO.read(new File(fileName));
		}catch(IOException e){
			System.out.print("Could not load image");
		}
		
		return img;
	}
	
	//Draws the passed in map
	public static void drawMap(Graphics g, Map map)
	{	
		//Loads in the tilesheet as a buffered image
		tileSheet = loadTileSheet("tileset.png");
		
		for(int y = 0; y < map.map.length; y++){
			for(int x = 0; x < map.map[y].length; x++){
				//Index is the value assigned to the tile
				//This is worked out by dividing the number within the tile by the (tile width - 1)
				//Minus 1 because we count from 0
				int index = map.map[y][x];
				//The y Offset is here so we can wrap around incase the index is larger than the width of the tile sheet
				int yOffset = 0;

				//If the index is greater than the width of the tile sheet
				if(index > (tileSheet.getWidth() / TILE_WIDTH) - 1){
					//And how many times over the index is greater
					while((index > (tileSheet.getWidth() / TILE_WIDTH)-1)){
						//Y is offset further with each iteration
						yOffset++;
						index = index - (tileSheet.getWidth() / TILE_WIDTH);
					}							
				}
				
				//The first four parameters are were we would like to draw the tiles 
				//x and y being the point in the map array we are currently at, multiplied by the tiles parameters
				//The second 4 parameters are where in the tile sheet we source the tiles from
				//The source locations x values are calculated by taking the index value, and multiplying it by the tile parameters
				//This will jump us horizonally along the sheet
				//The source locations y values are calculated by taking the y offset and multiplying it by the tile parameters
				//wrapping horizontally when needed
				g.drawImage(tileSheet, x * TILE_WIDTH, y * TILE_HEIGHT, (x * TILE_WIDTH) + TILE_WIDTH, (y * TILE_HEIGHT) + TILE_HEIGHT,
							index * TILE_WIDTH, yOffset * TILE_HEIGHT, (index * TILE_WIDTH) + TILE_WIDTH, (yOffset * TILE_HEIGHT) + TILE_HEIGHT, null);
			}
		}
	}
}
