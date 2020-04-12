package main;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import fly.window.FlyTextFieldManager;
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
	
	public static void main(String[] args) throws MalformedURLException, IOException 
	{
		主窗口 = new 窗口("Tile Map Editor");
		
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
				
				try {TileMap.WriteMap(TileMapPath, new_map);} 
				catch (IOException e1){e1.printStackTrace();}
				
				try{map = TileMap.ReadMap(TileMapPath);}
				catch (IOException e2) {e2.printStackTrace();}
				
				地图属性设置窗口.SetVisible(false);
			}
			
		});
		
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
				if(文件选择器1.弹出保存文件对话框() == 文件选择器.确定选项)
				{
					地图属性设置窗口.SetVisible(true);
					TileMapPath = 文件选择器1.获取选中文件的路径();
				}
			}
			
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		菜单栏1.添加监听器(1, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				if(文件选择器1.弹出保存文件对话框() == 文件选择器.确定选项)
				{
					System.out.println("打开文件");
					
					TileMapPath = 文件选择器1.获取选中文件的路径();
					
					try{map = TileMap.ReadMap(TileMapPath);}
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
				}
			}
			
			@Override public void stateChanged(ChangeEvent event) {}
		});
		
		菜单栏1.添加监听器(2, new FlyActionAndChangeListener() {
			
			@Override public void actionPerformed(ActionEvent event)
			{
				try {TileMap.WriteMap(TileMapPath, map);} 
				catch (IOException e1){e1.printStackTrace();}
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
	
	
}
