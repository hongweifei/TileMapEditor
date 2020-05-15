package fly.graphics;



import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;



/**
 * @file FlyRenderer.java
 * @author Fly
 */


/*
 * 图像读取
 * 1.Image image = ImageIO.read(new FileInputStream(“文件路径”));
 * 2.Image image = ImageIO.read(new File（）);
 * 3.Image image = ImageIO.read(new URL（）));
 *
 * 4.Toolkit.getDefaultToolkit().getImage(“图片路径”)；
 *
 * 5.new ImageIcon("图片路径).getImage()；
 */

/**
 * 渲染器类
 * */
public class FlyRenderer
{
	//private Event<Graphics> render_event;
	//private boolean auto_repaint = false;

	public FlyRenderer()
	{
		//render_event = new Event<Graphics>() {@Override public void invoke(Graphics g) {}};
	}


	/**
	 * 设置渲染事件
	 *
	 * @param render_event 要设置的渲染事件
	 * */
	//public void SetRenderEvent(Event<Graphics> render_event)
	//{
	//	this.render_event = render_event;
	//}


	/**
	 * 设置渲染事件
	 *
	 * @param render_event 要设置的渲染事件
	 * @param auto_repaint 自动重绘
	 * */
	//public void SetRenderEvent(Event<Graphics> render_event,boolean auto_repaint)
	//{
	//	this.render_event = render_event;
	//	this.auto_repaint = auto_repaint;
	//}


	/**
	 * 执行渲染事件，Event.invoke的object是graphics类型
	 *
	 * @param g
	 * */
	//public boolean Render(Graphics g) {render_event.invoke(g);return this.auto_repaint;}


	/**
	 * 画线
	 *
	 * @param g
	 * @param x1 绘制的第一个点的横坐标
	 * @param y1 绘制的第一个点的纵坐标
	 * @param x2 绘制的第二个点的横坐标
	 * @param y2 绘制的第二个点的纵坐标
	 * */
	public final void DrawLine(Graphics g,int x1,int y1,int x2,int y2)
	{g.drawLine(x1, y1, x2, y2);}

	/**
	 * 画矩形
	 *
	 * @param g
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * */
	public final void DrawRect(Graphics g,int x,int y,int width,int height)
	{g.drawRect(x, y, width, height);}

	/**
	 * 画3D矩形
	 *
	 * @param g
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * @param raised 用于确定矩形是在曲面上方凸起还是在曲面中凹陷
	 * */
	public final void Draw3DRect(Graphics g,int x,int y,int width,int height,boolean raised)
	{g.draw3DRect(x, y, width, height, raised);}

	/**
	 * 画文本
	 *
	 * @param g
	 * @param text 要绘制的文本
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * */
	public final void DrawText(Graphics g,String text,int x,int y)
	{g.drawString(text,x,y);}

	/**
	 * 绘制图片，不可设置绘制宽高
	 *
	 * @param g
	 * @param img 要绘制的图像
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Graphics g,Image img,int x,int y,ImageObserver observer)
	{return g.drawImage(img, x, y, observer);}

	/**
	 * 绘制图片，可设置背景颜色，不可设置绘制宽高
	 *
	 * @param g
	 * @param img 要绘制的图像
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param back_ground_color 背景颜色
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Graphics g,Image img,int x,int y,Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, x, y, back_ground_color, observer);}

	/**
	 * 绘制图片，不可设置背景颜色，可设置绘制宽高
	 *
	 * @param g
	 * @param img 要绘制的图像
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Graphics g,Image img,int x,int y,int width,int height,ImageObserver observer)
	{return g.drawImage(img, x, y, width, height, observer);}

	/**
	 * 绘制图片，可设置背景颜色，可设置宽高
	 *
	 * @param g
	 * @param img 要绘制的图像
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * @param back_ground_color 背景颜色
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Graphics g,Image img,int x,int y,int width,int height,
			Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, x, y, width, height, back_ground_color, observer);}

	/**
	 * 绘制裁剪后的图片图片，不可设置背景颜色，可设置绘制宽高
	 *
	 * @param g
	 * @param img 要绘制的图像
	 * @param dx1 目标矩形第一个角的x坐标。
	 * @param dy1 目标矩形第一个角的y坐标。
	 * @param dx2 目标矩形第二个角的x坐标。
	 * @param dy2 目标矩形第二个角的y坐标。
	 * @param sx1 源矩形第一个角的x坐标。
	 * @param sy1 源矩形第一个角的y坐标。
	 * @param sx2 源矩形第二个角的x坐标。
	 * @param sy2 源矩形的第二个角点的y坐标。
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Graphics g,Image img,int dx1,int dy1,int dx2,int dy2,
			int sx1,int sy1,int sx2,int sy2,ImageObserver observer)
	{return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);}

	/**
	 * 绘制裁剪后的图片图片，可设置背景颜色，可设置绘制宽高
	 *
	 * @param g
	 * @param img 要绘制的图像
	 * @param dx1  目标矩形第一个角的x坐标。
	 * @param dy1  目标矩形第一个角的y坐标。
	 * @param dx2  目标矩形第二个角的x坐标。
	 * @param dy2  目标矩形第二个角的y坐标。
	 * @param sx1  源矩形第一个角的x坐标。
	 * @param sy1  源矩形第一个角的y坐标。
	 * @param sx2  源矩形第二个角的x坐标。
	 * @param sy2  源矩形的第二个角点的y坐标。
	 * @param back_ground_color 背景颜色
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Graphics g,Image img,int dx1,int dy1,int dx2,int dy2,
			int sx1,int sy1,int sx2,int sy2,Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, back_ground_color, observer);}

	/**
	 * 绘制裁剪后的图片图片，不可设置背景颜色，可设置绘制宽高
	 *
	 * @param g
	 * @param img 要绘制的图像
	 * @param x 目标矩形第一个角的x坐标。
	 * @param y 目标矩形第一个角的y坐标。
	 * @param width 目标矩形的宽。
	 * @param height 目标矩形的高。
	 * @param sx 源矩形第一个角的x坐标。
	 * @param sy 源矩形第一个角的y坐标。
	 * @param swidth 源矩形的宽。
	 * @param sheight 源矩形的高。
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Graphics g,Image img,float x,float y,int width,int height,
			int sx,int sy,int swidth,int sheight,ImageObserver observer)
	{
		return this.DrawImage(g, img, (int)x, (int)y, (int)x + width, (int)y + height,
				sx, sy, sx + swidth, sy + sheight, observer);
	}

	/**
	 * 绘制裁剪后的图片图片，可设置背景颜色，可设置绘制宽高
	 *
	 * @param g
	 * @param img 要绘制的图像
	 * @param x 目标矩形第一个角的x坐标。
	 * @param y 目标矩形第一个角的y坐标。
	 * @param width 目标矩形的宽。
	 * @param height 目标矩形的高。
	 * @param sx 源矩形第一个角的x坐标。
	 * @param sy 源矩形第一个角的y坐标。
	 * @param swidth 源矩形的宽。
	 * @param sheight 源矩形的高。
	 * @param back_ground_color 背景颜色
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Graphics g,Image img,float x,float y,int width,int height,
			int sx,int sy,int swidth,int sheight,Color back_ground_color,ImageObserver observer)
	{
		return this.DrawImage(g, img, (int)x, (int)y, (int)x + width, (int)y + height,
				sx, sy, sx + swidth, sy + sheight, back_ground_color, observer);
	}
}












