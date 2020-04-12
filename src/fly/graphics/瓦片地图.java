package fly.graphics;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class 瓦片地图
{
	public short 宽度;
	public short 高度;
    public short 瓦片宽度;
    public short 瓦片高度;
    
    public short 瓦片图像数量;
    public String[] 瓦片图像路径;
    
    public short[] 数据;
	
	public 瓦片地图()
	{
		宽度 = 0;
		高度 = 0;
		瓦片宽度 = 0;
		瓦片高度 = 0;
		瓦片图像数量 = 0;
		瓦片图像路径 = new String[0];
		数据 = null;
	}
	
	public 瓦片地图(int 宽度,int 高度,
			int 瓦片宽度,int 瓦片高度,
			int 瓦片数量,String[] 瓦片图像路径,
			short[] 地图数据)
	{
		this.宽度 = (short) 宽度;
		this.高度 = (short) 高度;
		this.瓦片宽度 = (short) 瓦片宽度;
		this.瓦片高度 = (short) 瓦片高度;
		this.瓦片图像数量 = (short) 瓦片数量;
		this.瓦片图像路径 = 瓦片图像路径;
		this.数据 = 地图数据;
	}
	
	/*写出地图*/
	public static void 写出地图(String 路径,瓦片地图 地图) throws IOException
	{
		DataOutputStream writer = new DataOutputStream(new FileOutputStream (路径));//创建地图文件
		
		writer.writeShort(地图.宽度);
		writer.writeShort(地图.高度);
		writer.writeShort(地图.瓦片宽度);
		writer.writeShort(地图.瓦片高度);
		
		writer.writeShort(地图.瓦片图像数量);
		
		for(int i = 0;i < 地图.瓦片图像路径.length;i++)
		{
			short str_n = (short) 地图.瓦片图像路径[i].length();
			writer.writeShort(str_n);
			writer.writeChars(地图.瓦片图像路径[i]);
		}
		
		for(int i = 0;i < 地图.宽度 * 地图.高度;i++)
		{
			if(地图.数据 == null)
				writer.writeShort(0);
			else if(地图.数据.length < 地图.宽度 * 地图.高度)
			{
				if(i < 地图.数据.length)
					writer.writeShort(地图.数据[i]);
				else
					writer.writeShort(0);
			}
			else
				writer.writeShort(地图.数据[i]);
		}
	}
	
	/*读取地图*/
	public static 瓦片地图 读取地图(String 路径) throws IOException
	{
		DataInputStream reader = new DataInputStream(new FileInputStream (路径));//打开地图文件
		
		瓦片地图 地图 = new 瓦片地图();
		
		地图.宽度 = reader.readShort();
		地图.高度 = reader.readShort();
		地图.瓦片宽度 = reader.readShort();
		地图.瓦片高度 = reader.readShort();
		
		地图.瓦片图像数量 = reader.readShort();
		地图.瓦片图像路径 = new String[地图.瓦片图像数量];
		
		for(int i = 0;i < 地图.瓦片图像数量;i++)
		{
			/*读取图片路径字节数*/
			short image_path_n = reader.readShort();
			
			/*读取图片路径*/
			char[] image_path = new char[image_path_n];
			for(int j = 0;j < image_path_n;j++)
			{
				image_path[j] = reader.readChar();
			}
			
			地图.瓦片图像路径[i] = new String(image_path);
		}
		
		地图.数据 = new short[地图.宽度 * 地图.高度];
		
		for(int i = 0;i < 地图.数据.length;i++)
		{
			地图.数据[i] = reader.readShort();
		}
		
		
		return 地图;
	}
}
