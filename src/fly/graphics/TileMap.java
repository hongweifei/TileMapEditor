package fly.graphics;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TileMap
{
	public short width;
	public short height;
    public short tile_width;
    public short tile_height;
    
    public short tile_count;
    public String[] tile_image_path;
    
    public short[] data;
	
	public TileMap()
	{
		width = 0;
		height = 0;
		tile_width = 0;
		tile_height = 0;
		tile_count = 0;
		tile_image_path = new String[0];
		data = null;
	}
	
	public TileMap(int width,int height,
			int tile_width,int tile_height,
			int tile_count,String[] tile_image_path,
			short[] data)
	{
		this.width = (short) width;
		this.height = (short) height;
		this.tile_width = (short) tile_width;
		this.tile_height = (short) tile_height;
		this.tile_count = (short) tile_count;
		this.tile_image_path = tile_image_path;
		this.data = data;
	}
	
	/*写出地图*/
	public static void WriteMap(String path,TileMap map) throws IOException
	{
		DataOutputStream writer = new DataOutputStream(new FileOutputStream (path));//创建地图文件
		
		writer.writeShort(map.width);
		writer.writeShort(map.height);
		writer.writeShort(map.tile_width);
		writer.writeShort(map.tile_height);
		
		writer.writeShort(map.tile_count);
		
		for(int i = 0;i < map.tile_image_path.length;i++)
		{
			short str_n = (short) map.tile_image_path[i].length();
			writer.writeShort(str_n);
			writer.writeChars(map.tile_image_path[i]);
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
	
	/*读取地图*/
	public static TileMap ReadMap(String path) throws IOException
	{
		DataInputStream reader = new DataInputStream(new FileInputStream (path));//打开地图文件
		
		TileMap map = new TileMap();
		
		map.width = reader.readShort();
		map.height = reader.readShort();
		map.tile_width = reader.readShort();
		map.tile_height = reader.readShort();
		
		map.tile_count = reader.readShort();
		map.tile_image_path = new String[map.tile_count];
		
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






















