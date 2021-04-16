package fly.graphics;


import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


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

    public ArrayList<Tile> tile;///< 瓦片

    public short[] data;///< 瓦片地图数据

	public FlyTileMap() throws IOException{this(0,0,null,null);}

	/**
	 * @param width 地图宽度
	 * @param height 地图高度
	 * @param tile_width 瓦片宽度
	 * @param tile_height 瓦片高度
	 * @param data 瓦片地图数据
	 * @param tile_image_path 瓦片地图内的图片路径
	 * @throws IOException 
	 * */
	public FlyTileMap(int width,int height,
			short[] data,
			String... tile_image_path) throws IOException
	{
		this.width = (short) width;
		this.height = (short) height;
		
		this.tile = new ArrayList<Tile>(); 

		if(tile_image_path != null)
		{
			for(int i = 0;i < tile_image_path.length;i++)
			{
				this.tile.add(new Tile(tile_image_path[i]));
			}
		}

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
					Tile tile = this.tile.get(this.data[n] - 1);
					renderer.DrawImage(g, tile.img, x + j * width,
								y + i * height, tile.tile_width * width, tile.tile_height * height,null);
				}

				renderer.Draw3DRect(g, x + j * width, y + i * height,
						width, height,false);
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
		
		for(int i = 0;i < this.height;i++)
		{
			for(int j = 0;j < this.width;j++)
			{
				int n = j + i * this.width;
				if(this.data[n] > 0)
				{
					Tile tile = this.tile.get(this.data[n] - 1);
					
					renderer.绘制图像(g, tile.img, x + j * width,
								y + i * height, tile.tile_width * width, tile.tile_height * height,null);
				}

				renderer.绘制3维矩形(g, x + j * width, y + i * height,
						width, height,false);
			}
		}
	}

	

	/**
	 * 添加Tile
	 *
	 * @param t_w tile_width
	 * @param t_h tile_height
	 * @param path 图片路径
	 * */
	public void AddTile(short t_w,short t_h,String path)
	{
		try {
			this.tile.add(new Tile(t_w,t_h,path));
			}
		catch (IOException e) {e.printStackTrace();}
	}
	
	
	/**
	 * 添加Tile
	 *
	 * @param path 图片路径
	 * */
	public void AddTile(String path){this.AddTile((short)1, (short)1, path);}


	/**
	 * 移除Tile
	 *
	 * @index 图片索引
	 * */
	public void RemoveTile(int index)
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

		
	}






	/**
	 * 写出地图
	 *
	 * @param path 写出的地图的路径
	 * @param map 要写出的地图
	 * */
	public static void WriteMap(String path,FlyTileMap map) throws IOException
	{
		@SuppressWarnings("resource")
		DataOutputStream writer = new DataOutputStream(new FileOutputStream (path));//创建地图文件

		writer.writeShort(map.width);
		writer.writeShort(map.height);

		writer.writeShort(map.tile.size());

		if(map.tile.size() > 0)
		{
			
			for(int i = 0;i < map.tile.size();i++)
			{
				writer.writeShort(map.tile.get(i).tile_width);
				writer.writeShort(map.tile.get(i).tile_height);
				
				short str_n = (short) map.tile.get(i).image_path.length();
				writer.writeShort(str_n);
				writer.writeChars(map.tile.get(i).image_path);
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
		@SuppressWarnings("resource")
		DataInputStream reader = new DataInputStream(new FileInputStream (path));//打开地图文件

		FlyTileMap map = new FlyTileMap();
		short tile_count = 0;

		map.width = reader.readShort();
		map.height = reader.readShort();

		tile_count = reader.readShort();


		for(int i = 0;i < tile_count;i++)
		{
			short tile_width = reader.readShort();
			short tile_height = reader.readShort();
			
			/*读取图片路径字节数*/
			short image_path_n = reader.readShort();

			/*读取图片路径*/
			char[] image_path = new char[image_path_n];
			for(int j = 0;j < image_path_n;j++)
			{
				image_path[j] = reader.readChar();
			}
			
			map.tile.add(new Tile((short)tile_width,(short)tile_height,new String(image_path)));
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
    short tile.length;
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

void WriteTileMap(const char *path,TileMapInformation map_information,TileMapData map_data,short tile.length,...);
TileMap ReadTileMap(const char *path);

*/


/*

void WriteTileMap(const char *path,TileMapInformation map_information,TileMapData map_data,short tile.length,...)
{
    short *image_path_str_n = (short*)calloc(tile.length,sizeof(short));//图片路径字符串大小
    char *image_path[tile.length];//图片路径

    if(tile.length > 0)
    {
        va_list image_path_list;
        va_start(image_path_list,tile.length);

        int i;
        for (i = 0; i < tile.length; i++)
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

    fwrite(&tile.length,1,sizeof(tile.length),fp);//写出瓦片数量

    int i;
    for (i = 0; i < tile.length; i++)
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

    fread(&tile_sets,1,sizeof(short),fp);//读入tile.length

    if(tile_sets.tile.length > 0)
    {
        tile_sets.tile_image_path = (char**)calloc(tile_sets.tile.length,sizeof(char*));

        int i;
        for (i = 0; i < tile_sets.tile.length; i++)
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






















