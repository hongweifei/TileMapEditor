package fly.window.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface FlyKeyListener extends KeyListener
{
	public void keyTyped(KeyEvent e);
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}



