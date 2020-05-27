/**
 * @(#)BasketballViewer.java
 *
 *
 * @author 
 * @version 1.00 2019/5/1
 */
 

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;

public class BasketballViewer {
	
	private static final int FRAME_Y = 50;
	private static final int FRAME_X = 300;
	private static final int SHOOT_HEIGHT = 50;
	private static final int SHOOT_WIDTH = 70;
	private static final int SHOOT_Y1 = 632;
	private static final int SHOOT_X1 = 1080;
	private static final int POWERLABEL_HEIGHT = 20;
	private static final int POWERLABEL_WIDTH = 40;
	private static final int POWERLABEL_Y1 = 605;
	private static final int POWERLABEL_X1 = 1150;
	private static final int POWERCOUNT_HEIGHT = 20;
	private static final int POWERCOUNT_WIDTH = 100;
	private static final int POWERCOUNT_Y1 = 375;
	private static final int POWERCOUNT_X1 = 1150;
	private static final int TICK_SPACING_2 = 10;
	private static final int POWERSLIDER_HEIGHT = 200;
	private static final int POWERSLIDER_WIDTH = 50;
	private static final int POWERSLIDER_Y1 = 400;
	private static final int POWERSLIDER_X1 = 1150;
	private static final int ANGLE_LABEL_HEIGHT2 = 20;
	private static final int ANGLE_LABEL_WIDTH2 = 40;
	private static final int ANGLE_LABEL_Y2 = 605;
	private static final int ANGLE_LABEL_X2 = 1050;
	private static final int ANGLE_LABEL_HEIGHT1 = 20;
	private static final int ANGLE_LABEL_WIDTH1 = 100;
	private static final int ANGLE_LABEL_Y1 = 375;
	private static final int ANGLE_LABEL_X1 = 1050;
	private static final int ANGLE_SLIDER_HEIGHT = 200;
	private static final int ANGLE_SLIDER_WIDTH = 50;
	private static final int ANGLE_Y = 400;
	private static final int ANGLE_X = 1050;
	private static final int HIGHSCORE_FONT_SIZE = 18;
	private static final int HIGHSCORE_HEIGHT = 45;
	private static final int HIGHSCORE_WIDTH = 155;
	private static final int HIGHSCORE_Y = 63;
	private static final int HIGHSCORE_X = 550;
	private static final int BORDER_SIZE = 5;
	private static final int SCORE_FONT_SIZE = 25;
	private static final int SCORE_HEIGHT = 45;
	private static final int SCORE_WIDTH = 155;
	private static final int SCORE_Y = 25;
	private static final int SCORE_X = 550;
	private static final int SWISH_FONT_SIZE = 30;
	private static final int SWISH_HEIGHT = 45;
	private static final int SWISH_WIDTH = 155;
	private static final int SWISH_Y = 300;
	private static final int SWISH_X = 570;
	private static final int TICK_SPACING_1 = 5;
	private static final int MINIMUM_ANGLE = 25;
	private static final int MAXIMUM_ANGLE = 75;
	private static final int MAXIMUM_POWER = 100;
	private static final int WIDTH = 1300;
	private static final int HEIGHT = 1000;
			
	private static BasketballComponent component;
	private static JFrame frame;
	private static JLabel scoreLabel;
	private static JLabel highScoreLabel;
	private static JLabel swishLabel;
	
