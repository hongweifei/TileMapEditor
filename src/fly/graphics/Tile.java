package fly.graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile
{
	public short tile_width;
	public short tile_height;
	public String image_path;
	public Image img = null;
	
	public Tile(String path) throws IOException{this((short)1,(short)1,path);}
	public Tile(short width,short height,String path) throws IOException
	{
		this.tile_width = width;
		this.tile_height = height;
		this.image_path = path;
		
		if (this.image_path != "" || this.image_path != null)
		{
			this.img = ImageIO.read(new File(this.image_path));
		}
	}
}




