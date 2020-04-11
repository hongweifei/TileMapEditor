package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;

import fly.graphics.Event;
import fly.graphics.Renderer;
import fly.graphics.Scene;
import fly.window.FlyMenuBar;
import fly.window.Listener;
import fly.window.Window;

public class Main
{
	static Window window;
	static FlyMenuBar menu;
	
	public static void main(String[] args) 
	{
		window = new Window("Tile Map Editor");
		
		menu = new FlyMenuBar();
		menu.AddMenu("文件");
		
		String[] menu_item_title = {"新建","打开","保存","另存为","关闭"};
		menu.AddMenuItems(0, menu_item_title);
		menu.MenuInsertSeparator(0, 2);
		menu.MenuInsertSeparator(0, 5);
		
		window.AddMenuBar(menu);
		
		menu.MenuItemAddListener(4, new Listener() {

			@Override
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("Close");
				System.exit(0);
			}

			@Override
			public void stateChanged(ChangeEvent event)
			{
				
			}
			
		});
		
		Scene scene = new Scene(window);
		scene.GetRenderer().SetRenderEvent(new Event(){

			@Override
			public void invoke(Object object)
			{
				Renderer renderer = scene.GetRenderer();
				Graphics g = (Graphics)object;
				
				renderer.DrawText(g, "Hello world!!!", 100, 100);
			}
			
		});
	}
	
	
}
