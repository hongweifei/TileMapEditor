package fly.window;


import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class FlyList<T> extends Widget
{
	private JList<T> list;
	private Vector<T> item;
	
	public FlyList(){list = new JList<T>();item = new Vector<T>();}
	public FlyList(T[] list_data){list = new JList<T>(list_data);}
	public FlyList(Vector<T> list_data){list = new JList<T>(list_data);}
	public FlyList(ListModel<T> list_model){list = new JList<T>(list_model);}
	
	@Override public JList<T> Get() {return list;}
	
	/*添加列表项*/
	public int AddItem(T T)//返回项索引
	{
		item.add(T);
		list = new JList<T>(item);
		return item.size() - 1;
	}
	/*添加列表项*/
	public int[] AddItems(T... T)//返回项索引
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
	
	
	/*移除列表项*/
	public void RemoveItem(int index){list.remove(index);}
	public void RemoveAllItem() {list.removeAll();}
	
	
	/*添加鼠标监听器*/
	public void AddMouseListener(FlyMouseListener listener)
	{
		list.addMouseListener(listener);
		list.addMouseMotionListener(listener);
		list.addMouseWheelListener(listener);
	}
	/*添加key监听器*/
	public void AddKeyListener(FlyKeyListener listener){list.addKeyListener(listener);}
	
	/*添加鼠标监听器*/
	public void AddMouseListener(MouseListener listener){list.addMouseListener(listener);}
	public void AddMouseMotionListener(MouseMotionListener listener){list.addMouseMotionListener(listener);}
	public void AddMouseWheelListener(MouseWheelListener listener){list.addMouseWheelListener(listener);}
	/*添加key监听器*/
	public void AddKeyListener(KeyListener listener){list.addKeyListener(listener);}
}










