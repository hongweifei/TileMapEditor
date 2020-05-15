package fly.graphics;

import java.awt.Graphics;

import javax.swing.JPanel;

import fly.window.Window;
import fly.window.窗口;
import fly.window.listener.FlyKeyListener;
import fly.window.listener.FlyMouseListener;
import fly.window.listener.FlyWindowListener;

public class 场景 extends JPanel
{
	private 渲染器 渲染器1;

	/**场景渲染事件*/
	private 事件<Graphics> render_event =
			new 事件<Graphics>(){@Override public void 执行(Graphics t) {}};

	private boolean auto_repaint = false;

	public 场景(){渲染器1 = new 渲染器();}
	public 场景(Window w) {this();this.添加至窗口(w);}
	public 场景(窗口 w) {this();this.添加至窗口(w);}

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

	/**由Swing调用以绘制组件。这个方法实际上将绘画工作委托给三个受保护的方法：paintComponent、paintBorder和paintChildren。*/
	@Override public void paint(Graphics g)
	{
		super.paint(g);

		render_event.执行(g);

		if(//渲染器1.渲染(g)||
			auto_repaint)
			super.repaint();
	}


	/**
	 * 设置渲染事件
	 * */
	public void 设置渲染事件(事件<Graphics> event)
	{
		this.render_event = event;
	}

	/**
	 * 设置渲染事件
	 * */
	public void 设置渲染事件(事件<Graphics> event,boolean repaint)
	{
		this.render_event = event;
		this.auto_repaint = repaint;
	}


	/**设置和获取渲染器*/
	public void 设置渲染器(渲染器 renderer) {this.渲染器1 = renderer;}
	public 渲染器 获取渲染器() {return this.渲染器1;}


	/**设置鼠标监听器*/
	public void 设置鼠标监听器(FlyMouseListener 监听器)
	{
		this.addMouseListener(监听器);
		this.addMouseMotionListener(监听器);
		this.addMouseWheelListener(监听器);
	}

	/*设置key监听器*/
	public void 设置按键监听器(FlyKeyListener 监听器){this.addKeyListener(监听器);}

	/*设置鼠标监听器和key监听器*/
	public void 设置鼠标和按键监听器(FlyWindowListener 监听器)
	{
		this.addMouseListener(监听器);
		this.addMouseMotionListener(监听器);
		this.addMouseWheelListener(监听器);
		this.addKeyListener(监听器);
	}


	/*将场景添加至窗口*/
	public void 添加至窗口(Window w){w.GetFrame().add(this);w.SetVisible(true);}
	public void 添加至窗口(窗口 w){w.获取窗口().add(this);w.设置可视(true);}
}











