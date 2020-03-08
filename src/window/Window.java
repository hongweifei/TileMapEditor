package window;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Window
{
	private JFrame window = new JFrame();
	
	public Window(String title,int width,int height)
	{
		System.out.println("loading");
		
		this.window.setTitle(title);
		this.window.setSize(width, height);
		this.window.setLayout(new BorderLayout());
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void SetVisble(boolean arg)
	{
		this.window.setVisible(arg);
	}
	
	public void AddMenu(Menu menu)
	{
		this.window.setJMenuBar(menu.GetMenuBar());
		this.window.setVisible(true);
	}
	
}
