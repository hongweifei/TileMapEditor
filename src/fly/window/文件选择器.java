package fly.window;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import fly.window.widget.FlyWidget;
import fly.window.widget.组件;

public class 文件选择器
{
	private JFileChooser file_chooser;//文件选择器
	private FileFilter file_filter;//文件过滤器

	public static final int 确定选项 = JFileChooser.APPROVE_OPTION;
	public static final int 取消选项 = JFileChooser.CANCEL_OPTION;
	public static final int 错误选项 = JFileChooser.ERROR_OPTION;

	public 文件选择器()
	{
		file_chooser = new JFileChooser();
		//file_filter = new FileNameExtensionFilter(".*",".*");
		//file_chooser.setFileFilter(file_filter);
	}


	/*弹出带有自定义批准按钮的自定义文件选择器对话框。
	 * button_text 批准按钮的文本
	 *
	 * 弹出时文件选择器的返回状态:
	 * FlyFileChooser.APPROVE_OPTION
	 * FlyFileChooser.CANCEL_OPTION
	 * FlyFileChooser.ERROR_OPTION 如果发生错误或对话框被关闭
	 * */
	public int 弹出对话框(String 按钮文本)
	{return this.file_chooser.showDialog(null, 按钮文本);}

	/*弹出“打开文件”文件选择器对话框*/
	public int 弹出打开文件对话框()
	{return this.file_chooser.showOpenDialog(null);}

	/*弹出“保存文件”文件选择器对话框。*/
	public int 弹出保存文件对话框()
	{return this.file_chooser.showSaveDialog(null);}


	/*弹出带有自定义批准按钮的自定义文件选择器对话框。
	 * widget 打开对话框所依赖的框架和放置对话框时应考虑其外观位置的组件。
	 * button_text 批准按钮的文本
	 *
	 * 弹出时文件选择器的返回状态:
	 * FlyFileChooser.APPROVE_OPTION
	 * FlyFileChooser.CANCEL_OPTION
	 * FlyFileChooser.ERROR_OPTION 如果发生错误或对话框被关闭
	 * */
	public int 弹出对话框(Window window,String 按钮文本)
	{return this.file_chooser.showDialog(window.GetFrame(), 按钮文本);}
	public int 弹出对话框(窗口 window,String 按钮文本)
	{return this.file_chooser.showDialog(window.获取窗口(), 按钮文本);}
	public int 弹出对话框(FlyWidget widget,String 按钮文本)
	{return this.file_chooser.showDialog(widget.Get(), 按钮文本);}
	public int 弹出对话框(组件 widget,String 按钮文本)
	{return this.file_chooser.showDialog(widget.获取(), 按钮文本);}

	/*弹出“打开文件”文件选择器对话框
	 * widget 打开对话框所依赖的框架和放置对话框时应考虑其外观位置的组件。
	 * */
	public int 弹出打开文件对话框(Window window)
	{return this.file_chooser.showOpenDialog(window.GetFrame());}
	public int 弹出打开文件对话框(窗口 window)
	{return this.file_chooser.showOpenDialog(window.获取窗口());}
	public int 弹出打开文件对话框(FlyWidget widget)
	{return this.file_chooser.showOpenDialog(widget.Get());}
	public int 弹出打开文件对话框(组件 widget)
	{return this.file_chooser.showOpenDialog(widget.获取());}

	/*弹出“保存文件”文件选择器对话框。
	 * widget 打开对话框所依赖的框架和放置对话框时应考虑其外观位置的组件。
	 * */
	public int 弹出保存文件对话框(Window window)
	{return this.file_chooser.showSaveDialog(window.GetFrame());}
	public int 弹出保存文件对话框(窗口 window)
	{return this.file_chooser.showSaveDialog(window.获取窗口());}
	public int 弹出保存文件对话框(FlyWidget widget)
	{return this.file_chooser.showSaveDialog(widget.Get());}
	public int 弹出保存文件对话框(组件 widget)
	{return this.file_chooser.showSaveDialog(widget.获取());}


	/*获取选中的文件*/
	public File 获取选中文件(){return this.file_chooser.getSelectedFile();}
	public File[] 获取选中的多个文件(){return this.file_chooser.getSelectedFiles();}

	/*获取选中文件的文件名*/
	public String 获取选中文件的文件名(){return this.file_chooser.getSelectedFile().getName();}
	public String[] 获取选中的多个文件的文件名()
	{
		final long 文件数量 = this.file_chooser.getSelectedFiles().length;
		String[] 文件名 = new String[(int) 文件数量];

		for(int i = 0;i < 文件数量;i++)
		{
			文件名[i] = this.file_chooser.getSelectedFiles()[i].getName();
		}

		return 文件名;
	}

	/*获取选中文件的路径*/
	public String 获取选中文件的路径(){return this.file_chooser.getSelectedFile().getPath();}
	public String[] 获取选中的多个文件的路径()
	{
		final long 文件数量 = this.file_chooser.getSelectedFiles().length;
		String[] 路径 = new String[(int) 文件数量];

		for(int i = 0;i < 文件数量;i++)
		{
			路径[i] = this.file_chooser.getSelectedFiles()[i].getPath();
		}

		return 路径;
	}

	/**
	 * 设置过滤器,支持文件的格式 *，不用 *.*
	 *
	 * */
	public void 设置文件过滤器(FileFilter 文件过滤器)
	{
		this.file_filter = 文件过滤器;
		this.file_chooser.setFileFilter(this.file_filter);
	}

	/**
	 * 设置过滤器,支持文件的格式 *，不用 *.*
	 *
	 * */
	public void 设置文件过滤器(String 过滤器的文本说明, String... 接受的文件扩展名)
	{
		file_filter = new FileNameExtensionFilter(过滤器的文本说明,接受的文件扩展名);
		file_chooser.setFileFilter(file_filter);
	}
}






