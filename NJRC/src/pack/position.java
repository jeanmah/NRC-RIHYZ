// transferred to Navigation pack
package pack;

public class position {
	int x;
	int y;
	int direction;
	
	public position(int X, int Y, int DIRECTION)
	{
		this.x = X;
		this.y = Y;
		this.direction = DIRECTION;
	}
	
	public position()
	{
		return;
	}
	
//	public position (position pos)
//	{
//		this.x = pos.x;
//		this.y = pos.y;
//		this.direction = pos.direct	on;
//	}
	
	public void initialize()
	{
		this.x = -1;
		this.y= -1;
		this.direction = -1;
	}
}
