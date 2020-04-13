package fly.window;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuKeyListener;

import fly.window.listener.FlyActionAndChangeListener;
import fly.window.widget.FlyWidget;
import fly.window.widget.组件;

public class FlyPopupMenu
{
	private JPopupMenu popup_menu;
	private ArrayList<JMenuItem> menu_item;
	
	public FlyPopupMenu(){this.popup_menu = new JPopupMenu();menu_item = new ArrayList<JMenuItem>();}
	public FlyPopupMenu(String label)
	{
		this.popup_menu = new JPopupMenu(label);
		menu_item = new ArrayList<JMenuItem>();
	}
	
	
	/*设置菜单可视*/
	public void SetVisible(boolean b){popup_menu.setVisible(b);}
	public void Show(Component invoker, int x, int y){popup_menu.show(invoker, x, y);}
	public void Show(Window window, int x, int y){popup_menu.show(window.GetFrame(), x, y);}
	public void Show(窗口 window, int x, int y){popup_menu.show(window.获取窗口(), x, y);}
	public void Show(FlyWidget widget, int x, int y){popup_menu.show(widget.Get(), x, y);}
	public void Show(组件 widget, int x, int y){popup_menu.show(widget.获取(), x, y);}
	
	
	
	/*添加菜单监听器*/
	public void AddMenuKeyListener(MenuKeyListener listener){popup_menu.addMenuKeyListener(listener);}
	
	
	/*添加菜单项监听器*/
	public void AddActionListener(int index,ActionListener listener)
	{menu_item.get(index).addActionListener(listener);}
	
	/*添加菜单项监听器*/
	public void AddChangeListener(int index,ChangeListener listener)
	{menu_item.get(index).addChangeListener(listener);}
	
	/*添加菜单项监听器*/
	public void AddActionAndChangeListener(int index,FlyActionAndChangeListener listener)
	{
		menu_item.get(index).addActionListener(listener);
		menu_item.get(index).addChangeListener(listener);
	}
	
	
	
	/*添加一个菜单项*/
	public int AddItem(String text)
	{
		this.menu_item.add(new JMenuItem(text));
		this.popup_menu.add(this.menu_item.get(this.menu_item.size() - 1));
		return this.menu_item.size() - 1;
	}
	
	/*添加  n 个菜单项*/
	public int[] AddItems(String... text)
	{
		int[] index = new int[text.length];
		for(int i = 0;i < text.length;i++)
			index[i] = this.AddItem(text[i]);
		
		return index;
	}
}






