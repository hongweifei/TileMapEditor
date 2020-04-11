package fly.window;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	
	/*添加一个菜单*/
	
	public int AddMenu(){return AddMenu("");}
	
	public int AddMenu(String menu_title) 
	{
		menu.add(new JMenu(menu_title));//往菜单列表添加菜单
		
		menu_bar.add(menu.get(menu.size() - 1));//将菜单添加至菜单栏
		
		return menu.size() - 1;//返回菜单索引
	}
	
	/*添加多个菜单*/
	public int[] AddMenus(int n)//返回菜单索引
	{
		String[] title = new String[n];
		return this.AddMenus(title);
	}
	
	public int[] AddMenus(String[] menu_title)//返回菜单索引
	{
		int[] menu_index = new int[menu_title.length];//各个菜单的索引
		
		for(int i = 0;i < menu_title.length;i++)
		{
			menu_index[i] = menu.size();//索引等于菜单数量 -1，先赋值在创建菜单，所以不用 -1
			this.AddMenu(menu_title[i]);//添加一个菜单
		}
		
		return menu_index;//返回各个菜单索引
	}
	
	/*添加一个菜单项*/
	public int AddMenuItem(int menu_index){return this.AddMenuItem(menu_index, "");}
	
	public int AddMenuItem(int menu_index,String menu_item_title)
	{
		menu_item.add(new JMenuItem(menu_item_title));//添加菜单项
		menu.get(menu_index).add(menu_item.get(menu_item.size() - 1));//往菜单添加菜单项
		return menu_item.size() - 1;//返回菜单项索引,索引等于菜单项数量 -1
	}
	
	/*添加多个菜单项*/
	public int[] AddMenuItems(int menu_index,int n)// menu_index 为菜单索引   n 为菜单项数量
	{
		String[] title = new String[n];
		return AddMenuItems(menu_index,title);
	}
	
	public int[] AddMenuItems(int menu_index,String[] menu_item_title)
	{
		int[] index = new int[menu_item_title.length];//各个菜单的索引
		
		for(int i = 0;i < menu_item_title.length;i++)
		{
			index[i] = menu_item.size();//索引等于菜单项数量 -1，先赋值在创建菜单，所以不用 -1
			this.AddMenuItem(menu_index,menu_item_title[i]);//添加一个菜单项
		}
		
		return index;//返回各个菜单项索引
	}
	
	/*菜单插入分割线*/
	public void MenuInsertSeparator(int menu_index,int index)
	{menu.get(menu_index).insertSeparator(index);}
	
	public void MenuItemAddListener(int menu_item_index,Listener listener)
	{
		/*给菜单项添加ActionListener和ChangeListener*/
		menu_item.get(menu_item_index).addActionListener(listener);
		menu_item.get(menu_item_index).addChangeListener(listener);
	}
	
	public JMenuBar GetMenuBar(){return menu_bar;}
	public JMenu GetMenu(int menu_index){return menu.get(menu_index);}
	public JMenuItem GetMenuItem(int menu_item_index){return menu_item.get(menu_item_index);}
}









