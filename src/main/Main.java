package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;

import fly.graphics.TileMap;
import fly.graphics.事件;
import fly.graphics.场景;
import fly.graphics.渲染器;
import fly.window.FlyActionAndChangeListener;
import fly.window.FlyFileChooser;
import fly.window.列表框;
import fly.window.文件选择器;
import fly.window.窗口;
import fly.window.菜单栏;

/*Java无宏定义，不可全中文*/

public class Main
{
	static 窗口 主窗口;
	static 菜单栏 菜单栏1;
	
	static TileMap map = null;
	
	public static void main(String[] args) throws MalformedURLException, IOException 
	{
		主窗口 = new 窗口("Tile Map Editor");
		
		菜单栏1 = new 菜单栏();
		菜单栏1.添加菜单("文件");
		
		String[] 菜单项文本 = {"新建","打开","保存","另存为","关闭"};
		菜单栏1.添加菜单项(0, 菜单项文本);
		菜单栏1.插入分割线(0, 2);
		菜单栏1.插入分割线(0, 5);
		
		//列表框 列表框1 = new 列表框();
		//列表框1.添加列表项("Test");
		//列表框1.添加列表项(10);
		
		主窗口.设置菜单栏(菜单栏1);
		//主窗口.添加(列表框1);
		
		菜单栏1.添加监听器(0, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				if(文件选择器1.弹出保存文件对话框() == 文件选择器1.批准选项)
				{
					System.out.println("新建文件");
				}
			}
			
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		菜单栏1.添加监听器(1, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				if(文件选择器1.弹出保存文件对话框() == 文件选择器1.批准选项)
				{
					System.out.println("打开文件");
					
					try{map = TileMap.ReadMap(文件选择器1.获取选中文件的路径());}
					catch (IOException e) {e.printStackTrace();}
					
					for(int i = 0;i < map.width * map.height;i++)
					{
						System.out.println(map.data[i]);
					}
					
					
				}
			}
			
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		菜单栏1.添加监听器(4, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{System.out.println("Window close");System.exit(0);}
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		场景 场景1 = new 场景(主窗口);
		场景1.获取渲染器().设置渲染事件(new 事件(){

			@Override
			public void 执行(Object 物体)
			{
				渲染器 渲染器1 = 场景1.获取渲染器();
				Graphics g = (Graphics)物体;
				
				if(map != null)
				{
					Image[] img = new Image[map.tile_count];
					for(int i = 0;i < map.tile_count;i++)
					{
						System.out.println(map.tile_image_path[i]);
						try {img[i] = ImageIO.read(new FileInputStream(map.tile_image_path[i]));}
						catch (IOException e) {e.printStackTrace();}
					}
					for(int i = 0;i < map.height;i++)
					{
						for(int j = 0;j < map.width;j++)
						{
							if(map.data[i] == 0)
								continue;
							渲染器1.绘制图像(g, img[map.data[i] - 1], j * map.tile_width, 
							i * map.tile_height, map.tile_width, map.tile_height,null);
						}
					}
				}
				
			}
			
		});
	}
	
	
}
