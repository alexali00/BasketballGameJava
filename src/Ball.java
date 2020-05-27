/**
 * @(#)Ball.java
 *
 *
 * @author 
 * @version 1.00 2019/5/1
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Ball {
	
	private ImageIcon ballPic;
	private int x;
	private int y;
		
	public Ball(int startX, int startY, String fileName) 
	{
		x = startX;
		y = startY;
		ballPic = new ImageIcon(fileName);
	}
	
	public void draw(Graphics gr)
	{
		ballPic.paintIcon(new BasketballComponent(), gr, x, y);
	}
    
	public void changeX(int chgX)
	{
		x += chgX;
	}
	
	public void changeY(int chgY)
	{
		y += chgY;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getWidth()
	{
		return ballPic.getIconWidth();
	}
}