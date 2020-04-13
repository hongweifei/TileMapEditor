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


/**
 * @file FlyList.java
 * @author Fly
 */


/**
 * 列表框类
 * */
public class FlyList<T> extends FlyWidget
{
	private JList<T> list;
	private Vector<T> item;

	public FlyList(){list = new JList<T>();item = new Vector<T>();}
	public FlyList(T[] list_data){list = new JList<T>(list_data);}
	public FlyList(Vector<T> list_data){list = new JList<T>(list_data);}
	public FlyList(ListModel<T> list_model){list = new JList<T>(list_model);}

	/**获取列表框*/
	@Override public JList<T> Get() {return list;}

	/**
	 * 添加列表项
	 *
	 * @return 返回项索引
	 * */
	public int AddItem(T T)
	{
		item.add(T);
		list = new JList<T>(item);
		return item.size() - 1;
	}
	/**
	 * 添加列表项
	 *
	 * @return 返回项索引
	 * */
	public int[] AddItems(T... T)
	{
		int index[] = new int[T.length];
		for(int i = 0;i < T.length;i++)
		{
			index[i] = item.size();
			item.add(T[i]);
		}
		list = new JList<T>(item);
		return index;
	}


	/**
	 * 移除列表项
	 *
	 * @param index 要移除的列表项的索引，索引从0开始
	 * */
	public void RemoveItem(int index){list.remove(index);}

	/**移除所有列表项*/
	public void RemoveAllItem() {list.removeAll();}


	/**
	 * 添加鼠标监听器
	 *
	 * @param listener 监听器
	 * */
	public void AddMouseListener(FlyMouseListener listener)
	{
		list.addMouseListener(listener);
		list.addMouseMotionListener(listener);
		list.addMouseWheelListener(listener);
	}
	/**
	 * 添加key监听器
	 *
	 * @param listener 监听器
	 * */
	public void AddKeyListener(FlyKeyListener listener){list.addKeyListener(listener);}

	/**
	 * 添加鼠标监听器
	 *
	 * @param listener 监听器
	 * */
	public void AddMouseListener(MouseListener listener){list.addMouseListener(listener);}

	/**
	 * 添加鼠标移动监听器
	 *
	 * @param listener 监听器
	 * */
	public void AddMouseMotionListener(MouseMotionListener listener){list.addMouseMotionListener(listener);}

	/**
	 * 添加鼠标Wheel监听器
	 *
	 * @param listener 监听器
	 * */
	public void AddMouseWheelListener(MouseWheelListener listener){list.addMouseWheelListener(listener);}

	/**
	 * 添加key监听器
	 *
	 * @param listener 监听器
	 * */
	public void AddKeyListener(KeyListener listener){list.addKeyListener(listener);}
}










