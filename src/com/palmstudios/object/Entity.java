/**
 * 	@file
 * 
 *	Base entity class. All game objects should inherit from this!!
 *
 *	@date 12-3-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.object;

/**
 * @author Jesse
 *
 */
public abstract class Entity
{
	private String 	name; 		/**< The name of this entity */
	private int 	x; 			/**< What x value is this entity currently at? */ 
	private int 	y; 			/**< What y value is this entity currently at? */ 
	
	/**
	 * Basic constructor.
	 * 
	 * @param name - The name of this entity.
	 */
	protected Entity(String name)
	{
		this.name	= name;
		this.x 		= 0;
		this.y 		= 0;
	}
	
	/**
	 * Full constructor. Specifies entity location.
	 * 
	 * @param name - Name of this entity
	 * @param x - Initial tile (x) of this entity
	 * @param y - Initial tile (y) of this entity
	 */
	protected Entity(String name, int x, int y)
	{
		this.name	= name;
		this.x 	= x;
		this.y 	= y;
	}
	
	/**
	 * 	All entity logic should be contained in this function in the specific entity's class.
	 * 	This includes all movement logic.
	 */
	protected abstract void update();
	
	/**
	 * Draw this entity to the screen.
	 */
	protected abstract void draw();
	
	/**
	 * Entity movement method.
	 * @param vx - How many pixels we want to move per tick in the x axis.
	 * @param vy - How many pixels we want to move per tick in the y axis.
	 */
	protected abstract void move(double vx, double vy);
}
