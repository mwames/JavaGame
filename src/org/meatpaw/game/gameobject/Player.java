package org.meatpaw.game.gameobject;
import java.util.ArrayList;
import java.util.Random;

import org.meatpaw.game.Game;
import org.meatpaw.engine.GameObject;
import org.meatpaw.engine.GameTextures;
import org.meatpaw.engine.MoveableObject;
import org.meatpaw.engine.Physics;
import org.meatpaw.engine.Time;
import org.meatpaw.engine.Sprite;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Player extends MoveableObject
{
	public static final float HEIGHT = 64;
	public static final float WIDTH = 32;
	public static int bulletsOnScreen = 0;
	public static String previousHeading = "right";
	public static String heading = "right";
	public static String collisionDirection = "";
	public static boolean prevJumpState = false;
	public static boolean currentJumpState = false;
	public static boolean prevShootState = false;
	public static boolean currentShootState = false;
	private boolean jumping = false;
	public static boolean isJumpRequested = false;
	public static boolean onGround = false;
	public static GameObject underneath = new Platform(0,0,0,0,0,0,0,0,"underneath");
	public static GameObject onRight = new Platform(0,0,0,0,0,0,0,0,"onRight");
	public static GameObject onLeft = new Platform(0,0,0,0,0,0,0,0,"onLeft");
	public static GameObject above = new Platform(0,0,0,0,0,0,0,0,"above");
	private static Random rand = new Random();
	 
	static Controller controller;
	
	/*This is the beginning of the player class. The player will have access to the positions of all game objects.
	 * We can use this to implement collision detection, which has been started.
	 * Controls are also implemented in this class. Please make sure the actual movement is made in the update method
	 * and not in the get input method. This is important for proper collision.
	 */
	
	
	public Player(float x, float y)
	{
		init(x, y, 0f, 1f, 0f, WIDTH, HEIGHT, 0, name);
		controller = Game.getController();
		accelerationX = 0.1f;
		accelerationY = 0.5f;
		type = 0;
		newPosX = x;
		newPosY = y;
		maxSpeedX = 25;
		name = "player";
	}
	
	
	//getInput is just for finding the proposed position of the next frame. Please do not modify player coordinates directly from this method.
	public void getInput()
	{
		
		if(controller.isButtonPressed(4))
		{
			Display.destroy();
		}
		if(controller.getPovX() == -1)
		{
			moveX(-1);
			if(!heading.equals("none"))
				previousHeading = heading;
			heading = "left";
			moveX = true;
		}
		if(controller.getPovX() == 1)
		{
			moveX(1);
			if(!heading.equals("none"))
				previousHeading = heading;
			heading = "right";
			moveX = true;
		}
		if(controller.isButtonPressed(3))
		{
			this.setR(rand.nextFloat());
			this.setG(rand.nextFloat());
			this.setB(rand.nextFloat());
		}
		if(controller.isButtonPressed(2) && !prevShootState)
		{
			currentShootState = true;
			if(bulletsOnScreen < 3)
				Projectile.makeBullet();
		}
		if(!controller.isButtonPressed(2))
		{
			currentShootState = false;
		}
		if(controller.isButtonPressed(0))
		{
			isJumpRequested = false;
			currentJumpState = true;
			if(currentJumpState && !prevJumpState)
			{
				isJumpRequested = true;
			}
		
		}
		if(!controller.isButtonPressed(0))
		{
			currentJumpState = false;
			jumping = false;
		}
		if(isJumpRequested)
		{
			if(onGround == true)
			{
				jumping = true;
				accelerationY = 0.5f;
				onGround = false;
			}
		}
		if(!(controller.getPovX() == -1) && (!(controller.getPovX() == 1)))
		{
			speedX = 0;
			if(!heading.equals("none"))
				previousHeading = heading;
			heading = "none";
		}
	}

	
	// this code, when implemented will make the camera follow the player.
	/*public void render()
	{
		glTranslatef((float)(Display.getWidth()/2 - Player.WIDTH/2), (float)(Display.getHeight()/2 - Player.HEIGHT/2),0);
		sprite.render();
		glTranslatef(-posX,-posY,0);
		
		
	}*/
	
	//this method overrides the update method of the GameObjet class. Only player specific code here please.
	public void update()
	{
		prevShootState = currentShootState;
		prevJumpState = currentJumpState;
		leftSide = posX;
		rightSide = posX + WIDTH;
		newLeftSide = newPosX;
		newRightSide = newPosX + WIDTH;
		bottom = posY;
		top = posY + HEIGHT;
		newBottom = newPosY;
		newTop = newPosY + HEIGHT;
		Game.playerRect.setX(newPosX);
		Game.playerRect.setY(newPosY);
		if(posY - underneath.getTop() >= 2)
			onGround = false;
		
////////////////////////////////////////////////////////		

		moveY();
		System.out.println("Position Y " + posY);
		System.out.println("New Position Y: " + newPosY);
		if(velocityY == 0)
		{
			onGround = false;
			newPosY -= 1;
		}
		
		willCollideY = collisionDetectorY();
		if(willCollideY == false)
		{
			underneath = Game.player;
			above = Game.player;
		}
		if(isJumpRequested == true || onGround == false)
		{
			moveY = true;
		}
		if(willCollideY == true)
		{
			moveY = false;
			nudgeUpY(/* pass in go*/);
		}
		
		
		if(moveY)
		{
			posY = newPosY;
		}
		
///////////////////////////////////////////////////////////		
		
		willCollideX = collisionDetectorX();
		if(willCollideX == false)
		{
			onRight = Game.player;
			onLeft = Game.player;
		}
		if(willCollideX)
		{
			moveX = false;
			nudgeUpX();
		}
		if(willCollideX == false )
		{
			moveX = true;
		}
		if(moveX)
			posX = newPosX;	
		
/////////////////////////////////////////////////////////////
		//just some debugging tools here
		//System.out.println(previousHeading);
		//System.out.println("jumping: " + jumping);
		System.out.println("on ground? " + onGround);
		//System.out.println("Position Y " + posY);
		//System.out.println("New Position Y: " + newPosY);
		//System.out.println("underneath: " + underneath.name);
		//System.out.println("onRight: " + onRight.name);
		//System.out.println("onLeft: " + onLeft.name);
		//System.out.println("above: " + above.name);
		//System.out.println("Speed y: " + speedY);
		//System.out.println("Velocity y: " + velocityY);
		//System.out.println("acceleration y: " + accelerationY);
		//System.out.println("Obstacle y: " + underneath.name);
		//System.out.println("will collide Y? " + willCollideY);
		//System.out.println("will collide x? " + willCollideX);
		//for(GameObject go : confirmedCollisions)
		//{
		//	System.out.println("collision type: " + go.getType());
		//	System.out.println("collision x: " + go.getX());
		//}
		//end debugging
		moveX = false;
	}
	
	public void moveY()
	{
		newPosY = posY + getVelocityY();
	}
	public float getVelocityY()
	{
		velocityY = getSpeedY();
		return velocityY;
	}
	public float getSpeedY()
	{
		//if((jumping == false) && (onGround == true))
		//{
		//	speedY = 0;
		//	accelerationY = 0f;
		//}
		if(jumping == false && onGround == false)
		{
			accelerationY -= gravity;
			speedY = speedY  + accelerationY * Time.getDelta();
			if(velocityY > 0)
			{
				speedY *= 0.5f;
			}
			
		}
		if((jumping == true))
		{
		accelerationY -= gravity/1.25;
		speedY = speedY + accelerationY  * Time.getDelta();
		}
		
		if(speedY > maxSpeedY)
		{
			speedY = maxSpeedY;
			return maxSpeedY;
		}
		else if(speedY < (maxSpeedY * -1))
		{
			speedY = (maxSpeedY * -1);
			return maxSpeedY * -1;
		}
		else
			return speedY;
	}
	public void moveX(float directionX)
	{
		newPosX = posX + getVelocityX(directionX);
		
	}
	public float getVelocityX(float directionX)
	{
		velocityX = getSpeedX() * directionX;
		return velocityX;
	}
	public float getSpeedX()
	{
		if(heading.equals("right")&&(!(previousHeading.equals("right"))) || heading.equals("left")&&(!(previousHeading.equals("left"))))
		{
			speedX = 0;
			return speedX;
		}
		if(heading.equals("none"))
		{
			speedX = 0;
			return speedX;
		}
		if(speedX >= maxSpeedX)
			return speedX;
		else
		{
			speedX = speedX + (accelerationX * Time.getDelta());
			return speedX;
		}
	}
	
	//if proposed player position is inside an obstacle we will not update player position
	public boolean collisionDetectorY()
	{
		for(GameObject go : obstacles)
		{
			//System.out.println(go.name);
			if(Physics.checkCollisionY(this, go))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean collisionDetectorX()
	{
		for(GameObject go : obstacles)
		{
			if(Physics.checkCollisionX(this, go))
			{
				return true;
			}
		}
		return false;
	}
	
	public void nudgeUpX()
	{
			if (heading == "right" && !(onRight.name.equals("player")))
				posX = onRight.getX() - WIDTH; 
			if (heading == "left" && !(onLeft.name.equals("player")))
				posX = onLeft.getX() + onLeft.getSX(); 
	}
	public void nudgeUpY()
	{
			if (!isJumpRequested && velocityY <= 0 && underneath != Game.player)
			{
				posY = underneath.getY() + underneath.getSY();
				onGround = true;
				jumping = false;
				speedY = 0;
			}
			if(jumping == true && (above != Game.player))
			{
				posY = above.getY() - HEIGHT;
				//jumping = false;
			}
				 
	}
}


