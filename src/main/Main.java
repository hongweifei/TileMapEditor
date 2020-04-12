package main;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;

import fly.graphics.TileMap;
import fly.graphics.事件;
import fly.graphics.场景;
import fly.graphics.渲染器;
import fly.window.FlyActionAndChangeListener;
import fly.window.FlyDialog;
import fly.window.FlyLabelManager;
import fly.window.FlyPopupMenu;
import fly.window.FlyTextFieldManager;
import fly.window.列表框;
import fly.window.文件选择器;
import fly.window.窗口;
import fly.window.菜单栏;

/*Java无宏定义，不可全中文*/

public class Main
{
	static 窗口 主窗口;
	static 菜单栏 菜单栏1;
	
	static FlyDialog 地图属性设置窗口;
	static FlyLabelManager label;
	static FlyTextFieldManager text_field;
	
	static TileMap map = null;
	static Image[] img = null;
	static String TileMapPath = null;
	
	static 列表框<String> 列表框1 = new 列表框<String>();
	static FlyPopupMenu popup_menu = new FlyPopupMenu();
	
	public static TileMap OpenTileMap(String path)
	{
		TileMap map = null;
		try{map = TileMap.ReadMap(path);}
		catch (IOException e) {e.printStackTrace();}
		
		for(int i = 0;i < map.height;i++)
		{
			for(int j = 0;j < map.width;j++)
			{
				System.out.print(map.data[j + i * map.width]);
			}
			System.out.println();
		}
		
		img = new Image[map.tile_count];
		for(int i = 0;i < map.tile_count;i++)
		{
			System.out.println(map.tile_image_path[i]);
			try {img[i] = ImageIO.read(new File(map.tile_image_path[i]));}
			catch (IOException e) {e.printStackTrace();}
		}
		
		列表框1.清空列表项();
		列表框1.添加列表项(map.tile_image_path);
		主窗口.添加(列表框1);
		主窗口.设置可视(true);
		
		return map;
	}
	
	
	public static void main(String[] args) throws MalformedURLException, IOException 
	{
		主窗口 = new 窗口("Tile Map Editor");
		//主窗口.设置布局();
		
		地图属性设置窗口 = new FlyDialog(主窗口,"地图属性设置",250,100);
		地图属性设置窗口.SetLayout(new GridLayout(0,4));
		
		label = new FlyLabelManager();
		label.AddLabels("地图宽度：","地图高度：","瓦片宽度：","瓦片高度：");
		
		text_field = new FlyTextFieldManager();
		text_field.AddTextFields("0","0","0","0");
		
		label.AddToWindow(地图属性设置窗口,0);
		text_field.AddToWindow(地图属性设置窗口,0);
		label.AddToWindow(地图属性设置窗口,1);
		text_field.AddToWindow(地图属性设置窗口,1);
		label.AddToWindow(地图属性设置窗口,2);
		text_field.AddToWindow(地图属性设置窗口,2);
		label.AddToWindow(地图属性设置窗口,3);
		text_field.AddToWindow(地图属性设置窗口,3);
		
		JButton create_map_button = new JButton("确定");
		地图属性设置窗口.Add(create_map_button);
		
		create_map_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				TileMap new_map = new TileMap(Integer.parseInt(text_field.GetText(0)),
						Integer.parseInt(text_field.GetText(1)),
						Integer.parseInt(text_field.GetText(2)),
						Integer.parseInt(text_field.GetText(3)),0,null,null);
				
				TileMapPath += ".map";
				
				try {TileMap.WriteMap(TileMapPath, new_map);} 
				catch (IOException e1){e1.printStackTrace();}
				
				map = Main.OpenTileMap(TileMapPath);
				
				地图属性设置窗口.SetVisible(false);
			}
			
		});
		
		菜单栏1 = new 菜单栏();
		菜单栏1.添加菜单("文件");
		
		菜单栏1.添加菜单项(0, "新建","打开","保存","另存为","关闭");
		菜单栏1.插入分割线(0, 2);
		菜单栏1.插入分割线(0, 5);
		
		popup_menu.AddItem("导入图片");
		popup_menu.AddItem("移除图片");
		
		/*导入图片*/
		popup_menu.AddActionListener(0, new ActionListener(){

			@Override public void actionPerformed(ActionEvent e)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				文件选择器1.设置文件过滤器("图像文件", "png","bmp","jpg","jepg");
				if(文件选择器1.弹出对话框("确定") == 文件选择器.确定选项)
				{
					map.tile_count++;
					
					String[] image_path = map.tile_image_path;
					map.tile_image_path = new String[map.tile_count];
					
					System.arraycopy(image_path,0,map.tile_image_path,0,image_path.length);
					
					map.tile_image_path[map.tile_count - 1] = 文件选择器1.获取选中文件的路径();
					
					列表框1.添加列表项(map.tile_image_path[map.tile_count - 1]);
					主窗口.设置可视(true);
				}
			}
		});
		
		
		主窗口.设置菜单栏(菜单栏1);
		
		场景 场景1 = new 场景(主窗口);
		场景1.设置鼠标监听器(new MouseListener(){

			@Override public void mouseClicked(MouseEvent e){}

			@Override public void mousePressed(MouseEvent e)
			{
				if(map != null && e.getButton() == MouseEvent.BUTTON1)
				{
					for(int i = 0;i < map.height;i++)
					{
						for(int j = 0;j < map.width;j++)
						{
							int n = j + i * map.width;
							
							if(Main.Collision(e.getX(), e.getY(), 
									1, 1, 
									j * map.tile_width, i * map.tile_height, 
									map.tile_width, map.tile_height))
							{
								if(map.data[n] < map.tile_count)
									map.data[n] += 1;
							}
						}
					}
				}
				else if(map != null && e.getButton() == MouseEvent.BUTTON3)
				{
					for(int i = 0;i < map.height;i++)
					{
						for(int j = 0;j < map.width;j++)
						{
							int n = j + i * map.width;
							
							if(Main.Collision(e.getX(), e.getY(), 
									1, 1, 
									j * map.tile_width, i * map.tile_height, 
									map.tile_width, map.tile_height))
							{
								map.data[n] = 0;
							}
						}
					}
				}
			}
			@Override public void mouseReleased(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
		});
		
		//主窗口.添加(列表框1);
		
		/*新建地图*/
		菜单栏1.添加监听器(0, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				文件选择器1.设置文件过滤器("地图文件", "map","MAP");
				if(文件选择器1.弹出对话框("创建") == 文件选择器.确定选项)
				{
					地图属性设置窗口.SetVisible(true);
					TileMapPath = 文件选择器1.获取选中文件的路径();
				}
			}
			
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		/*打开地图*/
		菜单栏1.添加监听器(1, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				文件选择器1.设置文件过滤器("地图文件", "map","MAP");
				if(文件选择器1.弹出保存文件对话框() == 文件选择器.确定选项)
				{
					System.out.println("打开文件");
					
					TileMapPath = 文件选择器1.获取选中文件的路径();
					
					map = Main.OpenTileMap(TileMapPath);
				}
			}
			
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		/*保存地图*/
		菜单栏1.添加监听器(2, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{
				if(map != null && TileMapPath != null)
				{
					try {TileMap.WriteMap(TileMapPath, map);} 
					catch (IOException e1){e1.printStackTrace();}
				}
			}
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		/*关闭程序*/
		菜单栏1.添加监听器(4, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{System.out.println("Window close");System.exit(0);}
			@Override public void stateChanged(ChangeEvent event) {}
		});
		

		场景1.获取渲染器().设置渲染事件(new 事件(){

			@Override
			public void 执行(Object 物体)
			{
				渲染器 渲染器1 = 场景1.获取渲染器();
				Graphics g = (Graphics)物体;
				
				if(map != null)
				{
					for(int i = 0;i < map.height;i++)
					{
						for(int j = 0;j < map.width;j++)
						{
							int n = j + i * map.width;
							if(map.data[n] > 0)
							{
								渲染器1.绘制图像(g, img[map.data[n] - 1], j * map.tile_width, 
										i * map.tile_height, map.tile_width, map.tile_height,null);
							}
							
							渲染器1.绘制矩形(g, j * map.tile_width, i * map.tile_height, 
									map.tile_width, map.tile_height);
						}
					}
				}
				
			}
			
		});
	}
	
	
	public static boolean Collision(int x1,int y1,int width1,int height1,
			int x2,int y2,int width2,int height2)
	{
		if(x1 <= x2 + width2 && y1 + height1 >= y2
				&& x1 + width1 >= x2 && y1 <= y2 + height2)
		{
			return true;
		}
		
		return false;
	}
	
}







