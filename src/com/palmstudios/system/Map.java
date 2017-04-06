package com.palmstudios.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map
{
	public int[][] map;
	public int length;
	
	public Map(int width, int height){
		map = new int[height][width];
	}
	
	public static Map fromFile(String fileName){
		
		Map map = null;
		
		ArrayList<ArrayList<Integer>> tempLayout = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String currentLine;
			
			while((currentLine = br.readLine()) != null){
				//Ignoring whitespace
				if(currentLine.isEmpty())
					continue;
				
				ArrayList<Integer> row = new ArrayList<>();
				
				//Each tile is split with a space
				String[] values = currentLine.trim().split(" ");
				
				
				//If the string isnt empty the value is converted to int and put into our row array
				for(String string : values){
					if(!string.isEmpty()){
						int id = Integer.parseInt(string);
						
						row.add(id);
					}
				}
				
				//All data taken from the loop is added as a new entry into the temp array
				tempLayout.add(row);
			}
		}catch(IOException e){
			
		}
		
		//width of the map is calculated by how many entries there are in the first line of the temp array
		int width = tempLayout.get(0).size();
		//height is by how many lines are in the temp array
		int height = tempLayout.size();
		
		map = new Map(width, height);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				map.map[y][x] = tempLayout.get(y).get(x);
			}
		}
		return map;
	}
}
