package fly.window;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;

import fly.window.listener.FlyActionAndChangeListener;

/**
 * @file FlyMenuBar.java
 * @author Fly
 */


public class FlyMenuBar
{
	private JMenuBar menu_bar;
	private ArrayList<JMenu> menu;
	private ArrayList<JMenuItem> menu_item;

	public FlyMenuBar()
	{
		/*初始化*/
		menu_bar = new JMenuBar();
		menu = new ArrayList<JMenu>();
		menu_item = new ArrayList<JMenuItem>();
	}

	/**添加一个菜单*/
	public int AddMenu(){return AddMenu("");}
	public int AddMenu(String menu_text)
	{
		menu.add(new JMenu(menu_text));//往菜单列表添加菜单

		menu_bar.add(menu.get(menu.size() - 1));//将菜单添加至菜单栏

		return menu.size() - 1;//返回菜单索引
	}

	/**添加多个菜单*/
	public int[] AddMenus(int n)//返回菜单索引
	{String[] title = new String[n];return this.AddMenus(title);}
	public int[] AddMenus(String... menu_text)//返回菜单索引
	{
		int[] menu_index = new int[menu_text.length];//各个菜单的索引

		for(int i = 0;i < menu_text.length;i++)
			menu_index[i] = this.AddMenu(menu_text[i]);//添加一个菜单

		return menu_index;//返回各个菜单索引
	}

	/**添加一个菜单项*/
	public int AddMenuItem(int menu_index){return this.AddMenuItem(menu_index, "");}
	public int AddMenuItem(int menu_index,String menu_item_text)
	{
		menu_item.add(new JMenuItem(menu_item_text));//添加菜单项
		menu.get(menu_index).add(menu_item.get(menu_item.size() - 1));//往菜单添加菜单项
		return menu_item.size() - 1;//返回菜单项索引,索引等于菜单项数量 -1
	}

	/**添加多个菜单项*/
	public int[] AddMenuItems(int menu_index,int n)// menu_index 为菜单索引   n 为菜单项数量
	{String[] text = new String[n];return AddMenuItems(menu_index,text);}
	public int[] AddMenuItems(int menu_index,String... menu_item_text)
	{
		int[] index = new int[menu_item_text.length];//各个菜单的索引

		for(int i = 0;i < menu_item_text.length;i++)
			index[i] = this.AddMenuItem(menu_index,menu_item_text[i]);//添加一个菜单项

		return index;//返回各个菜单项索引
	}

	/**移除菜单*/
	public void RemoveMenu(int menu_index){menu_bar.remove(menu_index);menu.remove(menu_index);}

	/**菜单插入分割线*/
	public void MenuInsertSeparator(int menu_index,int index)
	{menu.get(menu_index).insertSeparator(index);}

	/**菜单项添加监听器*/
	public void MenuItemAddListener(int menu_item_index,FlyActionAndChangeListener listener)
	{
		/*给菜单项添加ActionListener和ChangeListener*/
		menu_item.get(menu_item_index).addActionListener(listener);
		menu_item.get(menu_item_index).addChangeListener(listener);
	}


	public void MenuItemAddListener(int menu_item_index,ActionListener listener)
	{menu_item.get(menu_item_index).addActionListener(listener);}

	public void MenuItemAddListener(int menu_item_index,ChangeListener listener)
	{menu_item.get(menu_item_index).addChangeListener(listener);}



	/*获取各项属性*/
	public JMenuBar GetMenuBar(){return menu_bar;}

	public JMenu GetMenu(int menu_index){return menu.get(menu_index);}
	public JMenu GetMenu(String menu_text)//返回第一个找到的
	{
		int index = 0;//菜单索引
		for(int i = 0;i < menu.size();i++)//遍历菜单文本
		{
			if(menu.get(i).getText() == menu_text)//若菜单文本是要找的文本，结束循环
			{
				index = i;//索引记录
				break;//结束循环
			}
		}
		return menu.get(index);//返回菜单
	}

	public JMenuItem GetMenuItem(int menu_item_index){return menu_item.get(menu_item_index);}
	public JMenuItem GetMenuItem(String menu_item_text)//返回第一个找到的
	{
		int index = 0;//菜单项索引
		for(int i = 0;i < menu_item.size();i++)//遍历菜单项文本
		{
			if(menu_item.get(i).getText() == menu_item_text)//若菜单项文本是要找的文本，结束循环
			{
				index = i;//索引记录
				break;//结束循环
			}
		}
		return menu_item.get(index);//返回索引
	}



	/*通过菜单文本找到菜单索引*/
	public int GetMenuIndex(String menu_text)
	{
		int index = 0;//菜单索引
		for(int i = 0;i < menu.size();i++)//遍历菜单文本
		{
			if(menu.get(i).getText() == menu_text)//若菜单文本是要找的文本，结束循环
			{
				index = i;//索引记录
				break;//结束循环
			}
		}
		return index;//返回菜单
	}


	/*通过菜单项文本找到菜单索引*/
	public int GetMenuItemIndex(String menu_item_text)
	{
		int index = 0;//菜单项索引
		for(int i = 0;i < menu_item.size();i++)//遍历菜单项文本
		{
			if(menu_item.get(i).getText() == menu_item_text)//若菜单项文本是要找的文本，结束循环
			{
				index = i;//索引记录
				break;//结束循环
			}
		}
		return index;//返回索引
	}




}









