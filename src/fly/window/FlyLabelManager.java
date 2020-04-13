package fly.window;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fly.window.listener.FlyKeyListener;
import fly.window.listener.FlyMouseListener;


/**
 * @file FlyLabelManager.java
 * @author Fly
 */


/*String转int
 * (1)Integer.parseInt(str)
 * (2)Integer.valueOf(str).intValue()
 * */

/*int转String
 * (1)String.valueOf(num)
 * (2)Integer.toString(num)
 * */



public class FlyLabelManager
{
	private ArrayList<JLabel> label;///<

	public static final int CENTER = SwingConstants.CENTER;
	public static final int TOP = SwingConstants.TOP;
	public static final int LEFT = SwingConstants.LEFT;
	public static final int BOTTOM = SwingConstants.BOTTOM;
	public static final int RIGHT = SwingConstants.RIGHT;

	public static final int HORIZONTAL = SwingConstants.HORIZONTAL;
	public static final int VERTICAL = SwingConstants.VERTICAL;

	public FlyLabelManager(){label = new ArrayList<JLabel>();}


	/**
	 * 为一个标签设置鼠标监听器
	 *
	 *
	 * */
	public void SetMouseListener(FlyMouseListener listener,int index)
	{
		this.label.get(index).addMouseListener(listener);
		this.label.get(index).addMouseMotionListener(listener);
		this.label.get(index).addMouseWheelListener(listener);
	}

	/**
	 * 为所以标签设置鼠标监听器
	 *
	 *
	 * */
	public void SetMouseListener(FlyMouseListener listener)
	{
		for(int i = 0;i < this.label.size();i++)
		{
			this.label.get(i).addMouseListener(listener);
			this.label.get(i).addMouseMotionListener(listener);
			this.label.get(i).addMouseWheelListener(listener);
		}
	}

	/**
	 * 为一个标签设置key监听器
	 *
	 *
	 * */
	public void SetKeyListener(FlyKeyListener listener,int index)
	{
		this.label.get(index).addKeyListener(listener);
	}

	/**
	 * 为一个标签设置key监听器
	 *
	 *
	 * */
	public void SetKeyListener(FlyKeyListener listener)
	{
		for(int i = 0;i < this.label.size();i++)
			this.label.get(i).addKeyListener(listener);
	}



	/**
	 * 添加一个标签
	 *
	 * @return 返回标签索引
	 * */
	public int AddLabel(){label.add(new JLabel());return label.size() - 1;}

	/**
	 * 添加一个标签，需设置文本
	 *
	 * @return 返回标签索引
	 * */
	public int AddLabel(String text){label.add(new JLabel(text));return label.size() - 1;}

	/**
	 * 添加一个标签，需设置图标
	 *
	 * @return 返回标签索引
	 * */
	public int AddLabel(Icon image){label.add(new JLabel(image));return label.size() - 1;}

	/**
	 * 添加一个标签，需设置文本和对齐
	 *
	 * @return 返回标签索引
	 * */
	public int AddLabel(String text, int horizontal_alignment)
	{label.add(new JLabel(text,horizontal_alignment));return label.size() - 1;}

	/**
	 * 添加一个标签，需设置图标和对齐
	 *
	 * @return 返回标签索引
	 * */
	public int AddLabel(Icon image, int horizontal_alignment)
	{label.add(new JLabel(image,horizontal_alignment));return label.size() - 1;}

	/**
	 * 添加一个标签，需设置图标，文本和对齐
	 *
	 * @return 返回标签索引
	 * */
	public int AddLabel(String text, Icon icon, int horizontal_alignment)
	{label.add(new JLabel(text,icon,horizontal_alignment));return label.size() - 1;}

	/**
	 * 获取单个标签
	 *
	 * @param index 标签索引
	 *
	 * @return 返回标签
	 * */
	public JLabel GetLabel(int index){return label.get(index);}

	/**
	 * 获取所有标签
	 *
	 * @return 返回所有标签
	 * */
	public JLabel[] GetLabels()
	{
		JLabel[] labels = new JLabel[label.size()];
		for(int i = 0;i < label.size();i++)
			labels[i] = label.get(i);
		return labels;
	}


	/**
	 * 添加  n 个标签
	 *
	 * @return 返回  n 个标签索引
	 * */
	public int[] AddLabels(int n)
	{
		int[] index = new int[n];
		for(int i = 0;i < n;i++)
		{
			index[i] = label.size();
			label.add(new JLabel());
		}
		return index;
	}

	/**
	 * 添加  n 个带文本的标签
	 *
	 * @return 返回  n 个标签索引
	 * */
	public int[] AddLabels(String... text)
	{
		int[] index = new int[text.length];
		for(int i = 0;i < text.length;i++)
		{
			index[i] = label.size();
			label.add(new JLabel(text[i]));
		}
		return index;
	}

	/**
	 * 设置指定标签的文本
	 *
	 * @param text 要设置的文本
	 * @param index 标签索引
	 * */
	public void SetText(String text,int index){label.get(index).setText(text);}

	/**
	 * 获取指定标签的文本
	 *
	 * @param index 标签索引
	 *
	 * @return 返回标签文本
	 * */
	public String GetText(int index){return label.get(index).getText();}

	/**
	 * 将标签管理器中的一个标签添加到窗口
	 *
	 * @param window 要添加标签的窗口
	 * @param index 标签索引
	 * */
	public void AddToWindow(Window window,int index){window.Add(label.get(index));}

	/**
	 * 将标签管理器中的所有标签添加到窗口
	 *
	 * @param window 要添加标签的窗口
	 * */
	public void AddToWindow(Window window)
	{
		for(int i = 0;i < label.size();i++)
			window.Add(label.get(i));
	}

	/**
	 * 将标签管理器中的一个标签添加到窗口
	 *
	 * @param window 要添加标签的窗口
	 * @param index 标签索引
	 * */
	public void AddToWindow(窗口 window,int index){window.添加(label.get(index));}

	/**
	 * 将标签管理器中的所有标签添加到窗口
	 *
	 * @param window 要添加标签的窗口
	 * */
	public void AddToWindow(窗口 window)
	{
		for(int i = 0;i < label.size();i++)
			window.添加(label.get(i));
	}

	/**
	 * 将标签管理器中的一个标签添加到窗口
	 *
	 * @param window 要添加标签的窗口
	 * @param index 标签索引
	 * */
	public void AddToWindow(FlyDialog window,int index){window.Add(label.get(index));}

	/**
	 * 将标签管理器中的所有标签添加到窗口
	 *
	 * @param window 要添加标签的窗口
	 * */
	public void AddToWindow(FlyDialog window)
	{
		for(int i = 0;i < label.size();i++)
			window.Add(label.get(i));
	}

}











