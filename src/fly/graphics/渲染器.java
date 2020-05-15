package fly.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;


/*图像读取
 * 1.Image image = ImageIO.read(new FileInputStream(“文件路径”));
 * 2.Image image = ImageIO.read(new File（）);
 * 3.Image image = ImageIO.read(new URL（）));
 *
 * 4.Toolkit.getDefaultToolkit().getImage(“图片路径”)；
 *
 * 5.new ImageIcon("图片路径).getImage()；
 */

public class 渲染器
{
	//private 事件<Graphics> 渲染事件;
	//private boolean 自动重绘 = false;

	public 渲染器()
	{
		//渲染事件 = new 事件<Graphics>() {@Override public void 执行(Graphics g) {}};
	}

	/**设置渲染事件*/

	/*
	public void 设置渲染事件(事件<Graphics> 渲染事件,boolean 自动重绘)
	{
		this.渲染事件 = 渲染事件;
		this.自动重绘 = 自动重绘;
	}
	*/

	/**执行渲染事件*/
	//public boolean 渲染(Graphics g) {渲染事件.执行(g);return this.自动重绘;}//渲染，Event.invoke的object是graphics类型

	/*画线*/
	public final void 绘制线(Graphics g,int 横坐标1,int 纵坐标1,int 横坐标2,int 纵坐标2)
	{g.drawLine(横坐标1, 纵坐标1, 横坐标2, 纵坐标2);}

	/*画矩形*/
	public final void 绘制矩形(Graphics g,int 横坐标,int 纵坐标,int 宽度,int 高度)
	{g.drawRect(横坐标, 纵坐标, 宽度, 高度);}

	/*画3D矩形*/
	public final void 绘制3维矩形(Graphics g,int 横坐标,int 纵坐标,int 宽度,int 高度,boolean raised)
	{g.draw3DRect(横坐标, 纵坐标, 宽度, 高度, raised);}

	/*画文本*/
	public final void 绘制文本(Graphics g,String 文本,int 横坐标,int 纵坐标)
	{g.drawString(文本,横坐标, 纵坐标);}

	/*绘制图片，不可设置绘制宽高
	 * */
	public final boolean 绘制图像(Graphics g,Image 图像,int 横坐标,int 纵坐标,ImageObserver observer)
	{return g.drawImage(图像, 横坐标, 纵坐标, observer);}

	/*绘制图片，可设置背景颜色，不可设置绘制宽高
	 * */
	public final boolean 绘制图像(Graphics g,Image 图像,int 横坐标,int 纵坐标,Color 背景颜色,ImageObserver observer)
	{return g.drawImage(图像,横坐标, 纵坐标, 背景颜色, observer);}

	/*绘制图片，不可设置背景颜色，可设置绘制宽高
	 * */
	public final boolean 绘制图像(Graphics g,Image 图像,int 横坐标,int 纵坐标,int 宽度,int 高度,ImageObserver observer)
	{return g.drawImage(图像, 横坐标, 纵坐标, 宽度, 高度, observer);}

	/*绘制图片，可设置背景颜色，可设置宽高
	 * */
	public final boolean 绘制图像(Graphics g,Image 图像,int 横坐标,int 纵坐标,int 宽度,int 高度,
			Color 背景颜色,ImageObserver observer)
	{return g.drawImage(图像, 横坐标, 纵坐标, 宽度, 高度, 背景颜色, observer);}

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
	public final boolean 绘制图像(Graphics g,Image 图像,int 左边横坐标,int 顶端纵坐标,int 右边横坐标,int 底部纵坐标,
			int 源左边横坐标,int 源顶端纵坐标,int 源右边横坐标,int 源底部纵坐标,ImageObserver observer)
	{
		return g.drawImage(图像, 左边横坐标, 顶端纵坐标, 右边横坐标, 底部纵坐标,
				源左边横坐标, 源顶端纵坐标, 源右边横坐标, 源底部纵坐标, observer);
	}

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
	public final boolean 绘制图像(Graphics g,Image 图像,int 左边横坐标,int 顶端纵坐标,int 右边横坐标,int 底部纵坐标,
			int 源左边横坐标,int 源顶端纵坐标,int 源右边横坐标,int 源底部纵坐标,Color 背景颜色
			,ImageObserver observer)
	{
		return g.drawImage(图像, 左边横坐标, 顶端纵坐标, 右边横坐标, 底部纵坐标,
				源左边横坐标, 源顶端纵坐标, 源右边横坐标, 源底部纵坐标, 背景颜色, observer);
	}

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
	public final boolean 绘制图像(Graphics g,Image 图像,float 横坐标,float 纵坐标,int 宽度,int 高度,
			int 要裁剪图像的横坐标,int 要裁剪图像的纵坐标,int 要裁剪图像的宽度,int 要裁剪图像的高度,ImageObserver observer)
	{
		return this.绘制图像(g, 图像, (int)横坐标, (int)纵坐标, (int)横坐标 + 宽度, (int)纵坐标 + 高度,
				要裁剪图像的横坐标, 要裁剪图像的纵坐标, 要裁剪图像的横坐标 + 要裁剪图像的宽度, 要裁剪图像的纵坐标 + 要裁剪图像的高度, observer);
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
	public final boolean 绘制图像(Graphics g,Image 图像,float 横坐标,float 纵坐标,int 宽度,int 高度,
			int 要裁剪图像的横坐标,int 要裁剪图像的纵坐标,int 要裁剪图像的宽度,int 要裁剪图像的高度,
			Color 背景颜色,ImageObserver observer)
	{
		return this.绘制图像(g, 图像, (int)横坐标, (int)纵坐标, (int)横坐标 + 宽度, (int)纵坐标 + 高度,
				要裁剪图像的横坐标, 要裁剪图像的纵坐标, 要裁剪图像的横坐标 + 要裁剪图像的宽度, 要裁剪图像的纵坐标 + 要裁剪图像的高度,
				背景颜色, observer);
	}
}






