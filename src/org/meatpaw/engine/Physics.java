package org.meatpaw.engine;

import org.lwjgl.util.Rectangle;
import org.meatpaw.game.Game;
import org.meatpaw.game.gameobject.Player;
import java.util.ArrayList;
public class Physics 
{
	
	
	
	public static boolean checkCollisionX(MoveableObject object1, GameObject object2)
	{
		if(object2.getType() == 0 || object2.getType() == 2)
		{
			return false;
		}
		if(object1.newRightSide > object2.leftSide && object1.newLeftSide < object2.leftSide && (Player.heading.equals("right") || Player.heading.equals("none")))
		{
			if(object1.bottom == object2.top)
				return false;
			if(object1.newBottom > object2.top)
				return false;
			if(object1.top <= object2.bottom)
				return false;
			Player.onRight = object2;
			return true;
		}
		if( object1.newLeftSide < object2.rightSide && object1.newRightSide > object2.rightSide && (Player.heading.equals("left") || Player.heading.equals("none")))
		{
			if(object1.bottom == object2.top)
				return false;
			if(object1.newBottom > object2.top)
				return false;
			if(object1.top <= object2.bottom)
				return false;
			Player.onLeft = object2;
			return true;
		}
		return false;
	}
	
	public static boolean checkCollisionY(MoveableObject object1, GameObject object2)
	{
		if(object2.getType() == 0 || object2.getType() == 2)
		{
			return false;
		}
		if(object1.newBottom < object2.top && object1.newTop > object2.top && Player.velocityY <= 0 )
		{
			if(object1.leftSide >= object2.rightSide)
				return false;
			if(object1.rightSide <= object2.leftSide)
				return false;
			Player.underneath = object2;
			return true;
		}
		if(object1.newTop > object2.bottom && object1.newBottom < object2.bottom && Player.velocityY > 0)
		{
			if(object1.leftSide >= object2.rightSide)
				return false;
			if(object1.rightSide <= object2.leftSide)
				return false;
			Player.above = object2;
			return true;
		}
		return false;
	}
}
//object1.newLeftSide < object2.leftSide && object1.newRightSide > object2.leftSide && !(object1.newBottom > object2.top || object1.newTop < object2.bottom
//(object1.newLeftSide < object2.rightSide && object1.newRightSide > object2.rightSide && !(object1.newBottom > object2.top || object1.newTop < object2.bottom))