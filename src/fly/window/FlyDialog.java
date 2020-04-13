package fly.window;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

import fly.window.listener.FlyKeyListener;
import fly.window.listener.FlyMouseListener;
import fly.window.listener.FlyWindowListener;
import fly.window.widget.FlyWidget;
import fly.window.widget.组件;

/**
 * @file FlyDialog.java
 * @author Fly
 */


public class FlyDialog
{
	private JDialog dialog;///<

	/**初始化*/
	public FlyDialog()
	{
		dialog = new JDialog();
		dialog.setLocationRelativeTo(null);							//设置窗口居中
		dialog.setLayout(new GridLayout());							//设置窗口布局
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//设置窗口默认CloseOperation
	}
	public FlyDialog(String title){this();dialog.setTitle(title);}
	public FlyDialog(String title,int width,int height){this(title);dialog.setSize(width,height);}

	/*初始化*/
	public FlyDialog(Window window)
	{
		dialog = new JDialog(window.GetFrame());
		dialog.setLocationRelativeTo(null);							//设置窗口居中
		dialog.setLayout(new GridLayout());							//设置窗口布局
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//设置窗口默认CloseOperation
	}
	public FlyDialog(Window window,String title){this(window);dialog.setTitle(title);}
	public FlyDialog(Window window,String title,int width,int height)
	{
		this(window,title);
		dialog.setSize(width, height);
	}

	/*初始化*/
	public FlyDialog(窗口 window)
	{
		dialog = new JDialog(window.获取窗口());
		dialog.setLocationRelativeTo(null);							//设置窗口居中
		dialog.setLayout(new GridLayout());							//设置窗口布局
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//设置窗口默认CloseOperation
	}
	public FlyDialog(窗口 window,String title){this(window);dialog.setTitle(title);}
	public FlyDialog(窗口 window,String title,int width,int height)
	{
		this(window,title);
		dialog.setSize(width, height);
	}

	/**
	 * 设置窗口可视
	 *
	 * @param b b若为真，会让这个Dialog可视
	 * */
	public void SetVisible(boolean b){this.dialog.setVisible(b);}

	/**
	 * 设置窗口布局
	 *
	 * @param manager 要设置的布局
	 * */
	public void SetLayout(LayoutManager manager) {this.dialog.setLayout(manager);}

	/**
	 * 获取JDialog
	 *
	 * @return 返回JDialog
	 * */
	public JDialog GetDialog(){return this.dialog;}


	/**
	 * 设置窗口图标
	 *
	 * @param path 图标路径
	 * */
	public void SetIcon(String path) throws IOException
	{
		Image img = ImageIO.read(this.dialog.getClass().getResource(path));//读取路径图片
		this.dialog.setIconImage(img);//设置图片为窗口图标
	}

	/**
	 * 设置窗口图标
	 *
	 * @param input
	 * */
	public void SetIcon(URL input) throws IOException
	{
		Image img = ImageIO.read(input);//读取图片
		this.dialog.setIconImage(img);//设置图片为窗口图标
	}

	/**
	 * 设置窗口图标
	 *
	 * @param img 图标图像
	 * */
	public void SetIcon(Image img){this.dialog.setIconImage(img);}


	/**
	 * 设置菜单栏
	 *
	 * @param menu_bar 要设置的菜单栏
	 * */
	public void SetMenuBar(MenuBar menu_bar)
	{this.dialog.setJMenuBar(menu_bar.GetMenuBar());}

	/**
	 * 设置Fly菜单栏
	 *
	 * @param menu_bar 要设置的菜单栏
	 * */
	public void SetMenuBar(FlyMenuBar menu_bar)
	{this.dialog.setJMenuBar(menu_bar.GetMenuBar());}

	/**
	 * 设置菜单栏
	 *
	 * @param menu_bar 要设置的菜单栏
	 * */
	public void SetMenuBar(菜单栏 menu_bar)
	{this.dialog.setJMenuBar(menu_bar.获取菜单栏());}


	/**
	 * 添加鼠标监听器
	 *
	 * @param listener 要设置的监听器
	 * */
	public void AddMouseListener(FlyMouseListener listener)
	{
		this.dialog.addMouseListener(listener);
		this.dialog.addMouseMotionListener(listener);
		this.dialog.addMouseWheelListener(listener);
	}

	/**
	 * 添加key监听器
	 *
	 * @param listener 要设置的监听器
	 * */
	public void AddKeyListener(FlyKeyListener listener)
	{this.dialog.addKeyListener(listener);}

	/**
	 * 添加窗口监听器
	 *
	 * @param listener 要设置的监听器
	 * */
	public void AddWindowListener(FlyWindowListener listener)
	{this.AddMouseListener(listener);this.AddKeyListener(listener);this.dialog.addWindowListener(listener);}


	/**
	 * 给Dialog添加组件
	 *
	 * @param comp 要添加的组件
	 * */
	public void Add(Component comp){this.dialog.getContentPane().add(comp);}

	/**
	 * 给Dialog添加组件
	 *
	 * @param widget 要添加的组件
	 * */
	public void Add(FlyWidget widget){this.dialog.getContentPane().add(widget.Get());}

	/**
	 * 给Dialog添加组件
	 *
	 * @param widget 要添加的组件
	 * */
	public void Add(组件 widget){this.dialog.getContentPane().add(widget.获取());}

}











