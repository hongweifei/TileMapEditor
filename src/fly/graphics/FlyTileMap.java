package fly.graphics;


import java.awt.Graphics;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @file FlyTileMap.java
 * @author Fly
 */



/**
 * 瓦片地图
 * */
public class FlyTileMap
{
	public short width;///< 地图宽度
	public short height;///< 地图高度
    public short tile_width;///< 瓦片宽度
    public short tile_height;///< 瓦片高度

    public short tile_count;///< 瓦片地图的图像数量
    public String[] tile_image_path;///< 瓦片地图内的图片路径

    public short[] data;///< 瓦片地图数据

    private Image[] img = null;

	public FlyTileMap(){this(0,0,0,0,null,null);}

	/**
	 * @param width 地图宽度
	 * @param height 地图高度
	 * @param tile_width 瓦片宽度
	 * @param tile_height 瓦片高度
	 * @param data 瓦片地图数据
	 * @param tile_image_path 瓦片地图内的图片路径
	 * */
	public FlyTileMap(int width,int height,
			int tile_width,int tile_height,
			short[] data,
			String... tile_image_path)
	{
		this.width = (short) width;
		this.height = (short) height;
		this.tile_width = (short) tile_width;
		this.tile_height = (short) tile_height;

		if(tile_image_path != null)
		{
			this.tile_count = (short) tile_image_path.length;
			this.img = new Image[this.tile_count];
			for(int i = 0;i < this.tile_count;i++)
			{
				try {img[i] = ImageIO.read(new File(this.tile_image_path[i]));}
				catch (IOException e) {e.printStackTrace();}
			}
		}
		else
			this.tile_count = 0;

		this.tile_image_path = tile_image_path;
		this.data = data;
	}

	/**
	 * 渲染地图
	 * @param width 渲染宽度
	 * @param height 渲染高度
	 * */
	public void Render(FlyRenderer renderer,Graphics g,int width,int height)
	{this.Render(renderer, g, 0, 0, width, height);}


	/**
	 * 渲染地图
	 * @param width 渲染宽度
	 * @param height 渲染高度
	 * */
	public void Render(渲染器 renderer,Graphics g,int width,int height)
	{this.Render(renderer, g,0,0, width, height);}

	/**
	 * 渲染地图
	 * @param x 地图左上角横坐标
	 * @param y 地图左上角纵坐标
	 * @param width 渲染宽度
	 * @param height 渲染高度
	 * */
	public void Render(FlyRenderer renderer,Graphics g,int x,int y,int width,int height)
	{
		for(int i = 0;i < this.height;i++)
		{
			for(int j = 0;j < this.width;j++)
			{
				int n = j + i * this.width;
				if(this.data[n] > 0)
				{
					renderer.DrawImage(g, img[this.data[n] - 1], x + j * width,
								y + i * height, width, height,null);
				}

				renderer.DrawRect(g, x + j * width, y + i * height,
						width, height);
			}
		}
	}


	/**
	 * 渲染地图
	 * @param x 地图左上角横坐标
	 * @param y 地图左上角纵坐标
	 * @param width 渲染宽度
	 * @param height 渲染高度
	 * */
	public void Render(渲染器 renderer,Graphics g,int x,int y,int width,int height)
	{
		final Image[] img = new Image[this.tile_count];
		for(int i = 0;i < this.tile_count;i++)
		{
			try {img[i] = ImageIO.read(new File(this.tile_image_path[i]));}
			catch (IOException e) {e.printStackTrace();}
		}

		for(int i = 0;i < this.height;i++)
		{
			for(int j = 0;j < this.width;j++)
			{
				int n = j + i * this.width;
				if(this.data[n] > 0)
				{
					renderer.绘制图像(g, img[this.data[n] - 1], x + j * width,
								y + i * height, width, height,null);
				}

				renderer.绘制矩形(g, x + j * width, y + i * height,
						width, height);
			}
		}
	}



	/**
	 * 添加图片
	 *
	 * @param path 图片路径
	 * */
	public void AddImage(String path)
	{
		tile_count++;

		String[] image_path = tile_image_path;
		tile_image_path = new String[tile_count];

		System.arraycopy(image_path,0,tile_image_path,0,image_path.length);

		tile_image_path[tile_count - 1] = path;


		this.img = new Image[this.tile_count];

		for(int i = 0;i < this.tile_count;i++)
		{
			try {img[i] = ImageIO.read(new File(this.tile_image_path[i]));}
			catch (IOException e) {e.printStackTrace();}
		}

	}




	/**
	 * 移除图片
	 *
	 * @index 图片索引
	 * */
	public void RemoveImage(int index)
	{
		for(int i = 0;i < height;i++)
		{
			for(int j = 0;j < width;j++)
			{
				int n = j + i * width;

				if(data[n] == index + 1)
					data[n] = 0;
				else if(data[n] > index + 1)
					data[n]--;
			}
		}

		tile_count--;

		String[] image_path = tile_image_path;
		tile_image_path = new String[tile_count];

		int j = 0;
		for(int i = 0;i < tile_count;i++)
		{
			if(i == index)
				j++;

			tile_image_path[i] = image_path[j];

			j++;
		}

		this.img = new Image[this.tile_count];
		for(int i = 0;i < this.tile_count;i++)
		{
			try {img[i] = ImageIO.read(new File(this.tile_image_path[i]));}
			catch (IOException e) {e.printStackTrace();}
		}
	}