	public static void main(String[] args)
	{
		frame = new JFrame("Basketball");
		frame.setSize(WIDTH, HEIGHT);
		frame.setBounds(0, 0, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		component = new BasketballComponent();
		component.setBounds(0, 0, WIDTH, HEIGHT);
		component.changeLine(MINIMUM_ANGLE);
		
		swishLabel = new JLabel("");
		swishLabel.setBounds(SWISH_X, SWISH_Y, SWISH_WIDTH, SWISH_HEIGHT);
		Font swishF = new Font("TimesRoman", Font.BOLD, SWISH_FONT_SIZE);
		swishLabel.setFont(swishF);
		swishLabel.setForeground(Color.blue);
		frame.getContentPane().add(swishLabel);
		
		scoreLabel = new JLabel("  Score: 0  ");
		scoreLabel.setBounds(SCORE_X, SCORE_Y, SCORE_WIDTH, SCORE_HEIGHT);
		Font f = new Font("TimesRoman", Font.BOLD, SCORE_FONT_SIZE);
		scoreLabel.setFont(f);
		scoreLabel.setOpaque(true);
		scoreLabel.setForeground(Color.yellow);
		scoreLabel.setBackground(Color.black);
		Border border = BorderFactory.createLineBorder(Color.gray, BORDER_SIZE);
		scoreLabel.setBorder(border);
		frame.getContentPane().add(scoreLabel);
		
		highScoreLabel = new JLabel("  High Score: 0 ");
		highScoreLabel.setBounds(HIGHSCORE_X, HIGHSCORE_Y, HIGHSCORE_WIDTH, HIGHSCORE_HEIGHT);
		Font f2 = new Font("TimesRoman", Font.BOLD, HIGHSCORE_FONT_SIZE);
		highScoreLabel.setFont(f2);
		highScoreLabel.setOpaque(true);
		highScoreLabel.setForeground(Color.red);
		highScoreLabel.setBackground(Color.black);
		highScoreLabel.setBorder(border);
		frame.getContentPane().add(highScoreLabel);
		
		JSlider angleSlider = new JSlider(JSlider.VERTICAL, MINIMUM_ANGLE, MAXIMUM_ANGLE, MINIMUM_ANGLE);
		angleSlider.setBounds(ANGLE_X, ANGLE_Y, ANGLE_SLIDER_WIDTH, ANGLE_SLIDER_HEIGHT);
		angleSlider.setMajorTickSpacing(TICK_SPACING_2);
		angleSlider.setMinorTickSpacing(2);
		angleSlider.setPaintTicks(true);
		angleSlider.setPaintLabels(true);
		JLabel currentAngle = new JLabel("Angle: 30\u00b0");
		currentAngle.setBounds(ANGLE_LABEL_X1, ANGLE_LABEL_Y1, ANGLE_LABEL_WIDTH1, ANGLE_LABEL_HEIGHT1);
		frame.getContentPane().add(currentAngle);
		angleSlider.addChangeListener(new AngleListener(currentAngle, component));
		frame.getContentPane().add(angleSlider);
		
		JLabel angleLabel = new JLabel("Angle");
		angleLabel.setBounds(ANGLE_LABEL_X2, ANGLE_LABEL_Y2, ANGLE_LABEL_WIDTH2, ANGLE_LABEL_HEIGHT2);
		frame.getContentPane().add(angleLabel);
		
		JSlider powerSlider = new JSlider(JSlider.VERTICAL, 0, MAXIMUM_POWER, 0);
		powerSlider.setBounds(POWERSLIDER_X1, POWERSLIDER_Y1, POWERSLIDER_WIDTH, POWERSLIDER_HEIGHT);
		powerSlider.setMajorTickSpacing(TICK_SPACING_2);
		powerSlider.setMinorTickSpacing(TICK_SPACING_1);
		powerSlider.setPaintTicks(true);
		powerSlider.setPaintLabels(true);
		JLabel powerCount = new JLabel("Power: 0");
		powerCount.setBounds(POWERCOUNT_X1, POWERCOUNT_Y1, POWERCOUNT_WIDTH, POWERCOUNT_HEIGHT);
		frame.getContentPane().add(powerCount);
		powerSlider.addChangeListener(new PowerListener(powerCount, component));
		frame.getContentPane().add(powerSlider);
		
		JLabel powerLabel = new JLabel("Power");
		powerLabel.setBounds(POWERLABEL_X1, POWERLABEL_Y1, POWERLABEL_WIDTH, POWERLABEL_HEIGHT);
		frame.getContentPane().add(powerLabel);
		
		JButton shoot = new JButton("Shoot");
		shoot.setBounds(SHOOT_X1, SHOOT_Y1, SHOOT_WIDTH, SHOOT_HEIGHT);
		int power = powerSlider.getValue();
		int angle = angleSlider.getValue();
		shoot.addActionListener(new ShootListener(component, power, angle));
		frame.add(shoot);
		
		frame.add(component);
		frame.setLocation(FRAME_X, FRAME_Y);
		frame.setVisible(true);
	}
	
	public static void updateScore(int score, int highScore)
	{
		scoreLabel.setText("  Score: " + score + " ");
		highScoreLabel.setText("  High Score: " + highScore + " ");
	}
	
	public static void showSwish()
	{
		swishLabel.setText("Swish! +2");
		
	}
	
	public static void hideSwish()
	{
		swishLabel.setText("");
		
	}
	

}