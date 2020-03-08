package window;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu 
{
	private JMenuBar menu_bar;//菜单栏
	private JMenu[] menu;//菜单
	private JMenuItem[][] menu_item;//菜单项
	
	private int menu_n = 0;
	private int[] menu_item_n = {0};
	
	public Menu(int MAX_MENU_N,int MAX_MENU_ITEM_N)
	{
		this.menu_bar = new JMenuBar();
		
		this.menu = new JMenu[MAX_MENU_N];//菜单
		this.menu_item = new JMenuItem[MAX_MENU_N][MAX_MENU_ITEM_N];//菜单项
		
		this.menu_item_n = new int[MAX_MENU_ITEM_N];
	}
	
	public int AddMenu(String menu_title)
	{
		this.menu[this.menu_n] = new JMenu(menu_title);
		
		this.menu_bar.add(this.menu[this.menu_n]);//添加菜单
		
		this.menu_n++;
		
		return this.menu_n - 1;//菜单索引
	}
	
	public int[] AddMenus(String[] menu_title)
	{
		int[] menu_index = new int[menu_title.length];
		
		for(int i = 0;i < menu_title.length;i++)
		{
			menu_index[i] = i;
			this.AddMenu(menu_title[i]);
		}
		
		return menu_index;
	}
	
	public void AddMenuItem(int menu_index,String menu_item_title)
	{
		this.menu_item[menu_index][this.menu_item_n[menu_index]] = 
		new JMenuItem(menu_item_title);
		
		this.menu[menu_index].add
		(this.menu_item[menu_index][this.menu_item_n[menu_index]]);//添加菜单项
		
		this.menu_item_n[menu_index]++;
	}
	
	public void AddMenuItems(int menu_index,String[] menu_item_title)
	{
		for(int i = 0;i < menu_item_title.length;i++)
		{
			this.AddMenuItem(menu_index, menu_item_title[i]);
		}
	}
	
	public void InsertSeparator(int menu_index,int index)
	{
		this.menu[menu_index].insertSeparator(index);
	}
	
	public void AddListener(int menu_index,int menu_item_index,
			Listener listener)
	{
		this.menu_item[menu_index][menu_item_index].addActionListener(listener);
	}
	
	public JMenuBar GetMenuBar()
	{
		return this.menu_bar;
	}
	
	public JMenu GetMenu(int menu_index)
	{
		return this.menu[menu_index];
	}
	
	public JMenuItem GetMenuItem(int menu_index,int menu_item_index)
	{
		return this.menu_item[menu_index][menu_item_index];
	}
}