	/**
	 * 写出地图
	 *
	 * @param path 写出的地图的路径
	 * @param map 要写出的地图
	 * */
	public static void WriteMap(String path,FlyTileMap map) throws IOException
	{
		DataOutputStream writer = new DataOutputStream(new FileOutputStream (path));//创建地图文件

		writer.writeShort(map.width);
		writer.writeShort(map.height);
		writer.writeShort(map.tile_width);
		writer.writeShort(map.tile_height);

		writer.writeShort(map.tile_count);

		if(map.tile_count > 0)
		{
			for(int i = 0;i < map.tile_image_path.length;i++)
			{
				short str_n = (short) map.tile_image_path[i].length();
				writer.writeShort(str_n);
				writer.writeChars(map.tile_image_path[i]);
			}
		}


		for(int i = 0;i < map.width * map.height;i++)
		{
			if(map.data == null)
				writer.writeShort(0);
			else if(map.data.length < map.width * map.height)
			{
				if(i < map.data.length)
					writer.writeShort(map.data[i]);
				else
					writer.writeShort(0);
			}
			else
				writer.writeShort(map.data[i]);
		}
	}

	/**
	 * 读取地图
	 *
	 * @param path 要读取的地图的路径
	 *
	 * @return 返回读到的地图
	 * */
	public static FlyTileMap ReadMap(String path) throws IOException
	{
		DataInputStream reader = new DataInputStream(new FileInputStream (path));//打开地图文件

		FlyTileMap map = new FlyTileMap();

		map.width = reader.readShort();
		map.height = reader.readShort();
		map.tile_width = reader.readShort();
		map.tile_height = reader.readShort();

		map.tile_count = reader.readShort();
		map.tile_image_path = new String[map.tile_count];


		map.img = new Image[map.tile_count];

		for(int i = 0;i < map.tile_count;i++)
		{
			/*读取图片路径字节数*/
			short image_path_n = reader.readShort();

			/*读取图片路径*/
			char[] image_path = new char[image_path_n];
			for(int j = 0;j < image_path_n;j++)
			{
				image_path[j] = reader.readChar();
			}

			map.tile_image_path[i] = new String(image_path);

			try {map.img[i] = ImageIO.read(new File(map.tile_image_path[i]));}
			catch (IOException e) {e.printStackTrace();}
		}

		map.data = new short[map.width * map.height];

		for(int i = 0;i < map.data.length;i++)
		{
			map.data[i] = reader.readShort();
		}


		return map;
	}

}



/*

typedef struct
{
    short width;//地图宽，16bit，2byte，2字节
    short height;//地图高，16bit，2byte，2字节
    short tile_width;//瓦片宽，2字节
    short tile_height;//瓦片高，2字节
}TileMapInformation;

typedef struct
{
    short tile_count;
    char **tile_image_path;
}TileMapSets;

typedef struct
{
    short *data;
}TileMapData;

typedef struct
{
    TileMapInformation information;
    TileMapSets sets;
    TileMapData map_data;
}TileMap;

void WriteTileMap(const char *path,TileMapInformation map_information,TileMapData map_data,short tile_count,...);
TileMap ReadTileMap(const char *path);

*/


/*

void WriteTileMap(const char *path,TileMapInformation map_information,TileMapData map_data,short tile_count,...)
{
    short *image_path_str_n = (short*)calloc(tile_count,sizeof(short));//图片路径字符串大小
    char *image_path[tile_count];//图片路径

    if(tile_count > 0)
    {
        va_list image_path_list;
        va_start(image_path_list,tile_count);

        int i;
        for (i = 0; i < tile_count; i++)
        {
            image_path[i] = va_arg(image_path_list,char*);//取出图片路径
            image_path_str_n[i] = strlen(image_path[i]);//计算图片路径字符串大小
        }

        va_end(image_path_list);
    }

    //开始写出地图
    FILE *fp = fopen(path,"w");

    fseek(fp,0L,SEEK_SET);
    fwrite(&map_information,1,sizeof(TileMapInformation),fp);//写出地图信息

    fwrite(&tile_count,1,sizeof(tile_count),fp);//写出瓦片数量

    int i;
    for (i = 0; i < tile_count; i++)
    {
        fwrite(&image_path_char_n[i],1,sizeof(short),fp);//写出瓦片图片路径字符
        fwrite(&image_path[i],1,sizeof(char) * image_path_char_n[i],fp);
    }

    fwrite(map_data.data,sizeof(short),map_information.width * map_information.height,fp);

    fclose(fp);
}

TileMap ReadTileMap(const char *path)
{
    FILE *fp = fopen(path,"r");

    TileMapInformation tile_map_information;
    TileMapSets tile_sets;
    TileMapData tile_map_data;

    TileMap tile_map;

    fseek(fp,0L,SEEK_SET);//跳至第0字节处
    fread(&tile_map_information,1,sizeof(TileMapInformation),fp);//读入tile_map_information

    tile_map_data.data = (short*)malloc(tile_map_information.width * tile_map_information.height * sizeof(short));

    fread(&tile_sets,1,sizeof(short),fp);//读入tile_count

    if(tile_sets.tile_count > 0)
    {
        tile_sets.tile_image_path = (char**)calloc(tile_sets.tile_count,sizeof(char*));

        int i;
        for (i = 0; i < tile_sets.tile_count; i++)
        {
            short image_path_str_n;
            fread(&image_path_char_n,1,sizeof(short),fp);

            tile_sets.tile_image_path[i] = (char*)malloc(sizeof(char) * image_path_char_n);
            fread(&tile_sets.tile_image_path[i],sizeof(char),image_path_str_n,fp);
        }
    }

    int i;
    for (i = 0; i < tile_map_information.width * tile_map_information.height; i++)
    {
        fread(&tile_map_data.data[i],1,sizeof(short),fp);
    }

    fclose(fp);

    tile_map.information = tile_map_information;
    tile_map.sets = tile_sets;
    tile_map.map_data = tile_map_data;

    return tile_map;
}


*/






















