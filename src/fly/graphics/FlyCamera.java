package fly.graphics;

/**
 * @file FlyCamera.java
 * @author Fly
 */

/**
 * 摄像机类
 * */
public class FlyCamera
{
	public int look_at_x;///<
	public int look_at_y;///<

	public FlyCamera(){this(0,0);}
	public FlyCamera(int look_at_x,int look_at_y){this.look_at_x = look_at_x;this.look_at_y = look_at_y;}
}
