package com.palmstudios.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.palmstudios.system.Audio;
import com.palmstudios.system.GameState;

public class DefeatState extends GameState
{
	private int score;
	private String scoreFile = "score.txt";
	private BufferedImage logo;

	public void load(String path)
	{
		try
		{
			Scanner scanner = new Scanner(new File(path));

			score = scanner.nextInt();

			scanner.close();
		} catch (IOException e)
		{

		}
		;
	}

	public DefeatState(GameStateManager gsm, int score)
	{
		this.gsm = gsm;
		this.score = score;
		init();
	}

	@Override
	public void init()
	{
		
		Audio.playSound("data/snd/go.wav", 0);
		try
		{
			logo = ImageIO.read(new File("data/state/defeat.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2d)
	{
		// TODO Auto-generated method stub
		g2d.drawImage(logo, 0, 0, logo.getWidth(), logo.getHeight(), null);
		g2d.drawString("Score: " + score, 200, 300);
	}

	@Override
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_ESCAPE)
		{
			gsm.unloadState(gsm.getNumberStates() - 1);
			gsm.changeState(GameStateManager.MENU_STATE);
		}
	}

	@Override
	public void keyReleased(int k)
	{
		// TODO Auto-generated method stub

	}

}
