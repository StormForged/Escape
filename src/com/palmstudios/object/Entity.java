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
	private String 	name; 	/**< The name of this entity */
	private int 	tilex; 	/**< What tile (along the x axis) does this entity lie on? */ 
	private int 	tiley; 	/**< What tile (along the y axis) does this entity lie on? */ 
	
	/**
	 * Basic constructor.
	 * 
	 * @param name - The name of this entity.
	 */
	protected Entity(String name)
	{
		this.name 	= name;
		tilex 		= 0;
		tiley 		= 0;
	}
	
	/**
	 * Full constructor. Specifies entity location.
	 * 
	 * @param name - Name of this entity
	 * @param tilex - Initial tile (x) of this entity
	 * @param tiley - Initial tile (y) of this entity
	 */
	protected Entity(String name, int tilex, int tiley)
	{
		this.name	= name;
		this.tilex 	= tilex;
		this.tiley 	= tiley;
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
}
