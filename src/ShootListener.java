/**
 * @(#)ShootListener.java
 *
 *
 * @author 
 * @version 1.00 2019/5/2
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ShootListener implements ActionListener 
{
	private static final int DELAY = 32;
	
    private BasketballComponent component;
    private int power;
    private int angle;
	
	
    public ShootListener(BasketballComponent c, int p, int a) 
    {
    	component = c;
    	power = p;
    	angle = a;
    }
    
    public void actionPerformed(ActionEvent event)
    {
    	component.setInitialVelocity();
    	Timer t = new Timer(DELAY, new AnimateListener(component, power, angle));
    	t.start();
    }

}