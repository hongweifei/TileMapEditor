package fly.graphics;

import java.awt.Graphics;

import javax.swing.JPanel;

import fly.window.Window;
import fly.window.窗口;
import fly.window.listener.FlyKeyListener;
import fly.window.listener.FlyMouseListener;
import fly.window.listener.FlyWindowListener;


/**
 * @file FlyScene.java
 * @author Fly
 */


/**
 * 场景类
 * */
public class FlyScene extends JPanel
{
	private FlyRenderer renderer;///< 渲染器

	public FlyScene(){renderer = new FlyRenderer();}
	public FlyScene(Window w) {this();this.AddToWindow(w);}
	public FlyScene(窗口 w) {this();this.AddToWindow(w);}

	/*
	绘制组件的边框。
	@Override public void paintBorder(Graphics g) {super.paintBorder(g);}

	绘制此组件的子组件。
	@Override public void paintChildren(Graphics g){super.paintChildren(g);}

	如果UI委托为非空，则调用UI委托的绘制方法。
	@Override public void paintComponent(Graphics g)
	{super.paintComponent(g);}

	绘制此组件及其所有子组件。
	@Override public void paintAll(Graphics g){super.paintAll(g);}
	*/

	/**
	 * 由Swing调用以绘制组件。这个方法实际上将绘画工作委托给三个受保护的方法：paintComponent、paintBorder和paintChildren。
	 *
	 * @param g
	 * */
	@Override public void paint(Graphics g){super.paint(g);renderer.Render(g);super.repaint();}

	/**
	 * 设置场景使用的渲染器
	 *
	 * @param renderer 给场景的渲染器
	 * */
	public void SetRenderer(FlyRenderer renderer) {this.renderer = renderer;}

	/**
	 * 获取场景使用的渲染器
	 * */
	public FlyRenderer GetRenderer() {return this.renderer;}

	/**
	 * 设置鼠标监听器
	 *
	 * @param listener 要设置的监听器
	 * */
	public void SetMouseListener(FlyMouseListener listener)
	{
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.addMouseWheelListener(listener);
	}


	/**
	 * 设置key监听器
	 *
	 * @param listener 要设置的监听器
	 * */
	public void SetKeyListener(FlyKeyListener listener){this.addKeyListener(listener);}

	/**
	 * 设置鼠标监听器和key监听器
	 *
	 * @param listener 要设置的监听器
	 * */
	public void SetMouseAndKeyListener(FlyWindowListener listener)
	{
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.addMouseWheelListener(listener);
		this.addKeyListener(listener);
	}

	/**
	 * 将场景添加至窗口
	 *
	 * @param w 场景要添加到的窗口
	 * */
	public void AddToWindow(Window w){w.GetFrame().add(this);w.SetVisible(true);}

	/**
	 * 将场景添加至窗口
	 *
	 * @param w 场景要添加到的窗口
	 * */
	public void AddToWindow(窗口 w){w.获取窗口().add(this);w.设置可视(true);}
}











