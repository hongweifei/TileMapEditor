package fly.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;


public class FlyRenderer2D extends FlyRenderer
{
	private Graphics2D g = null;

	public FlyRenderer2D()
	{
		super();
	}

	public FlyRenderer2D(Graphics2D g)
	{
		this();
		this.g = g;
	}

	/**获取Graphics2D*/
	public Graphics2D GetGraphics2D()
	{
		return this.g;
	}


	/**
	 * 设置paint
	 *
	 * 设置上下文的Paint属性。
	 * 使用空的绘制对象调用此方法对该图形的当前绘制属性没有任何影响2d
	 *
	 * @param paint 在渲染过程中用于生成颜色的绘制对象
	 * */
	public final void SetPaint(Paint paint)
	{
		g.setPaint(paint);
	}

	/**获取paint*/
	public Paint GetPaint()
	{
		return g.getPaint();
	}


	/**
	 * 设置stroke
	 *
	 * @param s 在渲染过程中用于绘制形状的笔划对象
	 * */
	public final void SetStroke(Stroke s)
	{
		g.setStroke(s);
	}

	/**
	 * 获取stroke
	 * */
	public Stroke GetStroke()
	{
		return g.getStroke();
	}


	/**
	 * 设置clip
	 *
	 * 将当前剪辑区域设置为任意剪辑形状。
	 * 不是所有实现形状接口的对象都可以用于设置剪辑。
	 * 唯一保证支持的形状对象是通过GetClip方法和矩形对象获得的形状对象。
	 * 此方法设置用户剪辑，它独立于与设备边界和窗口可见性相关联的剪辑。
	 *
	 * @param clip 用于设置剪辑的形状
	 * */
	public final void SetClip(Shape clip)
	{
		g.setClip(clip);
	}

	/**
	 * 设置clip
	 *
	 * 将当前剪辑设置为由给定坐标指定的矩形。
	 * 此方法设置用户剪辑，它独立于与设备边界和窗口可见性相关联的剪辑。
	 * 渲染操作在剪切区域之外没有效果。
	 *
	 * @param x 新剪辑矩形的x坐标。
	 * @param y 新剪辑矩形的y坐标。
	 * @param width 新剪辑矩形的宽度。
	 * @param height 新剪辑矩形的高度。
	 * */
	public final void SetClip(int x, int y, int width, int height)
	{
		g.setClip(x, y, width, height);
	}

	/**
	 * 获取clip
	 * */
	public Shape GetClip()
	{
		return g.getClip();
	}


	/**
	 * 旋转
	 *
	 * @param theta 旋转角度
	 * */
	public final void Ratote(double theta)
	{
		g.rotate(theta);
	}

	/**
	 * 旋转
	 *
	 * @param theta 旋转角度
	 * @param x 旋转原点的x坐标
	 * @param y 旋转原点的y坐标
	 * */
	public final void Ratote(double theta,double x,double y)
	{
		g.rotate(theta, x, y);
	}


	/**
	 * @param sx 相对于以前的渲染操作，后续渲染操作中X坐标操作相乘的量
	 * @param sy 相对于以前的渲染操作，后续渲染操作中Y坐标操作相乘的量
	 * */
	public final void Scale(double sx,double sy)
	{
		g.scale(sx, sy);
	}


	/**
	 *
	 * @param shx 坐标随Y坐标在正X轴方向移动的乘数
	 * @param shy 坐标随X坐标在正X轴方向移动的乘数
	 * */
	public final void shear(double shx,double shy)
	{
		g.shear(shx, shy);
	}


	/**
	 * 将上下文的原点转换为当前坐标系中的点（x，y）。
	 * 修改上下文，使其新原点对应于Graphics2D上下文以前坐标系中的点（x，y）。
	 * 此图形上下文上后续渲染操作中使用的所有坐标都与此新原点相关
	 *
	 * @param x 指定的x坐标
	 * @param y 指定的y坐标
	 * */
	public final void Translate(int x,int y)
	{
		g.translate(x, y);
	}


	/**
	 *
	 *
	 * @param tx 沿x轴平移的距离
	 * @param ty 沿y轴平移的距离
	 * */
	public final void Translate(double tx,double ty)
	{
		g.translate(tx, ty);
	}


	/**
	 * 根据上次指定的第一次应用的规则，使用此图形2d中的转换组合仿射转换对象。
	 * 如果当前转换是Cx，则与Tx组合的结果是一个新的转换Cx'。Cx'成为此图形2d的当前转换。
	 * 使用更新的转换Cx'转换点p相当于首先使用Tx转换p，然后使用原始转换Cx转换结果。
	 * 换句话说，Cx’（p）=Cx（Tx（p））。
	 * 如有必要，将复制Tx，因此对Tx的进一步修改不会影响渲染。
	 *
	 * @param Tx 要与当前变换组合的仿射变换对象
	 * */
	public final void Transform(AffineTransform Tx)
	{
		g.transform(Tx);
	}



