package com.palmstudiosjunit;

import static org.junit.Assert.*;
import org.junit.*;

import com.palmstudios.object.Player;

public class PlayerTest
{
	Player player;

	@Before
	public void setUp() throws Exception
	{
		player = new Player("Curtis", null, 0, 0, 5, false, 0);
		player.move(2, 2);
	}

	@Test
	public void testMove()
	{
		int x = player.getX();
		int y = player.getY();
		
		assertTrue(x == 2);
		assertTrue(y == 2);
	}

	@Test
	public void testPlayer()
	{
		assertFalse(player == null);
	}

	@Test
	public void testGetEntityName()
	{
		String name = player.getEntityName();
		
		assertTrue(name == "Curtis");
	}

	@Test
	public void testGetX()
	{
		int x = player.getX();
		
		assertTrue(x == 2);
	}

	@Test
	public void testGetY()
	{
		int y = player.getX();
		
		assertTrue(y == 2);
	}
}
