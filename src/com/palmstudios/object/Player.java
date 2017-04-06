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
	protected Player(String name)
	{
		super(name);
	}

	@Override
	protected void update()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void draw()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void move(double vx, double vy)
	{
		x += vx;
		y += vy;
	}
}
