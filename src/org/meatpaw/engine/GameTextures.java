package org.meatpaw.engine;
import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GameTextures 
{
	public static Texture playerTexture;
	
	public static void init()
	{
		try {
			playerTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/megaman.png"));
			//System.out.println("Texture loaded: "+playerTexture);
			//System.out.println(">> Image width: "+playerTexture.getImageWidth());
			//System.out.println(">> Image height: "+playerTexture.getImageHeight());
			//System.out.println(">> Texture width: "+playerTexture.getTextureWidth());
			//System.out.println(">> Texture height: "+playerTexture.getTextureHeight());
			//System.out.println(">> Texture ID: "+playerTexture.getTextureID());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
