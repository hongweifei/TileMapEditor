package fly.window;

import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.text.Document;

import fly.window.listener.FlyKeyListener;
import fly.window.listener.FlyMouseListener;


/*String转int
 * (1)Integer.parseInt(str)
 * (2)Integer.valueOf(str).intValue()
 * */

/*int转String
 * (1)String.valueOf(num)
 * (2)Integer.toString(num)
 * */


public class FlyTextFieldManager
{
	private ArrayList<JTextField> text_field;
	
	public FlyTextFieldManager(){text_field = new ArrayList<JTextField>();}
	
	
	/*获取文本编辑框*/
	public JTextField Get(int index) {return this.text_field.get(index);}
	
	
	/*为一个文本编辑框设置鼠标监听器*/
	public void SetMouseListener(FlyMouseListener listener,int index)
	{
		this.text_field.get(index).addMouseListener(listener);
		this.text_field.get(index).addMouseMotionListener(listener);
		this.text_field.get(index).addMouseWheelListener(listener);
	}
	
	/*为所以文本编辑框设置鼠标监听器*/
	public void SetMouseListener(FlyMouseListener listener)
	{
		for(int i = 0;i < this.text_field.size();i++)
		{
			this.text_field.get(i).addMouseListener(listener);
			this.text_field.get(i).addMouseMotionListener(listener);
			this.text_field.get(i).addMouseWheelListener(listener);
		}
	}
	
	/*为一个文本编辑框设置key监听器*/
	public void SetKeyListener(FlyKeyListener listener,int index)
	{
		this.text_field.get(index).addKeyListener(listener);
	}
	
	/*为一个文本编辑框设置key监听器*/
	public void SetKeyListener(FlyKeyListener listener)
	{
		for(int i = 0;i < this.text_field.size();i++)
			this.text_field.get(i).addKeyListener(listener);
	}
	
	
	
	/*添加一个文本编辑框*/
	public int AddTextField(){text_field.add(new JTextField());return text_field.size() - 1;}
	
	/*添加一个带文本的文本编辑框*/
	public int AddTextField(String text){text_field.add(new JTextField(text));return text_field.size() - 1;}
	
	/*添加一个具有指定列数的文本编辑框*/
	public int AddTextField(int columns){text_field.add(new JTextField(columns));return text_field.size() - 1;}
	
	/*添加一个带文本且具有指定列数的的文本编辑框*/
	public int AddTextField(String text, int columns)
	{text_field.add(new JTextField(text,columns));return text_field.size() - 1;}
	
	/*添加一个文本编辑框*/
	public int AddTextField(Document doc, String text, int columns)
	{text_field.add(new JTextField(doc,text, columns));return text_field.size() - 1;}
	
	/*添加 n 个文本编辑框*/
	public int[] AddTextFields(int n)
	{
		int[] index = new int[n];
		for(int i = 0;i < n;i++)
		{
			index[i] = text_field.size();
			text_field.add(new JTextField());
		}
			
		return index;
	}
	
	/*添加 n 个文本编辑框*/
	public int[] AddTextFields(String... text)
	{
		int[] index = new int[text.length];
		for(int i = 0;i < text.length;i++)
		{
			index[i] = text_field.size();
			text_field.add(new JTextField(text[i]));
		}
		
		return index;
	}
	
	
	public void SetText(String text,int index){text_field.get(index).setText(text);}//设置文本编辑框文本
	public String GetText(int index){return text_field.get(index).getText();}//获取文本编辑框文本
	
	/*将文本编辑框管理器添加到窗口*/
	public void AddToWindow(Window window,int index){window.Add(text_field.get(index));}
	/*将文本编辑框管理器添加到窗口*/
	public void AddToWindow(Window window)
	{
		for(int i = 0;i < text_field.size();i++)
			window.Add(text_field.get(i));
	}
	
	/*将文本编辑框管理器添加到窗口*/
	public void AddToWindow(窗口 window,int index){window.添加(text_field.get(index));}
	/*将文本编辑框管理器添加到窗口*/
	public void AddToWindow(窗口 window)
	{
		for(int i = 0;i < text_field.size();i++)
			window.添加(text_field.get(i));
	}
	
	/*将文本编辑框管理器添加到窗口*/
	public void AddToWindow(FlyDialog window,int index){window.Add(text_field.get(index));}
	/*将文本编辑框管理器添加到窗口*/
	public void AddToWindow(FlyDialog window)
	{
		for(int i = 0;i < text_field.size();i++)
			window.Add(text_field.get(i));
	}
}






