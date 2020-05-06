package main;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

import fly.graphics.FlyCamera;
import fly.graphics.FlyTileMap;
import fly.graphics.事件;
import fly.graphics.场景;
import fly.graphics.渲染器;
import fly.window.FlyDialog;
import fly.window.FlyLabelManager;
import fly.window.FlyTextFieldManager;
import fly.window.文件选择器;
import fly.window.窗口;
import fly.window.菜单栏;
import fly.window.listener.FlyActionAndChangeListener;
import fly.window.listener.FlyMouseListener;
import fly.window.widget.FlyList;



public class Main
{
	static 窗口 主窗口;
	static 菜单栏 菜单栏1;

	static FlyDialog 地图属性设置窗口;
	static FlyLabelManager label;
	static FlyTextFieldManager text_field;

	static FlyTileMap map = null;
	static int map_pic_index = 0;
	static Image[] img = null;
	static String tile_map_path = null;

	static boolean map_can_render = true;

	static FlyCamera map_camera = new FlyCamera();
	static int map_render_width = 16;
	static int map_render_height = 16;

	public static FlyTileMap OpenTileMap(String path)
	{
		FlyTileMap map = null;
		try{map = FlyTileMap.ReadMap(path);}
		catch (IOException e) {e.printStackTrace();}

		for(int i = 0;i < map.height;i++)
		{
			for(int j = 0;j < map.width;j++)
			{
				//System.out.print(map.data[j + i * map.width]);
			}
			//System.out.println();
		}

		img = new Image[map.tile_count];
		for(int i = 0;i < map.tile_count;i++)
		{
			//System.out.println(map.tile_image_path[i]);
			try {img[i] = ImageIO.read(new File(map.tile_image_path[i]));}
			catch (IOException e) {e.printStackTrace();}
		}

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
				FlyTileMap new_map = new FlyTileMap(Integer.parseInt(text_field.GetText(0)),
						Integer.parseInt(text_field.GetText(1)),
						Integer.parseInt(text_field.GetText(2)),
						Integer.parseInt(text_field.GetText(3)),null,null);

				tile_map_path += ".map";


				try {FlyTileMap.WriteMap(tile_map_path, new_map);}
				catch (IOException e1){e1.printStackTrace();}

				map = Main.OpenTileMap(tile_map_path);

				地图属性设置窗口.SetVisible(false);
			}

		});

		菜单栏1 = new 菜单栏();
		菜单栏1.添加菜单("文件","地图","地图绘制");

		菜单栏1.添加菜单项(0, "新建","打开","保存","另存为","关闭");
		菜单栏1.添加菜单项(1, "导入图片(绝对路径)","导入图片(相对路径)","移除图片");
		菜单栏1.添加菜单项(2,"绘制宽高调整","绘制图片选择");
		菜单栏1.插入分割线(0, 2);
		菜单栏1.插入分割线(0, 5);
		菜单栏1.插入分割线(1, 2);

		/*导入图片(绝对路径)*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("导入图片(绝对路径)"), new FlyActionAndChangeListener(){

			@Override public void actionPerformed(ActionEvent e)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				文件选择器1.设置文件过滤器("图像文件", "png","bmp","jpg","jepg");
				if(文件选择器1.弹出对话框("确定") == 文件选择器.确定选项)
				{
					if(map != null)
						map.AddImage(文件选择器1.获取选中文件的路径());
				}
			}
			@Override public void stateChanged(ChangeEvent event) {}
		});

		/*导入图片(相对路径)*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("导入图片(相对路径)"), new FlyActionAndChangeListener(){

			@Override public void actionPerformed(ActionEvent e)
			{
				FlyDialog 导入图片窗口 = new FlyDialog(主窗口,"图片路径设置",250,100);
				导入图片窗口.SetLayout(new GridLayout(2,1));

				FlyTextFieldManager manager = new FlyTextFieldManager();
				manager.AddTextField();

				JButton 导入按钮 = new JButton("导入");
				导入按钮.addActionListener(new ActionListener() {
					@Override public void actionPerformed(ActionEvent e)
					{
						if(map != null && !manager.GetText(0).isEmpty())
							map.AddImage(manager.GetText(0));
						导入图片窗口.SetVisible(false);
					}
				});

				manager.AddToWindow(导入图片窗口);
				导入图片窗口.Add(导入按钮);
				导入图片窗口.SetVisible(true);
			}
			@Override public void stateChanged(ChangeEvent event) {}
		});

		/*移除图片*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("移除图片"), new FlyActionAndChangeListener(){

			@Override public void actionPerformed(ActionEvent e)
			{
				if(map != null)
				{
					FlyTileMap now_map = map;

					FlyDialog 移除图片窗口 = new FlyDialog(主窗口,"移除图片",250,100);
					移除图片窗口.SetLayout(new GridLayout());

					FlyList<String> list = new FlyList<String>();

					for(int i = 0;i < map.tile_count;i++)
						list.AddItem(map.tile_image_path[i]);

					JButton 移除按钮 = new JButton("移除");
					移除按钮.addActionListener(new ActionListener() {
						@Override public void actionPerformed(ActionEvent e)
						{
							if(now_map == map && list.Get().isSelectionEmpty() == false)
							{
								map_can_render = false;

								map.RemoveImage(list.Get().getSelectedIndex());

								map_can_render = true;
							}
							else if(now_map != map)
								JOptionPane.showOptionDialog(null,
								"该地图以关闭", "提示", JOptionPane.DEFAULT_OPTION,
								JOptionPane.ERROR_MESSAGE, null, null, null);

							移除图片窗口.SetVisible(false);


						}
					});

					移除图片窗口.Add(list);
					移除图片窗口.Add(移除按钮);
					移除图片窗口.SetVisible(true);
				}
			}
			@Override public void stateChanged(ChangeEvent event) {}
		});


		/*绘制宽高调整*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("绘制宽高调整"), new ActionListener() {
			@Override public void actionPerformed(ActionEvent e)
			{
				FlyDialog 绘制宽高调整窗口 = new FlyDialog(主窗口,"地图绘制宽高调整",250,100);
				绘制宽高调整窗口.SetLayout(new GridLayout(0,4));

				FlyLabelManager label_manager = new FlyLabelManager();
				label_manager.AddLabels("绘制宽度：","绘制高度：");

				FlyTextFieldManager field_manager = new FlyTextFieldManager();
				field_manager.AddTextFields(Integer.toString(map_render_width),
				Integer.toString(map_render_height));

				label_manager.AddToWindow(绘制宽高调整窗口,0);
				field_manager.AddToWindow(绘制宽高调整窗口,0);
				label_manager.AddToWindow(绘制宽高调整窗口,1);
				field_manager.AddToWindow(绘制宽高调整窗口,1);

				JButton 调整按钮 = new JButton("确定");
				调整按钮.addActionListener(new ActionListener() {
					@Override public void actionPerformed(ActionEvent e)
					{
						if(field_manager.GetText(0).trim().equals("") == false
							&& field_manager.GetText(1).trim().equals("") == false)
						{
							map_render_width = Integer.parseInt(field_manager.GetText(0));
							map_render_height = Integer.parseInt(field_manager.GetText(1));
						}
						else
							JOptionPane.showOptionDialog(null, "绘制宽度或高度编辑框为空", "提示",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

						绘制宽高调整窗口.SetVisible(false);
					}
				});

				绘制宽高调整窗口.Add(调整按钮);
				绘制宽高调整窗口.SetVisible(true);

			}
		});

		/*绘制图片选择*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("绘制图片选择"), new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				FlyDialog win = new FlyDialog();
				win.GetDialog().setSize(300, 150);
				win.SetLayout(new GridLayout(2,1));

				JButton yes_button = new JButton("确认");

				JList list = new JList();
				DefaultListModel defaultListModel = new DefaultListModel();

				defaultListModel.add(0, "null");

				if(map != null)
				{
					for(int i = 0;i < map.tile_count;i++)
					{
						defaultListModel.add(i + 1, map.tile_image_path[i]);
					}
				}

				list.setModel(defaultListModel);
				list.setSelectedIndex(map_pic_index);

				yes_button.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						map_pic_index = list.getSelectedIndex();
						win.SetVisible(false);
					}

				});

				win.Add(list);
				win.Add(yes_button);
				win.SetVisible(true);
			}

		});

		主窗口.设置菜单栏(菜单栏1);

		/*地图编辑*/
		场景 场景1 = new 场景(主窗口);
		场景1.设置鼠标监听器(new FlyMouseListener(){

			int mouse_last_x = 0;
			int mouse_last_y = 0;

			@Override public void mouseClicked(MouseEvent e)
			{
				mouse_last_x = e.getX();
				mouse_last_y = e.getY();

				if(map != null && e.getButton() == MouseEvent.BUTTON1)
				{
					for(int i = 0;i < map.height;i++)
					{
						for(int j = 0;j < map.width;j++)
						{
							int n = j + i * map.width;

							if(Main.Collision(e.getX(), e.getY(),
									1, 1,
									map_camera.look_at_x + j * map_render_width,
									map_camera.look_at_y + i * map_render_height,
									map_render_width, map_render_height))
							{
								/*
								if(map.data[n] < map.tile_count)
									map.data[n] += 1;
								*/
								map.data[n] = (short) map_pic_index;
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
									map_camera.look_at_x + j * map_render_width,
									map_camera.look_at_y + i * map_render_height,
									map_render_width, map_render_height))
							{
								/*
								if(map.data[n] > 0)
									map.data[n] -= 1;
								*/
								map.data[n] = (short) map_pic_index;
							}
						}
					}
				}
			}

			@Override public void mousePressed(MouseEvent e)
			{
				mouse_last_x = e.getX();
				mouse_last_y = e.getY();
			}

			@Override public void mouseReleased(MouseEvent e)
			{

			}

			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}

			/*鼠标拖动*/
			@Override public void mouseDragged(MouseEvent e)
			{
				if(map != null)
				{
					int dx = e.getX() - mouse_last_x;
					int dy = e.getY() - mouse_last_y;

					map_camera.look_at_x += dx;
					map_camera.look_at_y += dy;

					mouse_last_x = e.getX();
					mouse_last_y = e.getY();
				}
			}

			@Override public void mouseMoved(MouseEvent e){}
			@Override public void mouseWheelMoved(MouseWheelEvent e){}
		});

		//主窗口.添加(列表框1);

		/*新建地图*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("新建"), new FlyActionAndChangeListener() {

			@Override public void actionPerformed(ActionEvent event)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				文件选择器1.设置文件过滤器("地图文件", "map");
				if(文件选择器1.弹出保存文件对话框() == 文件选择器.确定选项)
				{
					地图属性设置窗口.SetVisible(true);
					tile_map_path = 文件选择器1.获取选中文件的路径();
				}
			}

			@Override public void stateChanged(ChangeEvent event) {}
		});

		/*打开地图*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("打开"), new FlyActionAndChangeListener() {

			@Override public void actionPerformed(ActionEvent event)
			{
				文件选择器 文件选择器1 = new 文件选择器();
				文件选择器1.设置文件过滤器("地图文件", "map");
				if(文件选择器1.弹出打开文件对话框() == 文件选择器.确定选项)
				{
					System.out.println("打开文件");

					tile_map_path = 文件选择器1.获取选中文件的路径();

					map = Main.OpenTileMap(tile_map_path);
				}
			}

			@Override public void stateChanged(ChangeEvent event) {}
		});

		/*保存地图*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("保存"), new FlyActionAndChangeListener() {

			@Override public void actionPerformed(ActionEvent event)
			{
				if(map != null && tile_map_path != null)
				{
					try {FlyTileMap.WriteMap(tile_map_path, map);}
					catch (IOException e1){e1.printStackTrace();}
				}
			}
			@Override public void stateChanged(ChangeEvent event) {}
		});

		/*关闭程序*/
		菜单栏1.添加监听器(菜单栏1.获取菜单项索引("关闭"), new FlyActionAndChangeListener() {

			@Override public void actionPerformed(ActionEvent event)
			{System.out.println("Window close");System.exit(0);}
			@Override public void stateChanged(ChangeEvent event) {}
		});


		场景1.获取渲染器().设置渲染事件(new 事件<Graphics>(){

			@Override
			public void 执行(Graphics g)
			{
				渲染器 渲染器1 = 场景1.获取渲染器();

				if(map != null && map_can_render)
					map.Render(渲染器1, g,map_camera.look_at_x,map_camera.look_at_y,
					map_render_width,map_render_height);
			}

		},true);
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







