package fly.window;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class 菜单栏
{
	private JMenuBar menu_bar;
	private ArrayList<JMenu> menu;
	private ArrayList<JMenuItem> menu_item;
	
	public 菜单栏()
	{
		/*初始化*/
		menu_bar = new JMenuBar();
		menu = new ArrayList<JMenu>();
		menu_item = new ArrayList<JMenuItem>();
	}
	
	/*添加一个菜单*/
	public int 添加菜单(){return 添加菜单("");}
	public int 添加菜单(String 菜单文本) 
	{
		menu.add(new JMenu(菜单文本));//往菜单列表添加菜单
		
		menu_bar.add(menu.get(menu.size() - 1));//将菜单添加至菜单栏
		
		return menu.size() - 1;//返回菜单索引
	}
	
	/*添加多个菜单*/
	public int[] 添加菜单(int 数量)//返回菜单索引
	{String[] title = new String[数量];return this.添加菜单(title);}
	public int[] 添加菜单(String... 菜单文本)//返回菜单索引
	{
		int[] 菜单索引 = new int[菜单文本.length];//各个菜单的索引
		
		for(int i = 0;i < 菜单文本.length;i++)
		{
			菜单索引[i] = menu.size();//索引等于菜单数量 -1，先赋值在创建菜单，所以不用 -1
			this.添加菜单(菜单文本[i]);//添加一个菜单
		}
		
		return 菜单索引;//返回各个菜单索引
	}
	
	/*添加一个菜单项*/
	public int 添加菜单项(int 菜单索引){return this.添加菜单项(菜单索引, "");}
	public int 添加菜单项(int 菜单索引,String 菜单项文本)
	{
		menu_item.add(new JMenuItem(菜单项文本));//添加菜单项
		menu.get(菜单索引).add(menu_item.get(menu_item.size() - 1));//往菜单添加菜单项
		return menu_item.size() - 1;//返回菜单项索引,索引等于菜单项数量 -1
	}
	
	/*添加多个菜单项*/
	public int[] 添加菜单项(int 菜单索引,int 数量)// menu_index 为菜单索引   n 为菜单项数量
	{String[] 文本 = new String[数量];return 添加菜单项(菜单索引,文本);}
	public int[] 添加菜单项(int 菜单索引,String... 菜单项文本)
	{
		int[] 索引 = new int[菜单项文本.length];//各个菜单的索引
		
		for(int i = 0;i < 菜单项文本.length;i++)
		{
			索引[i] = menu_item.size();//索引等于菜单项数量 -1，先赋值在创建菜单，所以不用 -1
			this.添加菜单项(菜单索引,菜单项文本[i]);//添加一个菜单项
		}
		
		return 索引;//返回各个菜单项索引
	}
	
	/*菜单插入分割线*/
	public void 插入分割线(int 菜单索引,int 分割线索引)
	{menu.get(菜单索引).insertSeparator(分割线索引);}
	
	public void 添加监听器(int 菜单项索引,FlyActionAndChangeListener 监听器)
	{
		/*给菜单项添加ActionListener和ChangeListener*/
		menu_item.get(菜单项索引).addActionListener(监听器);
		menu_item.get(菜单项索引).addChangeListener(监听器);
	}
	
	
	public JMenuBar 获取菜单栏(){return menu_bar;}
	
	public JMenu 获取菜单(int 菜单索引){return menu.get(菜单索引);}
	public JMenu 获取菜单(String 菜单文本)//返回第一个找到的
	{
		int 索引 = 0;//菜单索引
		for(int i = 0;i < menu.size();i++)//遍历菜单文本
		{
			if(menu.get(i).getText() == 菜单文本)//若菜单文本是要找的文本，结束循环
			{
				索引 = i;//索引记录
				break;//结束循环
			}
		}
		return menu.get(索引);//返回索引
	}
	
	public JMenuItem 获取菜单项(int 菜单项索引){return menu_item.get(菜单项索引);}
	public JMenuItem 获取菜单项(String 菜单项文本)//返回第一个找到的
	{
		int 索引 = 0;//菜单项索引
		for(int i = 0;i < menu_item.size();i++)//遍历菜单项文本
		{
			if(menu_item.get(i).getText() == 菜单项文本)//若菜单项文本是要找的文本，结束循环
			{
				索引 = i;//索引记录
				break;//结束循环
			}
		}
		return menu_item.get(索引);//返回索引
	}
}
