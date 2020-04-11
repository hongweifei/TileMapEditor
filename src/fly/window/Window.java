package fly.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window
{
	private JFrame window = new JFrame();
	
	public Window(String title){this(title,800,600);}
	public Window(String title,int width,int height)
	{
		this.window.setTitle(title);								//设置窗口标题
		this.window.setSize(width, height);							//设置窗口宽高
		this.window.setLocationRelativeTo(null);					//设置窗口居中
		this.window.setLayout(new BorderLayout());					//设置窗口布局
		this.window.setVisible(true);								//设置窗口为可视
		this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//设置窗口默认CloseOperation
	}
	
	
	/*设置窗口为可视*/
	public void SetVisble(boolean arg){this.window.setVisible(arg);}
	/*设置窗口布局*/
	public void SetLayout(LayoutManager manager) {this.window.setLayout(manager);}
	
	
	/*设置窗口图标*/
	public void SetIcon(String path) throws IOException
	{
		Image img = ImageIO.read(this.window.getClass().getResource(path));//读取路径图片
		this.window.setIconImage(img);//设置图片为窗口图标
	}
	/*设置窗口图标*/
	public void SetIcon(URL input) throws IOException
	{
		Image img = ImageIO.read(input);//读取图片
		this.window.setIconImage(img);//设置图片为窗口图标
	}
	/*设置窗口图标*/
	public void SetIcon(Image img){this.window.setIconImage(img);}
	
	
	/*设置菜单栏*/
	public void SetMenuBar(MenuBar menu_bar)
	{this.window.setJMenuBar(menu_bar.GetMenuBar());this.window.setVisible(true);}
	
	/*设置Fly菜单栏*/
	public void SetMenuBar(FlyMenuBar menu_bar)
	{this.window.setJMenuBar(menu_bar.GetMenuBar());this.window.setVisible(true);}
	
	
	/*获取窗口*/
	public JFrame GetFrame() {return window;}
	
	
	/*添加鼠标监听器*/
	public void AddMouseListener(FlyMouseListener listener)
	{
		this.window.addMouseListener(listener);
		this.window.addMouseMotionListener(listener);
		this.window.addMouseWheelListener(listener);
	}
	
	/*添加key监听器*/
	public void AddKeyListener(FlyKeyListener listener)
	{this.window.addKeyListener(listener);}
	
	/*添加窗口监听器*/
	public void AddWindowListener(FlyWindowListener listener)
	{this.AddMouseListener(listener);this.AddKeyListener(listener);this.window.addWindowListener(listener);}
	
	/*window.Add*/
	public void Add(Component comp){this.window.getContentPane().add(comp);}
	
}



