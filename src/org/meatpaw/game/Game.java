package org.meatpaw.game;
import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.meatpaw.engine.GameObject;
import org.meatpaw.engine.MoveableObject;
import org.meatpaw.game.gameobject.Player;
import org.meatpaw.game.gameobject.Platform;

public class Game 
{
	static Controller controller = Controllers.getController(0);
	public static ArrayList<GameObject> objects;
	public static ArrayList<GameObject> toRemove;
	public static Player player;
	public static Platform bottomBorder;
	public static Platform leftBorder;
	public static Platform rightBorder;
	public static Platform topBorder;
	public static Platform platform;
	public static Platform obstacle1;
	public static Platform obstacle2;
	public static Platform pixel;
	public static Platform optional;
	public static Platform playerRect;
	public static Platform objectRect;
	
	public Game()
	{
		objects = new ArrayList<GameObject>();
		toRemove = new ArrayList<GameObject>();
		player = new Player(Display.getWidth()/2 - Player.WIDTH/2, Display.getHeight()/2 - Player.HEIGHT/2);
		bottomBorder = new Platform(0, 0, 0f, 0f, 0f, Display.getWidth(), 0, 1, "Bottom Border" );
		leftBorder = new Platform(0, 0, 0f, 0f, 0f, 0, Display.getHeight(), 1, "Left Border" );
		rightBorder = new Platform(Display.getWidth(), 0, 0f, 0f, 0f, 0, Display.getHeight(), 1, "Right Border" );
		topBorder = new Platform(0, Display.getHeight(), 0f, 0f, 0f, Display.getWidth(), 0, 1, "Top Border" );
		platform = new Platform(0, 0, 1f, 0f, 0f, 1920, 24, 1, "bottom");
		obstacle1 = new Platform(1280, 114, 0f, 0f, 1f, 96f, 100f, 1, "THEBOX");
		obstacle2 = new Platform(40, 24, 0f, 0f, 1f, 96, 1000, 1, "left");
		pixel = new Platform(1600, 0, 0.5f, 0.5f, 0.5f, 32, 88, 1, "rightmost");
		optional = new Platform(1550, 88, 1, 1, 1, 150, 50, 1, "again dunno");
		playerRect = new Platform(0, 0, 1, 0, 0, Player.WIDTH, Player.HEIGHT, 2, "player");
		objectRect = new Platform(0, 0, 0, 0, 1, 1, 1, 2, "dunno");
		objects.add(player);
		objects.add(platform);
		objects.add(obstacle1);
		objects.add(pixel);
		objects.add(optional);
		objects.add(obstacle2);
		//objects.add(playerRect);
		//objects.add(objectRect);
		MoveableObject.init(objects);
	}
	public void getInput()
	{
		player.getInput();
	}
	
	public void update()
	{			
		for(GameObject go : objects)
		{
			if(!go.getRemove())
			{
				go.update();
			}
			else
				toRemove.add(go);
		}
		
		for(GameObject go : toRemove)
		{
			objects.remove(go);
		
		}
		
	}
	
	public void render()
	{
		for(GameObject go : objects)
			go.render();
	}
	
	public static Controller getController()
	{
		return controller;
	}
	
	public static ArrayList<GameObject> getGameObjectsArrayList()
	{
		return objects;
	}
	
	
}
