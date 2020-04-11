package fly.window;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class FlyList extends Widget
{
	private JList<Object> list;
	private Vector<Object> item;
	
	public FlyList(){list = new JList<Object>();item = new Vector<Object>();}
	public FlyList(Object[] list_data){list = new JList<Object>(list_data);}
	public FlyList(Vector<?> list_data){list = new JList<Object>(list_data);}
	public FlyList(ListModel<Object> list_model){list = new JList<Object>(list_model);}
	
	@Override public Component Get() {return list;}
	
	/*添加列表项*/
	public int AddItem(Object object)//返回项索引
	{
		item.add(object);
		list = new JList<Object>(item);
		return item.size() - 1;
	}
	/*添加列表项*/
	public int[] AddItems(Object[] object)//返回项索引
	{
		int index[] = new int[object.length];
		for(int i = 0;i < object.length;i++)
		{
			index[i] = item.size();
			item.add(object[i]);
		}
		list = new JList<Object>(item);
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
}










