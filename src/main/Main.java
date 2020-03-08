package main;

import java.awt.Graphics2D;

import window.Menu;
import window.Window;

public class Main 
{
	static Window window;
	static Menu menu;
	static Graphics2D g;
	
	public static void main(String[] args) 
	{
		window = new Window("Tile Map Editor",800,600);
		
		menu = new Menu(9,9);
		menu.AddMenu("文件");
		
		String[] menu_item_title = {"新建","打开","保存","另存为"};
		menu.AddMenuItems(0, menu_item_title);
		menu.InsertSeparator(0, 2);
		
		window.AddMenu(menu);
		
		
	}
	
	
}
