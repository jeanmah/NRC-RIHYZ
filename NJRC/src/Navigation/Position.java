package Navigation;

public class Position {
	int x;
	int y;
	int direction;
	
	public Position(int X, int Y, int DIRECTION)
	{
		this.x = X;
		this.y = Y;
		this.direction = DIRECTION;
	}
	
	public Position()
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
