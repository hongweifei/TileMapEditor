package fly.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public interface Listener extends ActionListener,ChangeListener
{
	
	public abstract void actionPerformed(ActionEvent event);
	
	public abstract void stateChanged(ChangeEvent event);
	
}
