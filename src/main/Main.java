package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.event.ChangeEvent;

import fly.graphics.Event;
import fly.graphics.FlyRenderer;
import fly.graphics.FlyScene;
import fly.window.FlyMenuBar;
import fly.window.FlyActionAndChangeListener;
import fly.window.FlyList;
import fly.window.Window;

public class Main
{
	static Window window;
	static FlyMenuBar menu;
	
	public static void main(String[] args) throws MalformedURLException, IOException 
	{
		window = new Window("Tile Map Editor");
		
		menu = new FlyMenuBar();
		menu.AddMenu("文件");
		
		String[] menu_item_title = {"新建","打开","保存","另存为","关闭"};
		menu.AddMenuItems(0, menu_item_title);
		menu.MenuInsertSeparator(0, 2);
		menu.MenuInsertSeparator(0, 5);
		
		FlyList list = new FlyList();
		
		window.SetMenuBar(menu);
		
		menu.MenuItemAddListener(4, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{System.out.println("Window close");System.exit(0);}
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		FlyScene scene = new FlyScene(window);
		scene.GetRenderer().SetRenderEvent(new Event(){

			@Override
			public void invoke(Object object)
			{
				FlyRenderer renderer = scene.GetRenderer();
				Graphics g = (Graphics)object;
				
				renderer.DrawText(g, "Hello world!!!", 100, 100);
			}
			
		});
	}
	
	
}
