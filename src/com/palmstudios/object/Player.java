/**
 * 	@file
 * 
 * 	Player object.
 * 
 *	@date 4-6-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.object;

/**
 * @author Jesse
 *
 */
public class Player extends Entity
{
	public Player(String name)
	{
		super(name);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double vx, double vy)
	{
		x += vx;
		y += vy;
	}
}
