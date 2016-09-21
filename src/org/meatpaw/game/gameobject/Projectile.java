package org.meatpaw.game.gameobject;

import org.lwjgl.opengl.Display;
import org.meatpaw.engine.MoveableObject;
import org.meatpaw.engine.Physics;
import org.meatpaw.game.Game;
import org.meatpaw.engine.GameObject;

public class Projectile extends MoveableObject
{
	private String bheading;
	private float x;
	private float initSpeed;
	
	public Projectile(float x, float y, float r, float g, float b, float sx, float sy, int type, String name, String heading)
	{
		init(x,y,r,g,b,sx,sy,type,name);
		this.bheading = heading;
	}
	public static void makeBullet()
	{
		Player.bulletsOnScreen++;
		Projectile bullet = new Projectile(Game.player.getX() + Player.WIDTH/2, Game.player.getY() + Player.HEIGHT/2, 1f, 1f, 1f, 10, 10, 2, "bullet" + Game.objects.size(), Player.previousHeading);
		Game.objects.add(bullet);
	}
	
	public void update()
	{
		for(GameObject go : Game.objects)
		{
			if(Physics.checkCollisionX(this, go))
			{
				
			}
		}
		if(bheading.equals("right") || bheading.equals("none"))
			posX += 30;
		if(bheading.equals("left"))
			posX -= 30;
		//System.out.println(name + ": " + bheading);
		if(posX < 0 || posX > Display.getWidth())
		{
			remove = true;
			Player.bulletsOnScreen--;
		}
	
	}
}
