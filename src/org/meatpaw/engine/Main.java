package org.meatpaw.engine;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import org.lwjgl.input.Controllers;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.meatpaw.game.Game;

public class Main 
{
	private static Game game;
	
	public static void main(String[] args) 
	{
		initDisplay();
		initGL();
		GameTextures.init();
		initGame();
		
		gameLoop();
		cleanUp();
	}
	
	private static void gameLoop()
	{
		Time.init();
		while(!Display.isCloseRequested())
		{
			Time.update();
			getInput();
			update();
			render();
		}
	}
	
	private static void getInput()
	{
		game.getInput();
	}
	
	private static void update()
	{
		game.update();

	}
	
	private static void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		game.render();
		
		Display.update();
		Display.sync(60);
	}
	
	private static void initGame()
	{
		game = new Game();
	}
	
	private static void cleanUp()
	{
		Display.destroy();
	}
	
	private static void initDisplay()
	{
		try {
			DisplayMode displayMode = new DisplayMode(1920, 1000);
			Display.setDisplayMode(displayMode);
			Display.setLocation(0, 0);
			Display.setFullscreen(false);
			Display.setResizable(true);
			Display.create();
			Display.setVSyncEnabled(true);
			Keyboard.create();
			Controllers.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void initGL()
	{
		glMatrixMode(GL_PROJECTION);//sets matrix mode
		glLoadIdentity();//clears matrix
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glDisable(GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		glClearColor(0, 0, 0, 1);
	}
}
