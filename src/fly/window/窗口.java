package fly.window;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class 窗口
{
	private JFrame window = new JFrame();
	
	public 窗口(String 标题){this(标题,800,600);}
	public 窗口(String 标题,int 宽度,int 高度)
	{
		this.window.setTitle(标题);										//设置窗口标题
		this.window.setSize(宽度, 高度);									//设置窗口宽高
		this.window.setLocationRelativeTo(null);						//设置窗口居中
		this.window.setLayout(new GridLayout());						//设置窗口布局
		this.window.setVisible(true);									//设置窗口为可视
		this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//设置窗口默认CloseOperation
	}
	
	
	/*设置窗口为可视*/
	public void 设置可视(boolean 参数){this.window.setVisible(参数);}
	/*设置窗口布局*/
	public void 设置布局(LayoutManager 管理器) {this.window.setLayout(管理器);}
	
	
	/*设置窗口图标*/
	public void 设置图标(String 路径) throws IOException
	{
		Image 图像 = ImageIO.read(this.window.getClass().getResource(路径));//读取路径图片
		this.window.setIconImage(图像);//设置图片为窗口图标
	}
	/*设置窗口图标*/
	public void 设置图标(URL input) throws IOException
	{
		Image 图像 = ImageIO.read(input);//读取图片
		this.window.setIconImage(图像);//设置图片为窗口图标
	}
	/*设置窗口图标*/
	public void 设置图标(Image 图像){this.window.setIconImage(图像);}
	
	
	/*设置菜单栏*/
	public void 设置菜单栏(MenuBar menu_bar)
	{this.window.setJMenuBar(menu_bar.GetMenuBar());this.window.setVisible(true);}
	
	/*设置Fly菜单栏*/
	public void 设置菜单栏(FlyMenuBar menu_bar)
	{this.window.setJMenuBar(menu_bar.GetMenuBar());this.window.setVisible(true);}
	
	/*设置菜单栏*/
	public void 设置菜单栏(菜单栏 menu_bar)
	{this.window.setJMenuBar(menu_bar.获取菜单栏());}
	
	/*获取JFrame*/
	public JFrame 获取窗口() {return window;}
	
	
	/*添加鼠标监听器*/
	public void 添加鼠标监听器(FlyMouseListener 监听器)
	{
		this.window.addMouseListener(监听器);
		this.window.addMouseMotionListener(监听器);
		this.window.addMouseWheelListener(监听器);
	}
	
	/*添加key监听器*/
	public void 添加key监听器(FlyKeyListener 监听器)
	{this.window.addKeyListener(监听器);}
	
	/*添加窗口监听器*/
	public void 添加窗口监听器(FlyWindowListener 监听器)
	{this.添加鼠标监听器(监听器);this.添加key监听器(监听器);this.window.addWindowListener(监听器);}
	
	/*添加组件*/
	public void 添加(Component comp){this.window.getContentPane().add(comp);}
	public void 添加(Widget widget){this.window.getContentPane().add(widget.Get());}
	public void 添加(组件 widget){this.window.getContentPane().add(widget.获取());}
}






