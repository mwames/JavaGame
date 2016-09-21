package org.meatpaw.engine;

public class Time 
{
	private static long currentTime;
	private static long lastTime;
	
	public static long getTime()
	{
		return System.nanoTime() / 1000000;
	}
	
	public static long getDelta()
	{
		return (currentTime - lastTime);
	}
	
	public static void update()
	{
		lastTime = currentTime;
		currentTime = getTime();
	}
	
	public static void init()
	{
		lastTime = getTime();
		currentTime = getTime();
	}
}
