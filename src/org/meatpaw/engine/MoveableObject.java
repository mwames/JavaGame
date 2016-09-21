package org.meatpaw.engine;

import java.util.ArrayList;

public abstract class MoveableObject extends GameObject
{
	protected float gravity = 0.05f;
	protected float maxSpeedX;
	protected float maxSpeedY = 15;
	protected float newPosX;
	protected float newPosY;
	public static float speedX;
	protected float speedY;
	protected float accelerationX;
	protected float velocityX;
	public static float velocityY;
	protected float accelerationY;
	protected float rightSide;
	protected float leftSide;
	protected float newLeftSide;
	protected float newRightSide;
	protected float newTop;
	protected float newBottom;
	protected boolean willCollideX;
	protected boolean willCollideY = false;
	protected boolean moveX = false;
	protected boolean moveY = true;
	
	protected static ArrayList<GameObject> obstacles;	
	
	public static void init(ArrayList<GameObject> solidObjects)
	{
		obstacles = solidObjects;
		
	}
	public float getNewPosX() {
		return newPosX;
	}
	public float getNewPosY() {
		return newPosY;
	}
	public float getNewLeftSide() {
		return newLeftSide;
	}
	public float getNewRightSide() {
		return newRightSide;
	}
	
	
}
