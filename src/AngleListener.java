import javax.swing.event.*;
import javax.swing.*;

public class AngleListener implements ChangeListener {
	
	private JLabel label;
	private BasketballComponent component;
	
	public AngleListener (JLabel l, BasketballComponent c)
	{
		label = l;
		component = c;
	}
	
	public void stateChanged(ChangeEvent event)
	{
		JSlider source = (JSlider)event.getSource();
		int value = source.getValue();
		label.setText("Angle: " + value + "\u00b0");
		component.changeLine(value);
		component.setAngle(value);
	}

}
