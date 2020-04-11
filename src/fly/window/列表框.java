package fly.window;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class 列表框 extends 组件
{
	private JList<Object> list;
	private Vector<Object> item;
	
	public 列表框(){list = new JList<Object>();item = new Vector<Object>();}
	public 列表框(Object[] 列表数据){list = new JList<Object>(列表数据);}
	public 列表框(Vector<?> 列表数据){list = new JList<Object>(列表数据);}
	public 列表框(ListModel<Object> 列表模型){list = new JList<Object>(列表模型);}
	
	@Override public Component 获取() {return list;}
	
	/*添加列表项*/
	public int 添加列表项(Object 物体)//返回项索引
	{
		item.add(物体);
		list = new JList<Object>(item);
		return item.size() - 1;
	}
	/*添加列表项*/
	public int[] 添加列表项(Object[] 物体)//返回项索引
	{
		int index[] = new int[物体.length];
		for(int i = 0;i < 物体.length;i++)
		{
			index[i] = item.size();
			item.add(物体[i]);
		}
		list = new JList<Object>(item);
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
}
