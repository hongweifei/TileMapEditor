package fly.window.widget;


import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

import fly.window.listener.FlyKeyListener;
import fly.window.listener.FlyMouseListener;

public class 列表框<T> extends 组件
{
	private JList<T> list;
	private Vector<T> item;
	
	public 列表框(){list = new JList<T>();item = new Vector<T>();}
	public 列表框(T[] 列表数据){list = new JList<T>(列表数据);}
	public 列表框(Vector<T> 列表数据){list = new JList<T>(列表数据);}
	public 列表框(ListModel<T> 列表模型){list = new JList<T>(列表模型);}
	
	@Override public JList<T> 获取() {return list;}
	
	/*添加列表项*/
	public int 添加列表项(T 物体)//返回项索引
	{
		item.add(物体);
		list = new JList<T>(item);
		return item.size() - 1;
	}
	/*添加列表项*/
	public int[] 添加列表项(T... 物体)//返回项索引
	{
		int index[] = new int[物体.length];
		for(int i = 0;i < 物体.length;i++)
		{
			index[i] = item.size();
			item.add(物体[i]);
		}
		list = new JList<T>(item);
		return index;
	}
	
	
	/*移除列表项*/
	public void 移除列表项(int 索引){list.remove(索引);}
	public void 清空列表项() {list.removeAll();}
	
	
	/*添加鼠标监听器*/
	public void 添加鼠标监听器(FlyMouseListener 监听器)
	{
		list.addMouseListener(监听器);
		list.addMouseMotionListener(监听器);
		list.addMouseWheelListener(监听器);
	}
	/*添加key监听器*/
	public void 添加key监听器(FlyKeyListener 监听器){list.addKeyListener(监听器);}
	
	/*添加鼠标监听器*/
	public void 添加鼠标监听器(MouseListener listener){list.addMouseListener(listener);}
	public void 添加鼠标监听器(MouseMotionListener listener){list.addMouseMotionListener(listener);}
	public void 添加鼠标监听器(MouseWheelListener listener){list.addMouseWheelListener(listener);}
	/*添加key监听器*/
	public void 添加按键监听器(KeyListener listener){list.addKeyListener(listener);}
}










