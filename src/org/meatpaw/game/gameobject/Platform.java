package org.meatpaw.game.gameobject;

import org.lwjgl.opengl.Display;
import org.meatpaw.engine.GameObject;

public class Platform extends GameObject 
{

	private String name;

	public Platform(float x, float y, float r, float g, float b, float SX, float SY, int type, String name)
	{
		init(x, y, r, g, b, SX, SY, type, name);
		this.top = y + SY;
		this.bottom = y;
		this.name = name;
		
	}
	public void update()
	{
		
	}
	
	public String getName()
	{
		return this.name;
	}
}
