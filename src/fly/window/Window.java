package fly.window;

import java.awt.BorderLayout;
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
	public void SetLayout(LayoutManager manager) {this.window.setLayout(manager);}
	
	/*设置窗口图标*/
	public void SetIcon(String path) throws IOException
	{
		Image img = ImageIO.read(this.window.getClass().getResource(path));//读取路径图片
		this.window.setIconImage(img);//设置图片为窗口图标
	}
	public void SetIcon(URL input) throws IOException
	{
		Image img = ImageIO.read(input);//读取图片
		this.window.setIconImage(img);//设置图片为窗口图标
	}
	public void SetIcon(Image img){this.window.setIconImage(img);}
	
	/*获取窗口*/
	public JFrame GetFrame() {return window;}
	
	/*添加菜单栏*/
	public void AddMenuBar(MenuBar menu_bar)
	{this.window.setJMenuBar(menu_bar.GetMenuBar());this.window.setVisible(true);}
	
	/*添加Fly菜单栏*/
	public void AddMenuBar(FlyMenuBar menu_bar)
	{this.window.setJMenuBar(menu_bar.GetMenuBar());this.window.setVisible(true);}
}



