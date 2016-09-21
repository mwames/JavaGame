package org.meatpaw.engine;

public class Frame 
{
	private int length;
	private Sprite sprite;
	private int numberOfTimesDisplayed;
	
	public Frame(Sprite sprite, int length)
	{
		this.sprite = sprite;
		this.length = length;
		this.numberOfTimesDisplayed = 0;
	}
	
	public boolean render()
	{
		sprite.render();
		this.numberOfTimesDisplayed++;
		
		if(this.numberOfTimesDisplayed >= this.length)
		{
			this.numberOfTimesDisplayed = 0;
			return true;
		}
		
		return false;
	}
}
