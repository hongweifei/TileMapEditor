package fly.window.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public interface FlyMouseListener extends MouseListener,MouseMotionListener,MouseWheelListener
{
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseEntered(MouseEvent e);
    public void mouseExited(MouseEvent e);
    
    public void mouseDragged(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    
    public void mouseWheelMoved(MouseWheelEvent e);
}

