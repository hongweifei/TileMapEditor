package main;

import java.awt.Graphics2D;

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
		
	}
	
	
}
