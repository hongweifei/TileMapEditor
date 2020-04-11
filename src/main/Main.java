package main;

import java.awt.Graphics;

import fly.graphics.Event;
import fly.graphics.Renderer;
import fly.graphics.Scene;
import fly.window.FlyMenuBar;
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
		
		String[] menu_item_title = {"新建","打开","保存","另存为"};
		menu.AddMenuItems(0, menu_item_title);
		menu.MenuInsertSeparator(0, 2);
		
		window.AddMenuBar(menu);
		
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
