package Navigation;


public class PathFinder {
	static boolean[][][] visited= new boolean[4][4][4];  // to mark the visited points in BFS
	static Position[] queue = new Position[100];  // the queue for BFS
	static int[] previous = new int[100]; // used to generate the path
    static int[] operation = new int [100];
	static  int[] step_number = new int[100];
	static final int LEFT=1, RIGHT=2, FORWARD=3,BACK=4,REACH_DESTINATION=5;
			
	public static Position[][] access_Position = new Position[10][4]; // to save the access Position to initiate the BFS
    public static Position[] starting_Position = new Position[2];
	static int head,tail; // for the queue
	static final int ERR=0,BLACK=1,BLUE=2,GREEN=3,YELLOW=4,RED=5,WHITE=6; // refer to the colors
	
	static void initialize()
	{
		for (int i=0; i<4; i++)
			for (int j=0; j<4; j++)
				for (int k=0; k<4; k++)
					visited[i][j][k] = false;  // mark all the points as unvisited
		
		// block access to points occupied by the blocks 	
		for (int i=0; i<4; i++) // enumerate Positions
		{
			visited[2][0][i] = true;
			visited[0][2][i] = true;
			visited[2][2][i] = true;
			visited[4][2][i] = true;
			visited[2][4][i] = true;
		}
		
		head = 0;
		tail = 1; // empty the queue
		
		// type in the possible access Positions
	}
	
	static boolean compare_Positions( Position Position1, Position Position2)
	{
		if ((Position1.x == Position2.x) && (Position1.y == Position2.y) && (Position1.direction == Position2.direction))
			return true;
		else
			return false;	
	}
	
	static boolean check_visited (Position new_Position)
	{
		if (visited[new_Position.x][new_Position.y][new_Position.direction]) return true;
		else return false;
	}
	
	
	static boolean Position_transfer(Position end_Position)
	{
		Position current = queue[head];
		Position new_Position;
		
		// consider change direction
		new_Position = new Position(current.x,current.y,(current.direction+1)%4);
		if (! check_visited(new_Position))
		{
			queue[tail] = new_Position;
			previous[tail] = head;
			step_number[tail] = step_number[head]+1;
			operation[tail] = LEFT;
			visited[new_Position.x][new_Position.y][new_Position.direction] = true;
			if (compare_Positions(new_Position, end_Position)) return true; // signal the end of the BFS, the target Position is now at the end of the queue
			tail++;
		}
		
		new_Position = new Position(current.x,current.y,(current.direction+3)%4);
		if (! check_visited(new_Position))
		{
			queue[tail] = new_Position;
			previous[tail] = head;
			step_number[tail] = step_number[head]+1;
			operation[tail] = RIGHT;
			visited[new_Position.x][new_Position.y][new_Position.direction] = true;
			if (compare_Positions(new_Position, end_Position)) return true; // signal the end of the BFS, the target Position is now at the end of the queue
			tail++;
		}	
		
		// consider walking straight forward
		if ((current.direction==0) && (current.x != 4)) 
			new_Position = new Position(current.x+1, current.y,current.direction);
		if ((current.direction==1) && (current.y != 4)) 
			new_Position = new Position(current.x, current.y+1,current.direction);
		if ((current.direction==2) && (current.x != 0)) 
			new_Position = new Position(current.x-1, current.y,current.direction);
		if ((current.direction==3) && (current.y != 0)) 
			new_Position = new Position(current.x, current.y-1,current.direction);
		
		if (! check_visited(new_Position))
		{
			queue[tail] = new_Position;
			previous[tail] = head;
			step_number[tail] = step_number[head]+1;
			operation[tail] = FORWARD;
			visited[new_Position.x][new_Position.y][new_Position.direction] = true;
			if (compare_Positions(new_Position, end_Position)) return true; // signal the end of the BFS, the target Position is now at the end of the queue
			tail++;
		}
		
		//consider walking back aka reverse
		if ((current.direction==0) && (current.x != 0)) 
			new_Position = new Position(current.x-1, current.y,current.direction);
		if ((current.direction==1) && (current.y != 0)) 
			new_Position = new Position(current.x, current.y-1,current.direction);
		if ((current.direction==2) && (current.x != 4)) 
			new_Position = new Position(current.x+1, current.y,current.direction);
		if ((current.direction==3) && (current.y != 4)) 
			new_Position = new Position(current.x, current.y+1,current.direction);
		
		if (! check_visited(new_Position))
		{
			queue[tail] = new_Position;
			previous[tail] = head;
			step_number[tail] = step_number[head]+1;
			operation[tail] = BACK;
			visited[new_Position.x][new_Position.y][new_Position.direction] = true;
			if (compare_Positions(new_Position, end_Position)) return true; // signal the end of the BFS, the target Position is now at the end of the queue
			tail++;
		}
		
		return false; // target not reached, and BFS continues
	}
	
	static int[] BFS (Position start_Position, Position end_Position)
	{
		initialize(); // clean up
		queue[0]= start_Position;
		step_number[0]=0;
		
		while (head<tail)
		{
			if (Position_transfer(end_Position)) break; // terminate BFS
			head++;
		}
		
		// now it is the time to generate the path
		int path[] = new int[step_number[tail]]; // just enough length to accommodate the path 
		while (tail != 0) // now we are tracing back, and the variable tail is recycled to point to the current element in the queue
		{
			path[step_number[tail]-1] = operation[tail];
					/*             ^to change to the correct array index*/ 
			tail = previous[tail]; // tracing back
		}
		
		return path;
	}
	
	static int[] path_optimizer(int color1, int color2)
	{
		int[] path1, path2;
		int[] resulting_path = new int[1];
		int minimum_length=100; // we strive to find the minimum length
		
		for (int i=0; i<2; i++) // enumerate starting Positions
			for (int j=0; j<4; j++) // enumerate the access Position of color 1
				for (int k=0; k<4; k++)
				{
					if ((access_Position[color1][j]==null) || (access_Position[color2][k]==null)) continue; // in case such combination does not exist	
					
					path1 = BFS (starting_Position[i],access_Position[color1][j]);// obtain the path
					path2 = BFS (access_Position[color1][j],access_Position[color2][k]);
					
					//update final path
					if (path1.length+path2.length<minimum_length)
					{
						resulting_path= new int [path1.length+path2.length+3];
						if (i==0) resulting_path[0] = LEFT;
						if (i==1) resulting_path[0] = RIGHT;
						for (int l=0;l<path1.length;l++)
							resulting_path[l+1] = path1[l];
						
						resulting_path[path1.length+1]= REACH_DESTINATION; // mark one end
						
						for (int l=0;l<path2.length;l++)
							resulting_path[path1.length+l+2] = path2[l];
						
						resulting_path[path1.length+path2.length+2] = REACH_DESTINATION;
					}
					
				}
		return resulting_path;
	}
	
}
