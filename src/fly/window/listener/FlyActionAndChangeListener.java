package fly.window.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public interface FlyActionAndChangeListener extends FlyActionListener,FlyChangeListener
{
	public void actionPerformed(ActionEvent event);
	public void stateChanged(ChangeEvent event);
}

interface FlyActionListener extends ActionListener
{
	public void actionPerformed(ActionEvent event);
}

interface FlyChangeListener extends ChangeListener
{
	public void stateChanged(ChangeEvent event);
}







