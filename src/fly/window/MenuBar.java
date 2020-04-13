package fly.window;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fly.window.listener.FlyActionAndChangeListener;

public class MenuBar
{
	private JMenuBar menu_bar;		//菜单栏
	private JMenu[] menu;			//菜单
	private JMenuItem[][] menu_item;//菜单项
	
	private int menu_n = 0;			//菜单数量
	private int[] menu_item_n = {0};//菜单项数量
	
	public MenuBar(){this(1,1);}
	
	/*
	 * MAX_MENU_N		最大菜单数量
	 * MAX_MENU_ITEM_N	最大菜单项数量
	 * */
	public MenuBar(int MAX_MENU_N,int MAX_MENU_ITEM_N)
	{
		this.menu_bar = new JMenuBar();
		
		this.menu = new JMenu[MAX_MENU_N];//菜单
		this.menu_item = new JMenuItem[MAX_MENU_N][MAX_MENU_ITEM_N];//菜单项
		
		this.menu_item_n = new int[MAX_MENU_ITEM_N];
	}
	
	/*添加一个菜单*/
	public int AddMenu(String menu_text)
	{
		this.menu[this.menu_n] = new JMenu(menu_text);
		
		this.menu_bar.add(this.menu[this.menu_n]);//添加菜单
		
		this.menu_n++;//菜单数量++
		
		return this.menu_n - 1;//返回菜单索引
	}
	
	/*添加多个菜单*/
	public int[] AddMenus(String[] menu_text)
	{
		int[] menu_index = new int[menu_text.length];
		
		for(int i = 0;i < menu_text.length;i++)
		{
			menu_index[i] = i + menu.length;//菜单索引记录
			this.AddMenu(menu_text[i]);//添加一个菜单
		}
		
		return menu_index;//返回多个菜单的索引
	}
	
	/*添加一个菜单项*/
	public void AddMenuItem(int menu_index,String menu_item_text)
	{
		this.menu_item[menu_index][this.menu_item_n[menu_index]] = 
		new JMenuItem(menu_item_text);
		
		this.menu[menu_index].add
		(this.menu_item[menu_index][this.menu_item_n[menu_index]]);//添加菜单项
		
		this.menu_item_n[menu_index]++;
	}
	
	/*添加多个菜单项*/
	public void AddMenuItems(int menu_index,String[] menu_item_text)
	{
		for(int i = 0;i < menu_item_text.length;i++)//有多少个文本就循环多少次
		{
			this.AddMenuItem(menu_index, menu_item_text[i]);//添加一个菜单项
		}
	}
	
	/*往菜单插入分割符*/
	public void MenuInsertSeparator(int menu_index,int index)
	{this.menu[menu_index].insertSeparator(index);}
	
	/*给菜单项添加监听器*/
	public void MenuItemAddListener(int menu_index,int menu_item_index,FlyActionAndChangeListener listener)
	{
		this.menu_item[menu_index][menu_item_index].addActionListener(listener);
		this.menu_item[menu_index][menu_item_index].addChangeListener(listener);
	}
	
	/*获取...*/
	public JMenuBar GetMenuBar(){return this.menu_bar;}
	public JMenu GetMenu(int menu_index){return this.menu[menu_index];}
	public JMenuItem GetMenuItem(int menu_index,int menu_item_index)
	{return this.menu_item[menu_index][menu_item_index];}
}







