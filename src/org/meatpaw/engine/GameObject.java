package org.meatpaw.engine;

import static  org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

public abstract class GameObject 
{
	protected float posX;
	protected float posY;
	protected float bottom;
	protected float top;
	protected float leftSide;
	protected float rightSide;
	protected int type;
	public String name;
	public Sprite sprite;
	public boolean remove = false;
	
	public void update()
	{
		
	}
	
	public void render()
	{
		glPushMatrix();
		{
		glTranslatef(posX,posY,0);
		sprite.render();
		}
		glPopMatrix();
	}

	public boolean getRemove()
	{
		return remove;
	}
	public float getX() {
		return posX;
	}

	public void setX(float x) {
		this.posX = x;
	}

	public float getY() {
		return posY;
	}

	public void setY(float y) {
		this.posY = y;
	}	
	
	public float getSX()
	{
		return sprite.getSx();
	}
	
	public float getSY()
	{
		return sprite.getSy();
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public float getBottom() {
		return bottom;
	}

	public float getTop() {
		return top;
	}

	
	public String getName() {
		return name;
	}
	
	public void setR(float r)
	{
		sprite.setR(r);
	}
	public void setG(float g)
	{
		sprite.setG(g);
	}
	public void setB(float b)
	{
		sprite.setR(b);
	}
	protected void init(float x, float y, Texture t, float sx, float sy, int type, String name)
	{
		this.name = name;
		this.leftSide = x;
		this.rightSide = x + sx;
		this.bottom = y;
		this.top = y + sy;
		this.posX = x;
		this.posY = y;
		this.type = type;
		this.sprite = new Sprite(t, sx, sy);
	}
	protected void init(float x, float y, float r, float g, float b, float sx, float sy, int type, String name)
	{
		this.name = name;
		this.leftSide = x;
		this.rightSide = x + sx;
		this.bottom = y;
		this.top = y + sy;
		this.posX = x;
		this.posY = y;
		this.type = type;
		this.sprite = new Sprite(r, g, b, sx, sy);
	}
}
