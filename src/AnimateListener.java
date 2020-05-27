/**
 * @(#)TimerListener.java
 *
 *
 * @author 
 * @version 1.00 2019/5/2
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class AnimateListener implements ActionListener {
	
	private BasketballComponent component;
	private int power;
	private int angle;
	
    public AnimateListener(BasketballComponent c, int p, int a)
    {
    	component = c;
    	power = p;
    	angle = a;
    }
    
    public void actionPerformed(ActionEvent event)
    {
    	Timer source = (Timer)event.getSource();
    	component.shoot(power, angle);
    	if (component.outOfBounds())
    	{
    		component.reset();
    		source.stop();
    	}
    }
    
}