package fly.graphics;

import java.io.DataInputStream;
import java.io.FileInputStream;
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
		
	}
	
	/*写出地图*/
	public static void WriteMap()
	{
		
	}
	
	/*读取地图*/
	public static TileMap ReadMap(String path) throws IOException
	{
		DataInputStream reader = new DataInputStream(new FileInputStream (path));//打开地图文件
		
		byte[] map_information = new byte[10];
		
		reader.read(map_information, 0, 10);//读取地图数据
		
		TileMap map = new TileMap();
		
		map.width = (short) ((map_information[0] & 0xff) | (map_information[1] & 0xff));
		map.height = (short) ((map_information[2] & 0xff) | (map_information[3] & 0xff));
		map.tile_width = (short) ((map_information[4] & 0xff) | (map_information[5] & 0xff));
		map.tile_height = (short) ((map_information[6] & 0xff) | (map_information[7] & 0xff));
		
		map.tile_count = (short) ((map_information[8] & 0xff) | (map_information[9] & 0xff));
		map.tile_image_path = new String[map.tile_count];
		
		for(int i = 0;i < map.tile_count;i++)
		{
			/*读取图片路径字节数*/
			byte[] str_n = new byte[2];
			reader.read(str_n, 0, 2);
			
			short image_path_n = (short) ((str_n[0] & 0xff) | (str_n[1] & 0xff));
			
			/*读取图片路径*/
			byte[] image_path = new byte[image_path_n];
			reader.read(image_path, 0, image_path_n);
			
			map.tile_image_path[i] = new String(image_path);
		}
		
		map.data = new short[map.width * map.height];
		
		for(int i = 0;i < map.data.length;i++)
		{
			byte[] data = new byte[2];
			reader.read(data, 0, 2);
			
			map.data[i] = (short) ((data[0] & 0xff) | (data[1] & 0xff));
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






















