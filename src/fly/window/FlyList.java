package fly.window;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class FlyList
{
	private JList list;
	private Vector item;
	
	public FlyList(){list = new JList();}
	public FlyList(Object[] list_data){list = new JList(list_data);}
	public FlyList(Vector list_data){list = new JList(list_data);}
	public FlyList(ListModel list_model){list = new JList(list_model);}
	
	public int AddItem(Object object)//返回项索引
	{item.add(object);list = new JList(item);return item.size() - 1;}
	public int[] AddItems(Object[] object)//返回项索引
	{
		int index[] = new int[object.length];
		for(int i = 0;i < object.length;i++)
		{
			index[i] = item.size();
			item.add(object[i]);
		}
		list = new JList(item);
		return index;
	}
}
