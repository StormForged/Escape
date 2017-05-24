package com.palmstudiosjunit;

import static org.junit.Assert.*;

import org.junit.*;

import com.palmstudios.state.*;

public class StateTest
{
	static GameStateManager gsm;
	
	@BeforeClass
	public static void setUpClass() throws Exception
	{	
		gsm = new GameStateManager();
    	gsm.loadState(new PlayState(gsm));
	}
	
    @Before
    public void setUpTest() throws Exception 
    {
		gsm.changeState(0);
    }
	
	@Test
	public void testChangeState()
	{
		assertTrue(gsm.getCurrentState() != null);
	}

	@Test
	public void testLoadState()
	{
		gsm.loadState(new SplashState(gsm));
		gsm.changeState(gsm.getNumberStates() - 1);
		assertTrue(gsm.getCurrentState() != null);
	}

	@Test
	public void testGetCurrentState()
	{
		assertTrue(gsm.getCurrentState() != null);
	}

}
