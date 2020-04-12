package fly.graphics;

import java.awt.Graphics;

import javax.swing.JPanel;

import fly.window.Window;
import fly.window.窗口;

public class 场景 extends JPanel
{
	private 渲染器 渲染器1;
	
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
	
	/*由Swing调用以绘制组件。这个方法实际上将绘画工作委托给三个受保护的方法：paintComponent、paintBorder和paintChildren。*/
	@Override public void paint(Graphics g){super.paint(g);渲染器1.渲染(g);super.repaint();}
	
	/*设置和获取渲染器*/
	public void 设置渲染器(渲染器 renderer) {this.渲染器1 = renderer;}
	public 渲染器 获取渲染器() {return this.渲染器1;}
	
	/*将场景添加至窗口*/
	public void 添加至窗口(Window w){w.GetFrame().add(this);w.SetVisble(true);}
	public void 添加至窗口(窗口 w){w.获取窗口().add(this);w.设置可视(true);}
}











