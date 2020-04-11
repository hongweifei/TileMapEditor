package fly.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Renderer
{
	private Event render_event;
	
	public Renderer()
	{render_event = new Event() {@Override public void invoke(Object object) {}};}
	
	/*设置渲染事件和执行渲染事件*/
	public void SetRenderEvent(Event render_event){this.render_event = render_event;}//设置渲染事件
	public void Render(Graphics g) {render_event.invoke(g);}//渲染，Event.invoke的object是graphics类型
	
	/*画线*/
	public void DrawLine(Graphics g,int x1,int y1,int x2,int y2)
	{g.drawLine(x1, y1, x2, y2);}
	
	/*画矩形*/
	public void DrawRect(Graphics g,int x,int y,int width,int height)
	{g.drawRect(x, y, width, height);}
	
	/*画3D矩形*/
	public void Draw3DRect(Graphics g,int x,int y,int width,int height,boolean raised)
	{g.draw3DRect(x, y, width, height, raised);}
	
	/*画文本*/
	public void DrawText(Graphics g,String str,int x,int y)
	{g.drawString(str,x,y);}
	
	/*绘制图片，不可设置绘制宽高
	 * */
	public boolean DrawImage(Graphics g,Image img,int x,int y,ImageObserver observer)
	{return g.drawImage(img, x, y, observer);}
	
	/*绘制图片，可设置背景颜色，不可设置绘制宽高
	 * */
	public boolean DrawImage(Graphics g,Image img,int x,int y,Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, x, y, back_ground_color, observer);}
	
	/*绘制图片，不可设置背景颜色，可设置绘制宽高
	 * */
	public boolean DrawImage(Graphics g,Image img,int x,int y,int width,int height,ImageObserver observer)
	{return g.drawImage(img, x, y, width, height, observer);}
	
	/*绘制图片，可设置背景颜色，可设置宽高
	 * */
	public boolean DrawImage(Graphics g,Image img,int x,int y,int width,int height,
			Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, x, y, width, height, back_ground_color, observer);}
	
	/*绘制裁剪后的图片图片，不可设置背景颜色，可设置绘制宽高
	 * dx1目标矩形第一个角的x坐标。
	 * dy1目标矩形第一个角的y坐标。
	 * dx2目标矩形第二个角的x坐标。
	 * dy2目标矩形第二个角的y坐标。
	 * sx1源矩形第一个角的x坐标。
	 * sy1源矩形第一个角的y坐标。
	 * sx2源矩形第二个角的x坐标。
	 * sy2源矩形的第二个角点的y坐标。
	 * */
	public boolean DrawImage(Graphics g,Image img,int dx1,int dy1,int dx2,int dy2,
			int sx1,int sy1,int sx2,int sy2,ImageObserver observer)
	{return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);}
	
	/*绘制裁剪后的图片图片，可设置背景颜色，可设置绘制宽高
	 * dx1目标矩形第一个角的x坐标。
	 * dy1目标矩形第一个角的y坐标。
	 * dx2目标矩形第二个角的x坐标。
	 * dy2目标矩形第二个角的y坐标。
	 * sx1源矩形第一个角的x坐标。
	 * sy1源矩形第一个角的y坐标。
	 * sx2源矩形第二个角的x坐标。
	 * sy2源矩形的第二个角点的y坐标。
	 * back_ground_color背景颜色
	 * */
	public boolean DrawImage(Graphics g,Image img,int dx1,int dy1,int dx2,int dy2,
			int sx1,int sy1,int sx2,int sy2,Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, back_ground_color, observer);}
	
	/*绘制裁剪后的图片图片，不可设置背景颜色，可设置绘制宽高
	 * x目标矩形第一个角的x坐标。
	 * y目标矩形第一个角的y坐标。
	 * width目标矩形的宽。
	 * height目标矩形的高。
	 * sx源矩形第一个角的x坐标。
	 * sy源矩形第一个角的y坐标。
	 * swidth源矩形的宽。
	 * sheight源矩形的高。
	 * */
	public boolean DrawImage(Graphics g,Image img,float x,float y,int width,int height,
			int sx,int sy,int swidth,int sheight,ImageObserver observer)
	{
		return this.DrawImage(g, img, (int)x, (int)y, (int)x + width, (int)y + height, 
				sx, sy, sx + swidth, sy + sheight, observer);
	}
	
	/*绘制裁剪后的图片图片，可设置背景颜色，可设置绘制宽高
	 * x目标矩形第一个角的x坐标。
	 * y目标矩形第一个角的y坐标。
	 * width目标矩形的宽。
	 * height目标矩形的高。
	 * sx源矩形第一个角的x坐标。
	 * sy源矩形第一个角的y坐标。
	 * swidth源矩形的宽。
	 * sheight源矩形的高。
	 * back_ground_color背景颜色
	 * */
	public boolean DrawImage(Graphics g,Image img,float x,float y,int width,int height,
			int sx,int sy,int swidth,int sheight,Color back_ground_color,ImageObserver observer)
	{
		return this.DrawImage(g, img, (int)x, (int)y, (int)x + width, (int)y + height, 
				sx, sy, sx + swidth, sy + sheight, back_ground_color, observer);
	}
}