	/**
	 * 画线
	 *
	 * @param x1 绘制的第一个点的横坐标
	 * @param y1 绘制的第一个点的纵坐标
	 * @param x2 绘制的第二个点的横坐标
	 * @param y2 绘制的第二个点的纵坐标
	 * */
	public final void DrawLine(int x1,int y1,int x2,int y2)
	{g.drawLine(x1, y1, x2, y2);}


	/**
	 * 画矩形
	 *
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * */
	public final void DrawRect(int x,int y,int width,int height)
	{g.drawRect(x, y, width, height);}

	/**
	 * 画3D矩形
	 *
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * @param raised 用于确定矩形是在曲面上方凸起还是在曲面中凹陷
	 * */
	public final void Draw3DRect(int x,int y,int width,int height,boolean raised)
	{g.draw3DRect(x, y, width, height, raised);}

	/**
	 * 画3D fill矩形
	 *
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * @param raised 用于确定矩形是在曲面上方凸起还是在曲面中凹陷
	 * */
	public final void DrawFill3DRect(int x,int y,int width,int height,boolean raised)
	{
		this.g.fill3DRect(x, y, width, height, raised);
	}


	/**
	 * 画文本
	 *
	 * @param text 要绘制的文本
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * */
	public final void DrawText(String text,int x,int y)
	{g.drawString(text,x,y);}



	/**
	 * 绘制fill形状
	 * */
	public final void DrawFillShape(Shape s)
	{
		g.fill(s);
	}


	/**
	 * 绘制图片，不可设置绘制宽高
	 *
	 * @param img 要绘制的图像
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Image img,int x,int y,ImageObserver observer)
	{return g.drawImage(img, x, y, observer);}

	/**
	 * 绘制图片，可设置背景颜色，不可设置绘制宽高
	 *
	 * @param img 要绘制的图像
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param back_ground_color 背景颜色
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Image img,int x,int y,Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, x, y, back_ground_color, observer);}

	/**
	 * 绘制图片，不可设置背景颜色，可设置绘制宽高
	 *
	 * @param img 要绘制的图像
	 * @param x 绘制横坐标
	 * @param y 绘制纵坐标
	 * @param width 绘制宽度
	 * @param height 绘制高度
	 * @param observer
	 *
	 * @return
	 * */
	public final boolean DrawImage(Image img,int x,int y,int width,int height,ImageObserver observer)
	{return g.drawImage(img, x, y, width, height, observer);}

	/**
	 * 绘制图片，可设置背景颜色，可设置宽高
	 *
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
	public final boolean DrawImage(Image img,int x,int y,int width,int height,
			Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, x, y, width, height, back_ground_color, observer);}

	/**
	 * 绘制裁剪后的图片图片，不可设置背景颜色，可设置绘制宽高
	 *
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
	public final boolean DrawImage(Image img,int dx1,int dy1,int dx2,int dy2,
			int sx1,int sy1,int sx2,int sy2,ImageObserver observer)
	{return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);}

	/**
	 * 绘制裁剪后的图片图片，可设置背景颜色，可设置绘制宽高
	 *
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
	public final boolean DrawImage(Image img,int dx1,int dy1,int dx2,int dy2,
			int sx1,int sy1,int sx2,int sy2,Color back_ground_color,ImageObserver observer)
	{return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, back_ground_color, observer);}

	/**
	 * 绘制裁剪后的图片图片，不可设置背景颜色，可设置绘制宽高
	 *
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
	public final boolean DrawImage(Image img,float x,float y,int width,int height,
			int sx,int sy,int swidth,int sheight,ImageObserver observer)
	{
		return this.DrawImage(g, img, (int)x, (int)y, (int)x + width, (int)y + height,
				sx, sy, sx + swidth, sy + sheight, observer);
	}

	/**
	 * 绘制裁剪后的图片图片，可设置背景颜色，可设置绘制宽高
	 *
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
	public final boolean DrawImage(Image img,float x,float y,int width,int height,
			int sx,int sy,int swidth,int sheight,Color back_ground_color,ImageObserver observer)
	{
		return this.DrawImage(g, img, (int)x, (int)y, (int)x + width, (int)y + height,
				sx, sy, sx + swidth, sy + sheight, back_ground_color, observer);
	}


}













