package org.meatpaw.engine;
import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;


public class Sprite 
{

	private float r;
	private float g;
	private float b;
	private Texture t;
	private float sx;
	private float sy;
	
	public Sprite(Texture t, float sx, float sy)
	{
		this.t = t;
		this.sx = sx;
		this.sy = sy;
	}
	public Sprite(float r, float g, float b, float sx, float sy)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.sx = sx;
		this.sy = sy;
	}
	
	public void render()
	{
		
		glColor3f(r,g,b);
		glBegin(GL_QUADS);
		{
			glVertex2f(0,0);
			glVertex2f(0,sy);
			glVertex2f(sx,sy);
			glVertex2f(sx,0);
			
			/*glTexCoord2f(0,0);
			glVertex2f(100,100);
			glTexCoord2f(0,1);
			glVertex2f(100+GameTextures.playerTexture.getTextureWidth(),100);
			glTexCoord2f(1,1);
			glVertex2f(100+GameTextures.playerTexture.getTextureWidth(),100+GameTextures.playerTexture.getTextureHeight());
			glTexCoord2f(1,0);
			glVertex2f(100,100+GameTextures.playerTexture.getTextureHeight());*/
		}
		glEnd();
	}

	public float getSx() {
		return sx;
	}

	public void setSx(float sx) {
		this.sx = sx;
	}

	public float getSy() {
		return sy;
	}

	public void setSy(float sy) {
		this.sy = sy;
	}
	public void setR(float r) {
		this.r = r;
	}
	public void setG(float g) {
		this.g = g;
	}
	public void setB(float b) {
		this.b = b;
	}

	
	
}
