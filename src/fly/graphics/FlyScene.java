package fly.graphics;

import java.awt.Graphics;

import javax.swing.JPanel;

import fly.window.Window;
import fly.window.窗口;

public class FlyScene extends JPanel
{
	private FlyRenderer renderer;
	
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
	
	/*由Swing调用以绘制组件。这个方法实际上将绘画工作委托给三个受保护的方法：paintComponent、paintBorder和paintChildren。*/
	@Override public void paint(Graphics g){super.paint(g);renderer.Render(g);super.repaint();}
	
	/*设置和获取渲染器*/
	public void SetRenderer(FlyRenderer renderer) {this.renderer = renderer;}
	public FlyRenderer GetRenderer() {return this.renderer;}
	
	/*将场景添加至窗口*/
	public void AddToWindow(Window w){w.GetFrame().add(this);w.SetVisible(true);}
	public void AddToWindow(窗口 w){w.获取窗口().add(this);w.设置可视(true);}
}











