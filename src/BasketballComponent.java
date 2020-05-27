/**
 * @(#)BasketballComponent.java
 *
 *
 * @author 
 * @version 1.00 2019/5/1
 */
 
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BasketballComponent extends JComponent {
	

	private static final int ADD_Y = 40;
	private static final int ADD_X = 30;
	private static final int RANDOM_RANGE_MIN = 600;
	private static final int RANDOM_RANGE_WIDTH = 300;
	private static final float[] DASHED_ARRAY = {10.0f};
	private static final BasicStroke DASH_STROKE =
			new BasicStroke(3.0f, BasicStroke.CAP_SQUARE,
			BasicStroke.JOIN_MITER, 1.0f, DASHED_ARRAY, 0.0f);
	private static final int BOARD_X2 = 30;
	private static final int BOARD_X1 = 20;
	private static final int BOARD_MIN_Y = 40;
	private static final int BOARD_MAX_Y = 300;
	private static final int BOUNCE_MAX_Y = 205;
	private static final int FRONT_RIM_MAX_Y = 260;
	private static final int FRONT_RIM_MIN_Y = 180;
	private static final int FRONT_RIM_X3 = 140;
	private static final int FRONT_RIM_X2 = 130;
	private static final int FRONT_RIM_X1 = 160;
	private static final int WIDTH_NUMBER = 3;
	private static final int BACK_RIM_MAX_Y = 260;
	private static final int BACK_RIM_MIN_Y = 178;
	private static final int HOOP_START_Y = 100;
	private static final int LINE_START_X = 930;
	private static final int LINE_START_Y = 490;
	private static final int BALL_START_X = 900;
	private static final int BALL_START_Y = 450;
	private static final int X_BOUND = -300;
	private static final int Y_BOUND = 1100;
	private static final int GRAVITY = 2;
	private static final int BACK_RIM_X = 55;
	private static final int MAKE_X1 = 45;
	private static final int MAKE_X2 = 150;
	private static final int MAKE_Y1 = 220;
	private static final int MAKE_Y2 = 260;
	private static final int POWER_LENGTH = 125;
	private static final int DEGREES = 180;
	private static final int MAKE_VELOCITY = 4;
	private static final double POWER_MULTIPLIER = .6;
	private static final double VELOCITY_MULTIPLIER = .7;
	private static final double PROBABILITY = .5;
	
	private static boolean hitFrontRim = false;
	private static boolean hitBackRim = false;
	private static boolean madeIt = false;
	private static boolean hitBackboard = false;
	private static int score = 0;
	private static int highScore = 0;
	private static int ballStartX = BALL_START_X;
	private static int lineStartX = LINE_START_X;
	private static int ballStartY = BALL_START_Y;
	private static int lineStartY = LINE_START_Y;
	
	private Ball ball;
	private int lineX;
	private int lineY;
	private int deltaY;
	private int deltaX;
	private int power;
	private int angle;
	 
    public BasketballComponent()
    {
    	ball = new Ball(ballStartX, ballStartY, "ball.png");
    	lineX = 0;
    	lineY = 0;
    	deltaY = 0;
    	power = 0;
    	angle = 0;
    }
    
    public void paintComponent(Graphics gr)
    {
    	Graphics2D g2 = (Graphics2D) gr;
    	ball.draw(gr);
    	ImageIcon hoop = new ImageIcon("hoop.gif");
    	hoop.paintIcon(this, gr, 0, HOOP_START_Y);
    	g2.setStroke(DASH_STROKE);
    	g2.setColor(Color.BLACK);
    	Line2D.Double line = new Line2D.Double(lineStartX, lineStartY, lineX, lineY);
    	g2.draw(line);
    }
    
    public void setPower(int pow)
    {
    	power = (int) (pow * POWER_MULTIPLIER);
    }
    
    public void setAngle(int ang)
    {
    	angle = ang;
    }
    
    public void setInitialVelocity()
    {
    	double rad = angle * Math.PI / DEGREES;
    	deltaY = - (int) (power * Math.sin(rad));
    	deltaX = - (int) (power * Math.cos(rad));
    }
    
    public void shoot(int power, int angle)
    {
    	ball.changeX(deltaX);
    	ball.changeY(deltaY);
    	deltaY += GRAVITY;
    	repaint();
    	checkBoard();
    	checkFrontRim();
    	checkBackRim();
    	checkMake();
    }
    

    public void changeLine(int angle)
    {
    	double rad = angle * Math.PI / DEGREES;
    	int chgX = (int) (POWER_LENGTH * Math.cos(rad));
    	int chgY = (int) (POWER_LENGTH * Math.sin(rad));
    	lineX = lineStartX - chgX;
    	lineY = lineStartY - chgY;
    }
    
    public void reset()
    {
    	if (!madeIt)
    	{
    		score = 0;
    		BasketballViewer.updateScore(score, highScore);
    	}
    	else
    	{
    		ballStartX = (int)(Math.random() * RANDOM_RANGE_WIDTH) + RANDOM_RANGE_MIN;
    		lineStartX = ballStartX + ADD_X;
    		ballStartY = (int)(Math.random() * RANDOM_RANGE_WIDTH) + RANDOM_RANGE_WIDTH;
    		lineStartY = ballStartY + ADD_Y;
    		changeLine(angle);
    	}
    	ball.setX(ballStartX);
    	ball.setY(ballStartY);
    	deltaY = 0;
    	hitFrontRim = false;
    	hitBackRim = false;
    	madeIt = false;
    	hitBackboard = false;
    	BasketballViewer.hideSwish();
    	repaint();
    }
    
    public Ball getBall()
    {
    	return ball;
    }
    
    public void checkBoard()
    {
    	boolean checkY = (ball.getY() < BOARD_MAX_Y && ball.getY() > BOARD_MIN_Y);
    	boolean checkX = ball.getX() > BOARD_X1 + (deltaX / 2) && ball.getX() < BOARD_X2 - (deltaX / 2);
    	if (checkX && checkY)
    	{
    		deltaX = (int) (-deltaX * VELOCITY_MULTIPLIER);
        	deltaY = (int) (deltaY * VELOCITY_MULTIPLIER);
        	hitBackboard = true;
    	}
    }
    
    public void checkFrontRim()
    {
    	int ballX = ball.getX();
    	int ballY = ball.getY();
    	boolean checkX = ballX > FRONT_RIM_X1 + (deltaX / 2) && ballX < FRONT_RIM_X1 - (deltaX / 2);
    	boolean isXOpposite = deltaX > 0;
    	if (isXOpposite)
    	{
    		checkX = ballX > FRONT_RIM_X2 - (deltaX / 2)  && ballX < FRONT_RIM_X3 + (deltaX / 2);
    	}
    	boolean checkY = (ballY > FRONT_RIM_MIN_Y && ballY < FRONT_RIM_MAX_Y);
    	if (checkX && checkY && !hitFrontRim)
    	{
            deltaX = (-deltaX / 2);
            deltaY = (-deltaY / 2);
            if (Math.random() < PROBABILITY && ball.getY() < BOUNCE_MAX_Y)
            	deltaX = -deltaX;
            hitFrontRim = true;
            hitBackRim = false;
    	}
    }
    
    public void checkBackRim()
    {
    	int ballX = ball.getX();
    	int ballY = ball.getY();
    	boolean checkX = ballX > BACK_RIM_X + (deltaX / 2) && ballX < BACK_RIM_X - (deltaX / 2);
    	boolean checkY = (ballY > BACK_RIM_MIN_Y && ballY < BACK_RIM_MAX_Y);
    	if (checkX && checkY && !hitBackRim)
    	{
            deltaX = (-deltaX / 2);
            deltaY = (-deltaY / 2);
            if (Math.random() < PROBABILITY)
            	deltaX = 2;
            hitBackRim = true;
            hitFrontRim = false;
    	}
    }
    
    public void checkMake()
    {
    	int ballX = ball.getX();
    	int ballY = ball.getY();
    	boolean checkX = ballX > MAKE_X1 && ballX < MAKE_X2;
    	boolean checkY = (ballY > MAKE_Y1 && ballY < MAKE_Y2);
    	int makeWidth = (MAKE_X2 - MAKE_X1) / WIDTH_NUMBER;
    	if (checkX && checkY && !madeIt)
    	{
    		if (ballX < MAKE_X1 + makeWidth)
    			deltaX = MAKE_VELOCITY;
    		else if (ballX < MAKE_X1 + (2 * makeWidth))
    			deltaX = 0;
    		else
    			deltaX = -MAKE_VELOCITY;
    		madeIt = true;
    		score++;
    		if (!hitBackRim && !hitFrontRim && !hitBackboard)
    		{
    			score++;
    			BasketballViewer.showSwish();
    		}
    		if (score >= highScore)
    			highScore = score;
    		BasketballViewer.updateScore(score, highScore);
    	}
    }
    
    public boolean outOfBounds()
    {
    	return (ball.getY() >= Y_BOUND || ball.getX() < X_BOUND);
    }
    
    
    
}