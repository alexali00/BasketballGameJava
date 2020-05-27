
import javax.swing.event.*;
import javax.swing.*;

public class PowerListener implements ChangeListener {
	
	private JLabel label;
	private BasketballComponent component;
	
	public PowerListener (JLabel l, BasketballComponent c)
	{
		label = l;
		component = c;
	}
	
	public void stateChanged(ChangeEvent event)
	{
		JSlider source = (JSlider)event.getSource();
		label.setText("Power: " + source.getValue());
		component.setPower(source.getValue());
	}

}
